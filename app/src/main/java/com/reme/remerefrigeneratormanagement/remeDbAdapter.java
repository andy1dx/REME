package com.reme.remerefrigeneratormanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class remeDbAdapter {
    remeDbHelper remehelper;
    public remeDbAdapter(Context context)
    {
        remehelper = new remeDbHelper(context);
    }
    public long insertSize(String name,String status){
        SQLiteDatabase dbb = remehelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(remeDbHelper.PLACE_NAME, name);
        contentValues.put(remeDbHelper.PLACE_STATUS, status);
        long id = dbb.insert(remeDbHelper.TABLE_PLACE, null , contentValues);
        return id;
    };

    public long deleteSize(String name){
        SQLiteDatabase db = remehelper.getWritableDatabase();

        return db.delete(remeDbHelper.TABLE_PLACE,remeDbHelper.PLACE_NAME + "=?",new String[]{name});
    }

    public int checkSizeData() {
        SQLiteDatabase db = remehelper.getWritableDatabase();
        String[] columns = {remeDbHelper.PLACE_UID,remeDbHelper.PLACE_NAME,remeDbHelper.PLACE_STATUS};
        Cursor cursor =db.query(remeDbHelper.TABLE_PLACE,columns,null,null,null,null,null);

        return cursor.getCount();
    }
    public String getSizeData()
    {
        SQLiteDatabase db = remehelper.getWritableDatabase();
        String[] columns = {remeDbHelper.PLACE_UID,remeDbHelper.PLACE_NAME,remeDbHelper.PLACE_STATUS};
        Cursor cursor =db.query(remeDbHelper.TABLE_PLACE,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(remeDbHelper.PLACE_UID));
            String name =cursor.getString(cursor.getColumnIndex(remeDbHelper.PLACE_NAME));
            String  password =cursor.getString(cursor.getColumnIndex(remeDbHelper.PLACE_STATUS));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

    public Cursor viewSizeData()
    {
        SQLiteDatabase db = remehelper.getReadableDatabase();
        String query = "select * from "+ remeDbHelper.TABLE_PLACE;
        Cursor cursor= db.rawQuery(query, null);

        return cursor;
    }

    static class remeDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "databaseReme";    // Database Name
        private static final String TABLE_PLACE = "myPlace";   // Table Name
        private static final String PLACE_UID="_id";     // Column I (Primary Key)
        private static final String PLACE_NAME = "Name";    //Column II
        private static final String PLACE_STATUS= "Status";    // Column III


        private static final String TABLE_ITEM = "myItem";   // Table Name
        private static final String ITEM_UID="_id";     // Column I (Primary Key)
        private static final String ITEM_PLACE_UID = "place-id";    //Column II
        private static final String ITEM_PRODUCT_ID = "product-id";    //Column II
        private static final String ITEM_DATE= "Date";    // Column III
        private static final String ITEM_STATUS = "Status";    //Column II
        private static final String ITEM_PRICE = "Price";    //Column II

        private static final String TABLE_PRODUCT = "myProduct";   // Table Name
        private static final String PRODUCT_UID="_id";     // Column I (Primary Key)
        private static final String PRODUCT_BARCODE = "Barcode";    //Column II
        private static final String PRODUCT_NAME = "Name";    //Column II
        private static final String PRODUCT_STATUS= "Status";    // Column III

        private static final int DATABASE_Version = 1;    // Database Version

        private static final String CREATE_TABLE_PLACE = "CREATE TABLE "+TABLE_PLACE+
                " ("+PLACE_UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PLACE_NAME+" VARCHAR(255) ,"+ PLACE_STATUS+" INTEGER);";
        private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE "+TABLE_PRODUCT+
                " ("+PRODUCT_UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PRODUCT_BARCODE+" VARCHAR(255) ,"+PRODUCT_NAME+" VARCHAR(255) ,"+ PRODUCT_STATUS+" INTEGER);";
        private static final String CREATE_TABLE_ITEM = "CREATE TABLE "+TABLE_ITEM+
                " ("+ITEM_UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ITEM_PLACE_UID+" INTEGER ,"+ITEM_PRODUCT_ID+" INTEGER ,"+ITEM_DATE+" DATE ,"+ ITEM_STATUS+" INTEGER,"+ ITEM_PRICE+" INTEGER);";

        private static final String DROP_TABLE_PLACE ="DROP TABLE IF EXISTS "+TABLE_PLACE;
        private static final String DROP_TABLE_PRODUCT ="DROP TABLE IF EXISTS "+TABLE_PRODUCT;
        private static final String DROP_TABLE_ITEM ="DROP TABLE IF EXISTS "+TABLE_ITEM;

        private Context context;

        public remeDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE_PLACE);
                db.execSQL(CREATE_TABLE_PRODUCT);
                db.execSQL(CREATE_TABLE_ITEM);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE_PLACE);
                db.execSQL(DROP_TABLE_PRODUCT);
                db.execSQL(DROP_TABLE_ITEM);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}
