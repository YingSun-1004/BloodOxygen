package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText username, password, phonenumber, retypepassword; // edit text objects
    Button register;    // button objects
    MyDBHelper DB; //database object

    String TAG1 = " Register Page ";
    String TAG2 = "error - enter all fields  ";
    String TAG3 = " success - successful registration ";
    String TAG5 = " error - existing user ";
    String TAG6 = " error- password not matching ";
    String TAG4 = " error- Registration failed ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG1, "onCreate: ");
        // importing the object values for the variables
        username = (EditText) findViewById(R.id.et_r_name);
        password = (EditText) findViewById(R.id.et_r_pass);
        retypepassword = (EditText) findViewById(R.id.et_r_retypepass);
        phonenumber = (EditText) findViewById(R.id.et_r_phone);
        register = (Button) findViewById(R.id.bt_r);
        DB = new MyDBHelper(this);

        // creating listeners
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = retypepassword.getText().toString();
                String phone = phonenumber.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    Log.d(TAG2, "onClick: ");
                    Toast.makeText(Register.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {

                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Log.d(TAG3, "Database SQL: ");
                                Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                Log.d(TAG4, "Database SQL: ");
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.d(TAG5, "Database SQL: ");
                            Toast.makeText(Register.this, "User already exists please Login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d(TAG6, "Database SQL: ");
                        Toast.makeText(Register.this, "Password not matching", Toast.LENGTH_SHORT).show();


                    }
                }


            }
        });
    }
}


