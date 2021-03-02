package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    EditText loginUserId,loginUserPW;
    Button loginLoginButton;
    TextView loginError,loginToRegister;

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

        loginUserId = findViewById(R.id.et_s_name);
        loginUserPW = findViewById(R.id.et_s_pass);
        loginLoginButton = findViewById(R.id.bt_sign);
        loginError = findViewById(R.id.loginError);
        loginToRegister = findViewById(R.id.loginToRegister);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mydatabase.close();
        myDBHelper.close();
    }

}