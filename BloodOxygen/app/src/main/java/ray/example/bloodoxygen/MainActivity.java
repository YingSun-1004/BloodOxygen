package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    //object variables
    EditText loginUserId,loginUserPW;
    Button loginLoginButton,button_register,button_quickstart;
    TextView loginError,loginToRegister;

    //Tags
    String TAG1 = " This is Oncreate activity in LoginActivity ";
    String TAG2 = " error - missing fields ";
    String TAG3 = " success - Sign in successfull ";
    String TAG4 = "error - Invalid credentials  ";

    LoginClickListener loginClickListener;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase mydatabase;
    private Context context;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface LoginClickListener{
        public void loginClick(String UserID);
        public void loginToRegisterClick();
    }

    public void setLoginClickListener(LoginClickListener loginClickListener){
        this.loginClickListener = loginClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUserId = findViewById(R.id.et_s_name); // username
        loginUserPW = findViewById(R.id.et_s_pass); //password
        loginLoginButton = findViewById(R.id.bt_sign); //login button
        loginError = findViewById(R.id.loginError); // dont know---------
        loginToRegister = findViewById(R.id.loginToRegister); // dont know---------
        button_register = findViewById(R.id.bt_register);//register button
        myDBHelper = new MyDBHelper(this);
        button_quickstart = findViewById(R.id.bt_quickStart);
        button_quickstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Home_Page_Activity.class);
                startActivity(intent);
            }
        });

        loginLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = loginUserId.getText().toString();
                String pass = loginUserPW.getText().toString();

                if (user.equals("")||pass.equals("")){
                    Log.d(TAG2, "onClick: ");

                    Toast.makeText(MainActivity.this,"Please enter all fields",Toast.LENGTH_LONG).show();
                    Toasty.error(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT, true).show();
                    //Toast.makeText(getBaseContext(), "Reason can not be blank", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = myDBHelper.checkusernamepassword(user,pass);
                    if (checkuserpass==true){
                        Log.d(TAG3, "Database SQL: ");
                        Toast.makeText(MainActivity.this,"Sign in successfully",Toast.LENGTH_SHORT).show();
                        Toasty.success(MainActivity.this, "Congrats login successful!", Toast.LENGTH_SHORT, true).show();
                        Intent intent = new Intent(getApplicationContext(),Test.class);
                        startActivity(intent);

                    }else{
                        Log.d(TAG4, "Database SQL: ");
                        Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                        Toasty.error(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT, true).show();

                    }


                }
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mydatabase.close();
        myDBHelper.close();
    }


}