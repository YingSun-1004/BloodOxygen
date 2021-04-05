package ray.example.bloodoxygen;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME = "userinfo-db";
    public static final int DATABASE_VERSION = 1;

    public static final String UI_TABLE_NAME = "usersinfo";

    public static final String COLUMN_UI_ID = "_id";
    public static final String COLUMN_UI_NAME = "username";
    public static final String COLUMN_UI_AGE = "age";
    public static final String COLUMN_UI_GENDER = "gender";
    public static final String COLUMN_UI_HEIGHT = "height";
    public static final String COLUMN_UI_WEIGHT = "weight";

    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_UIDATA = "CREATE TABLE " + UI_TABLE_NAME +
                " (" + COLUMN_UI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_UI_NAME + " TEXT NOT NULL,"
                + COLUMN_UI_AGE + " TEXT NOT NULL,"
                + COLUMN_UI_GENDER + " TEXT NOT NULL,"
                + COLUMN_UI_HEIGHT + " TEXT NOT NULL,"
                + COLUMN_UI_WEIGHT + " TEXT NOT NULL)";
        Log.d(TAG, CREATE_TABLE_UIDATA);
        db.execSQL(CREATE_TABLE_UIDATA);
        Log.d(TAG, "db created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertUserInfoData(String name, String age, String sex, String height, String weight){
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("sex", sex);
        contentValues.put("height", height);
        contentValues.put("weight", weight);
        long result = db.insert(UI_TABLE_NAME, null, contentValues);
        return id;
    }
}
