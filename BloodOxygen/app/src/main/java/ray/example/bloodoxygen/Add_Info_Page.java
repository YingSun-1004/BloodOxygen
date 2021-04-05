package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Add_Info_Page extends AppCompatActivity {

    Button Btn_info, Btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__info__page);

        Btn_info = findViewById(R.id.bt_Add);
        Btn_test = findViewById(R.id.bt_Test);

        Btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),User_Info.class);
                startActivity(intent);
            }
        });

        Btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Test.class);
                startActivity(intent);
            }
        });
    }
}