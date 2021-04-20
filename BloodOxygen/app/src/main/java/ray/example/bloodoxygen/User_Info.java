package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class User_Info extends AppCompatActivity {
    EditText name, age, sex, height, weight; // edit text objects
    Button save;    // button object
    DatabaseHelper Db; //database object
    ArrayList<String>NAME,AGE,SEX,HEIGHT,WEIGHT;

    String TAG1 = " Information page ";
    String TAG2 = "error - enter all fields  ";
    String TAG3 = " success - successfully saved ";
    String TAG4 = " Couldn't Save! ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__info);
        Log.d(TAG1, "onCreate: ");

        name = (EditText) findViewById(R.id.et_ui_name);
        age = (EditText) findViewById(R.id.et_ui_age);
        sex = (EditText) findViewById(R.id.et_ui_gender);
        height = (EditText) findViewById(R.id.et_ui_height);
        weight = (EditText) findViewById(R.id.et_ui_weight);
        save = (Button) findViewById(R.id.bt_ui_save);
        Db = new DatabaseHelper(this);



        save.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String usernameinfo = name.getText().toString();
                String ageinfo = age.getText().toString();
                String genderinfo = sex.getText().toString();
                String heightinfo = height.getText().toString();
                String weightinfo = weight.getText().toString();

                if (usernameinfo.equals("") || ageinfo.equals("") || genderinfo.equals("") || heightinfo.equals("") || weightinfo.equals("")) {
                    Log.d(TAG2, "onClick: ");
                    Toast.makeText(User_Info.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    Toasty.error(User_Info.this, "Please enter all fields", Toast.LENGTH_SHORT, true).show();
                } else {
                    Boolean checkuser = Db.checkName(usernameinfo);
                    if (checkuser == false) {
                        Log.d("Check User", "check user = false");
                        Boolean isInserted = Db.insertUserInfoData(usernameinfo, ageinfo, genderinfo, heightinfo, weightinfo);
                        if (isInserted == true) {
                            Log.d(TAG3, "Database SQL: ");
                            Toasty.success(User_Info.this, " Saved Successfully! ", Toast.LENGTH_SHORT, true).show();
//                            Toasty.success(User_Info.this, "Congrats Saved successful!", Toast.LENGTH_SHORT, true).show();
                            Intent intent = new Intent(getApplicationContext(), Test.class);
                            startActivity(intent);

                        } else {
                            Log.d(TAG4, "Database SQL: ");
                            Toast.makeText(User_Info.this, "Save failed", Toast.LENGTH_SHORT).show();
                        }


                    }
                }

            }


        });
        Db = new DatabaseHelper(User_Info.this);
        NAME = new ArrayList<>();
        AGE = new ArrayList<>();
        SEX = new ArrayList<>();
        HEIGHT = new ArrayList<>();
        WEIGHT = new ArrayList<>();
        //StoreDataInArrays();

    }
//    void StoreDataInArrays(){
//        Cursor cursor = Db.readAllData();
//        if (cursor.getCount()==0){
//            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
//        }else {
//            while (cursor.moveToNext()){
//                NAME.add(cursor.getString(0));
//                AGE.add(cursor.getString(1));
//                SEX.add(cursor.getString(2));
//                HEIGHT.add(cursor.getString(3));
//                WEIGHT.add(cursor.getString(4));
//
//
//            }
//        }
//    }



}