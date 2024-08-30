package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ProductDataSource {

    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IS_FREE = "is_free";
    private static final String COLUMN_CATEGORY = "category";

    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;

    public ProductDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // 추가
    public void addProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, product.getTitle());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_IS_FREE, product.isFree() ? 1 : 0);
        values.put(COLUMN_CATEGORY, product.getCategory());

        long result = database.insert(TABLE_PRODUCTS, null, values);
    }





    // 특정 카테고리의 제품을 가져오는 메서드
    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> productList = new ArrayList<>();
        String selection = COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = { category };

        Cursor cursor = database.query(
                TABLE_PRODUCTS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String price = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                boolean isFree = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FREE)) == 1;
                String productCategory = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));

                Product product = new Product(title, price, description, isFree, productCategory);
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "products.db";
        private static final int DATABASE_VERSION = 2; // 데이터베이스 버전 업데이트

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE products (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_PRICE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_IS_FREE + " INTEGER, " +
                    COLUMN_CATEGORY + " TEXT);");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion < 2) {
                db.execSQL("ALTER TABLE products ADD COLUMN category TEXT");
            }
        }
    }
}