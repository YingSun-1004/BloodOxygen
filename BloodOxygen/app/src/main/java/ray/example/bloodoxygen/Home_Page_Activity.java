package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home_Page_Activity extends AppCompatActivity {
    TextView text1, text2, text3;
    Button addinfo;

    String TAG = " Home Page ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page_);
        Log.d(TAG, "onCreate: ");

        text1 = (TextView) findViewById(R.id.Qs_Tv1);
        text2 = (TextView) findViewById(R.id.Qs_Tv2);
        text3 = (TextView) findViewById(R.id.Qs_Tv3);
        addinfo = (Button) findViewById(R.id.Qs_Begin);

        addinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), User_Info.class);
                startActivity(intent);
            }
        });

    }
}