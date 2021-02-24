package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private EditText EdText_1, EdText_2, EdText_3;
    private ImageView ImView_1;
    private Button Btn_1, Btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_1 = findViewById(R.id.bt_1);
        Btn_2 = findViewById(R.id.bt_2);

        Btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
            }
        });
    }
}