package com.ahmadabuhasan.samudrajaya;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Objects;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class BarcodeActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    int currentApiVersion = Build.VERSION.SDK_INT;
    public ZBarScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.qr_barcode);

        if (this.currentApiVersion >= 23) {
            requestCameraPermission();
        }

        ZBarScannerView zBarScannerView = new ZBarScannerView(this);
        scannerView = zBarScannerView;
        setContentView(zBarScannerView);
        scannerView.startCamera();
        scannerView.setResultHandler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.scannerView.setResultHandler(this);
        this.scannerView.startCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.scannerView.stopCamera();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(getApplicationContext(), rawResult.getContents(), Toast.LENGTH_SHORT).show();
        MainActivity.editText.setText(rawResult.getContents());
        Log.d("QRCodeScanner", rawResult.getContents());
        Log.d("QRCodeScanner", rawResult.getBarcodeFormat().getName());
        onBackPressed();
    }

    public void requestCameraPermission() {
        Dexter.withContext(BarcodeActivity.this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        BarcodeActivity.this.scannerView = new ZBarScannerView(BarcodeActivity.this);
                        BarcodeActivity barcodeActivity = BarcodeActivity.this;
                        barcodeActivity.setContentView(barcodeActivity.scannerView);
                        BarcodeActivity.this.scannerView.startCamera();
                        BarcodeActivity.this.scannerView.setResultHandler(BarcodeActivity.this);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        if (permissionDeniedResponse.isPermanentlyDenied()) {
                            Toast.makeText(getApplicationContext(), "Camera Permission Required", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}