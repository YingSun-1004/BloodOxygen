package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tips extends AppCompatActivity {
    TextView tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        tips = (TextView) findViewById(R.id.Tv_tips);
        int age = 20;

        Button btn = (Button) findViewById(R.id.bt_back);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
            }
        });

        tips.setText(Tips(age));




        DatabaseHelper db = new DatabaseHelper(this);

    }
    public String Tips(int age){
        if(age<20){
            return "Practice portion control, eat veggies, exercise regularly";
        }
        else if(age<40){
            return "Manage your stress, exercise daily for 30 minutes, eat proteins, schedule vital health checks";
        }
        else if(age<60){
            return "Manage your stress, exercise daily for 30 minutes, eat proteins, schedule vital health checks";
        }
        else {
            return "Manage your stress, exercise daily for 30 minutes, eat proteins, schedule vital health checks";
        }
    }
}