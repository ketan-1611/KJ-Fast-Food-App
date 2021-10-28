package com.example.kjfastfood.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(
                 "create table orders " +
                         "(id integer primary key autoincrement," +
                         "name text," +
                         "phone text," +
                         "price int, " +
                         "image int, " +
                         "quantity int, " +
                         "description text, " +
                         "foodname text)"

         );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
              db.execSQL("DROP Table if exists orders");
              onCreate(db);
    }

    public boolean insertOrder(String name, String phone, int price , int image ,String desc, String foodName,int quantity)
    {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodname",foodName);
        values.put("quantity",quantity);
        long id = db.insert("orders",null,values);
        if(id<=0)
            return false;
        else
            return true;
    }


    public ArrayList<OrderModel> getOrders(){
        ArrayList<OrderModel> orderModels = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select id,foodname,image,price from orders",null);
        if(cursor.moveToFirst())
        {
            while (cursor.moveToNext())
            {
                OrderModel model = new OrderModel();
                model.setOrderNumber(cursor.getInt(0) +"");
                model.setOrderName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setOrderPrice(cursor.getInt(3) +"");
                orderModels.add(model);

            }
        }

        cursor.close();
        db.close();
        return orderModels;
    }


    public Cursor getOrderById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from orders where id = "+ id,null);
        if(cursor != null)
         cursor.moveToFirst();

        return cursor;
    }

    public boolean updateOrder(String name, String phone, int price , int image ,String desc, String foodName,int quantity,int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodname",foodName);
        values.put("quantity",quantity);
        long row = db.update("orders",values,"id = "+id,null);
        if(row<=0)
            return false;
        else
            return true;
    }

    public int deleteOrder(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("orders","id= "+id,null);
    }
}
