package ray.example.bloodoxygen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DB_NANE = "database_weight_record";
    public static final String Table_User_NANE = "table_user";


    public MyDBHelper(Context context) {
        super(context, DB_NANE, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key,password TEXT)");


//        db.execSQL("create table if not exists "+Table_User_NANE+"("
//                +"UserID"+" interger primary key,"
//                +"UserRealName"+" varchar(255),"
//                +"UserPassWord"+" varchar(255)"
//                +")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {

        MyDB.execSQL("drop Table if exists users");
    }

    // constructor for inserting data into the database
    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    // constructor for checking username
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username= ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    // constructor for checking password
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username= ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }


}
