package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class User_Info extends AppCompatActivity {
    EditText name, age, sex, height, weight; // edit text objects
    Button save;    // button object
    DatabaseHelper Db; //database object

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
                }
                else {
                    long isInserted = Db.insertUserInfoData(usernameinfo, ageinfo, genderinfo, heightinfo, weightinfo);
                    Toasty.success(User_Info.this, " Saved Successfully! ", Toast.LENGTH_SHORT, true).show();
                    Intent intent = new Intent(getApplicationContext(), Test.class);
                    startActivity(intent);
                }
                }


        });

    }


}