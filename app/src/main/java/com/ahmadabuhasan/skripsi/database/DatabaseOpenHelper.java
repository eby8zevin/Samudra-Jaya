package com.ahmadabuhasan.skripsi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "skripsi.db";

    // Table Name
    public static final String TABLE_PRODUCT = "products";
    public static final String TABLE_CATEGORY = "product_category";
    public static final String TABLE_WEIGHT = "product_weight";
    public static final String TABLE_SHOP = "shop";
    public static final String TABLE_SUPPLIER = "suppliers";

    // Column products
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_CODE = "product_code";
    public static final String PRODUCT_CATEGORY = "product_category";
    public static final String PRODUCT_BUY = "product_buy";
    public static final String PRODUCT_STOCK = "product_stock";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_TOTAL_QTY = "product_total_qty";
    public static final String PRODUCT_DISC_QTY = "product_disc_qty";
    public static final String PRODUCT_WEIGHT = "product_weight";
    public static final String PRODUCT_WEIGHT_UNIT_ID = "product_weight_unit_id";
    public static final String PRODUCT_LAST_UPDATE = "product_last_update";
    public static final String PRODUCT_INFORMATION = "product_information";
    public static final String PRODUCT_SUPPLIER = "product_supplier";

    // Column product_category
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";

    // Column product_weight
    public static final String WEIGHT_ID = "weight_id";
    public static final String WEIGHT_UNIT = "weight_unit";

    // Column shop
    public static final String SHOP_ID = "shop_id";
    public static final String SHOP_NAME = "shop_name";
    public static final String SHOP_CONTACT = "shop_contact";
    public static final String SHOP_EMAIL = "shop_email";
    public static final String SHOP_ADDRESS = "shop_address";
    public static final String SHOP_CURRENCY = "shop_currency";
    public static final String SHOP_TAX = "tax";

    // Column Suppliers
    public static final String SUPPLIER_ID = "supplier_id";
    public static final String SUPPLIER_NAME = "supplier_name";
    public static final String SUPPLIER_ADDRESS = "supplier_address";
    public static final String SUPPLIER_CONTACT = "supplier_contact";
    public static final String SUPPLIER_FAX = "supplier_fax";
    public static final String SUPPLIER_SALES = "supplier_sales";
    public static final String SUPPLIER_HP = "supplier_hp";
    public static final String SUPPLIER_ACCOUNT = "supplier_account";
    public static final String SUPPLIER_INFORMATION = "supplier_information";
    public static final String SUPPLIER_LAST_UPDATE = "supplier_last_update";


    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Table Create Statement
    // products
    private static final String CREATE_PRODUCTS = "CREATE TABLE " + TABLE_PRODUCT +
            "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PRODUCT_NAME + " TEXT,"
            + PRODUCT_CODE + " TEXT,"
            + PRODUCT_CATEGORY + " TEXT,"
            + PRODUCT_BUY + " TEXT,"
            + PRODUCT_STOCK + " TEXT,"
            + PRODUCT_PRICE + " TEXT,"
            + PRODUCT_TOTAL_QTY + " TEXT,"
            + PRODUCT_DISC_QTY + " TEXT,"
            + PRODUCT_WEIGHT + " TEXT,"
            + PRODUCT_WEIGHT_UNIT_ID + " TEXT,"
            + PRODUCT_LAST_UPDATE + " TEXT,"
            + PRODUCT_INFORMATION + " TEXT,"
            + PRODUCT_SUPPLIER + " TEXT"
            + ")";

    private static final String CREATE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY +
            "(" + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CATEGORY_NAME + " TEXT"
            + ")";

    private static final String CREATE_WEIGHT = "CREATE TABLE " + TABLE_WEIGHT +
            "(" + WEIGHT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + WEIGHT_UNIT + " TEXT"
            + ")";

    private static final String CREATE_SHOP = "CREATE TABLE " + TABLE_SHOP +
            "(" + SHOP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SHOP_NAME + " TEXT,"
            + SHOP_CONTACT + " TEXT,"
            + SHOP_EMAIL + " TEXT,"
            + SHOP_ADDRESS + " TEXT,"
            + SHOP_CURRENCY + " TEXT,"
            + SHOP_TAX + " TEXT"
            + ")";

    private static final String CREATE_SUPPLIERS = "CREATE TABLE " + TABLE_SUPPLIER +
            "(" + SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SUPPLIER_NAME + " TEXT,"
            + SUPPLIER_ADDRESS + " TEXT,"
            + SUPPLIER_CONTACT + " TEXT,"
            + SUPPLIER_FAX + " TEXT,"
            + SUPPLIER_SALES + " TEXT,"
            + SUPPLIER_HP + " TEXT,"
            + SUPPLIER_ACCOUNT + " TEXT,"
            + SUPPLIER_INFORMATION + " TEXT,"
            + SUPPLIER_LAST_UPDATE + " TEXT"
            + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {

        // script sql
        db.execSQL(CREATE_PRODUCTS);
        db.execSQL(CREATE_CATEGORY);
        db.execSQL(CREATE_WEIGHT);
        db.execSQL(CREATE_SHOP);
        db.execSQL(CREATE_SUPPLIERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLIER);

        onCreate(db);

    }
}
