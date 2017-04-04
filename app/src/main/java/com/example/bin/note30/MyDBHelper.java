/*
package com.example.bin.note30;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

*/
/**
 * Created by Bin on 2017/3/30.
 *//*


public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database";
    private static final String TABLE_NAME = "userDate";
    private static final int DATABASE_VERSION = 1;
    MyDBHelper helper;

    public static final String CREATE_USERDATA="create table userData(" +
            "id integer primary key autoincrement,"
            +"username text not null,"
            +"sex text not null,"
            +"password text,"
            +"tel not null);";

    public MyDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public  void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USERDATA);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists userData");
        this.onCreate(db);
    }

    public void insert(String userName, String sex, String pwd, String tel){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into userData (username,sex,password,tel) values ("+ userName +"," + sex +"," +
        pwd + "," + tel +")";
        db.execSQL(sql);
        System.out.print(sql+"\n");
        db.close();
    }

    public int selectUser(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        int re = 0;
        Cursor c = db.rawQuery("select * from userData where username = ?", new String[]{userName});
        if(c.moveToFirst()){
            re = 1;
        }
        db.close();
        return re;
    }

    public int login(String userName, String pwd){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from userData where username = ? and password = ?", new String[]{userName,pwd});
        int re = 0;
        if(c.moveToFirst())
            re = 1;
        db.close();
        return re;
    }
}
*/
