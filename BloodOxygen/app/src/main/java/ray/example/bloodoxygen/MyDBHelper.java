package ray.example.bloodoxygen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NANE="database_weight_record";//数据库名
    public static final String Table_User_NANE="table_user";//用户表

    public MyDBHelper(Context context) {
        super(context, DB_NANE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //用户表
        db.execSQL("create table if not exists "+Table_User_NANE+"("
                +"UserID"+" interger primary key,"
                +"UserRealName"+" varchar(255),"
                +"UserPassWord"+" varchar(255)"
                +")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
