package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import androidx.annotation.Nullable;


public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String qr1="create table users(username text,email text,password text)";
    sqLiteDatabase.execSQL(qr1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean isEmailTaken( String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.query("users", null, "email = ?", new String[]{email}, null, null, null);
        boolean exists=cursor.getCount()>0;
        cursor.close();
        return exists;

    }

    public boolean isCredentialValid(String username,String password){
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[2];
        str[0]=username;
        str[1]=password;
        Cursor cursor=db.rawQuery("select *from users where username=? and password=?",str);
        boolean exists=cursor.getCount()>0;
        cursor.close();
        return exists;
    }
    public void  register(String username,String email,String password){

        
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("users",null,cv);
        db.close();

    }

    public String getUsernameByid(int userid){
SQLiteDatabase db=this.getReadableDatabase();
Cursor cursor= db.rawQuery("SELECT Username from users where id=?",new String[]{String.valueOf(userid)});
String username=null;

        if (cursor.moveToFirst()) {
            username = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return username;
    }





    public int login(String username,String password){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c= db.rawQuery("select *from users where username=? and password=?",str);
        if(c.moveToFirst()){
             result=1;

        }
        return result;
    }
}

