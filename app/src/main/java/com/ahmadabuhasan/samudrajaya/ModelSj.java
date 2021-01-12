package com.ahmadabuhasan.samudrajaya;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSj {

    @SerializedName("No")
    @Expose
    private int No;

    @SerializedName("NamaBarang")
    @Expose
    private String NamaBarang;

    @SerializedName("KodeBarang")
    @Expose
    private String KodeBarang;

    @SerializedName("Kategori")
    @Expose
    private String Kategori;

    @SerializedName("Beli")
    @Expose
    private int Beli;

    @SerializedName("Stok")
    @Expose
    private double Stok;

    @SerializedName("Harga")
    @Expose
    private int Harga;

    @SerializedName("JumlahQTY")
    @Expose
    private int JumlahQTY;

    @SerializedName("DiscQTY")
    @Expose
    private int DiscQTY;

    @SerializedName("Satuan")
    @Expose
    private String Satuan;

    @SerializedName("LastUpdate")
    @Expose
    private String LastUpdate;

    @SerializedName("Keterangan")
    @Expose
    private String Keterangan;

    @SerializedName("Suplier")
    @Expose
    private String Suplier;

    public ModelSj(int No, String NamaBarang, String KodeBarang, String Kategori, int Beli, Double Stok, int Harga, int JumlahQTY, int DiscQTY, String Satuan, String LastUpdate, String Keterangan, String Suplier) {
        this.No = No;
        this.NamaBarang = NamaBarang;
        this.KodeBarang = KodeBarang;
        this.Kategori = Kategori;
        this.Beli = Beli;
        this.Stok = Stok;
        this.Harga = Harga;
        this.JumlahQTY = JumlahQTY;
        this.DiscQTY = DiscQTY;
        this.Satuan = Satuan;
        this.LastUpdate = LastUpdate;
        this.Keterangan = Keterangan;
        this.Suplier = Suplier;
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public String getNamaBarang() {
        return NamaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        NamaBarang = namaBarang;
    }

    public String getKodeBarang() {
        return KodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        KodeBarang = kodeBarang;
    }

    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String kategori) {
        Kategori = kategori;
    }

    public int getBeli() {
        return Beli;
    }

    public void setBeli(int beli) {
        Beli = beli;
    }

    public double getStok() {
        return Stok;
    }

    public void setStok(double stok) {
        Stok = stok;
    }

    public int getHarga() {
        return Harga;
    }

    public void setHarga(int harga) {
        Harga = harga;
    }

    public int getJumlahQTY() {
        return JumlahQTY;
    }

    public void setJumlahQTY(int jumlahQTY) {
        JumlahQTY = jumlahQTY;
    }

    public int getDiscQTY() {
        return DiscQTY;
    }

    public void setDiscQTY(int discQTY) {
        DiscQTY = discQTY;
    }

    public String getSatuan() {
        return Satuan;
    }

    public void setSatuan(String satuan) {
        Satuan = satuan;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public String getSuplier() {
        return Suplier;
    }

    public void setSuplier(String suplier) {
        Suplier = suplier;
    }
}
