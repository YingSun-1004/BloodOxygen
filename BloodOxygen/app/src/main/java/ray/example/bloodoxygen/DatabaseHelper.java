package ray.example.bloodoxygen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME = "userinfo_db";
    public static final int DATABASE_VERSION = 1;

    public static final String UI_TABLE_NAME = "usersinfo";

    public static final String COLUMN_UI_ID = "_id";
    public static final String COLUMN_UI_NAME = "name";
    public static final String COLUMN_UI_AGE = "age";
    public static final String COLUMN_UI_GENDER = "sex";
    public static final String COLUMN_UI_HEIGHT = "height";
    public static final String COLUMN_UI_WEIGHT = "weight";

    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table IF NOT EXISTS " + UI_TABLE_NAME + " (" + COLUMN_UI_ID + " TEXT primary key, " + COLUMN_UI_NAME + " TEXT, " + COLUMN_UI_AGE + " TEXT," +
                COLUMN_UI_GENDER +  " TEXT, " + COLUMN_UI_HEIGHT + " TEXT, " + COLUMN_UI_WEIGHT + " TEXT)");
        Log.d(TAG, db.toString());
        db.execSQL("create table if not exists datas(id Long primary key,number varchar(10),create_time varchar(100))");
//        String CREATE_TABLE_UIDATA = "CREATE TABLE " + UI_TABLE_NAME +
//                " (" + COLUMN_UI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + COLUMN_UI_NAME + " TEXT NOT NULL,"
//                + COLUMN_UI_AGE + " TEXT NOT NULL,"
//                + COLUMN_UI_GENDER + " TEXT NOT NULL,"
//                + COLUMN_UI_HEIGHT + " TEXT NOT NULL,"
//                + COLUMN_UI_WEIGHT + " TEXT NOT NULL)";
//        Log.d(TAG, CREATE_TABLE_UIDATA);
//        db.execSQL(CREATE_TABLE_UIDATA);
//        Log.d(TAG, "db created.");
//        db.execSQL("create table if not exists "+UI_TABLE_NAME+" ("
//                + "COLUMN_UI_ID" + " integer primary key, "
//                + "COLUMN_UI_NAME" + " varchar(255),"
//                + "COLUMN_UI_AGE" + " varchar(255),"
//                + "COLUMN_UI_GENDER" + " varchar(255),"
//                + "COLUMN_UI_HEIGHT" + " varchar(255),"
//                + "COLUMN_UI_WEIGHT" + " varchar(255)"
//                +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists user_db");
        db.execSQL("drop Table if exists datas");


    }

    public Boolean insertData(String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Long id = System.currentTimeMillis();
        contentValues.put("id",id);
        contentValues.put("number", number);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        contentValues.put("create_time",time);
        long result = db.insert("datas", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

//    public List<DataBean_information> selectAllData(){
//        List<DataBean_information> dataBeanList = new ArrayList<>();
//        DataBean_information dataBean = new DataBean_information();
//        //初始化头部
//        dataBean.setCreateTime("测量时间");
//        dataBean.setNumber("测量数据");
//        dataBeanList.add(dataBean);
//        dataBean = new DataBean_information();
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        //创建游标对象
//        Cursor cursor = db.query("datas", null, null, null, null, null, "id ASC");
//        //利用游标遍历所有数据对象
//        while(cursor.moveToNext()){
//            dataBean.setId(cursor.getLong(cursor.getColumnIndex("id")));
//            dataBean.setNumber(cursor.getString(cursor.getColumnIndex("number")));
//            dataBean.setCreateTime(cursor.getString(cursor.getColumnIndex("create_time")));
//            dataBeanList.add(dataBean);
//            dataBean = new DataBean_information();
//        }
//        return dataBeanList;
//    }

    // constructor for inserting data into the database
    public Boolean insertUserInfoData(String name, String age, String sex, String height, String weight){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("sex", sex);
        contentValues.put("height", height);
        contentValues.put("weight", weight);
        long result = db.insert(UI_TABLE_NAME, null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public List<DataBean> selectAllData(){
        List<DataBean> dataBeanList = new ArrayList<>();
        DataBean dataBean = new DataBean();
        //初始化头部
        dataBean.setCreateTime("measure time");
        dataBean.setNumber("Measurement data");
        dataBeanList.add(dataBean);
        dataBean = new DataBean();

        SQLiteDatabase db = this.getWritableDatabase();
        //创建游标对象
        Cursor cursor = db.query("datas", null, null, null, null, null, "id ASC");
        //利用游标遍历所有数据对象
        while(cursor.moveToNext()){
            dataBean.setId(cursor.getLong(cursor.getColumnIndex("id")));
            dataBean.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            dataBean.setCreateTime(cursor.getString(cursor.getColumnIndex("create_time")));
            dataBeanList.add(dataBean);
            dataBean = new DataBean();
        }
        return dataBeanList;
    }

    // constructor for checking name
    public Boolean checkName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS " + UI_TABLE_NAME + " (" + COLUMN_UI_ID + " TEXT primary key, " + COLUMN_UI_NAME + " TEXT, " + COLUMN_UI_AGE + " TEXT," +
                COLUMN_UI_GENDER +  " TEXT, " + COLUMN_UI_HEIGHT + " TEXT, " + COLUMN_UI_WEIGHT + " TEXT)");
        Cursor cursor = db.rawQuery("select * from  "  + UI_TABLE_NAME + " where name= ?" ,new String[] {name} );
        if (cursor.getCount()>0)
            return  true;
        else
            return  false;

    }
    //Read data from table
    Cursor readAllData(){
        String query = "select * from "+UI_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        /*Cursor cursor = null;
        if (db!= null){
            cursor = db.rawQuery(query,null);
        }*/
        //Cursor cursor = db.rawQuery(query,null);
        return db.rawQuery(query,null);
    }
}
