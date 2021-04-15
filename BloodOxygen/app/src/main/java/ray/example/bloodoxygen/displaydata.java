package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displaydata extends AppCompatActivity {
    TextView NAME_TEXT, AGE_TEXT, SEX_TEXT, WEIGHT_TEXT, HEIGHT_TEXT;
    Button BT_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaydata);

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor c = db.readAllData();

        NAME_TEXT = findViewById(R.id.NAME_TEXT);
        AGE_TEXT = findViewById(R.id.AGE_TEXT);
        SEX_TEXT = findViewById(R.id.SEX_TEXT);
        WEIGHT_TEXT = findViewById(R.id.WEIGHT_TEXT);
        HEIGHT_TEXT = findViewById(R.id.HEIGHT_TEXT);
        BT_home = findViewById(R.id.bt_home);

        while (!c.isAfterLast()) {
            NAME_TEXT.setText(c.getString(c.getColumnIndex("name")));
            AGE_TEXT.setText(c.getString(c.getColumnIndex("age")));
            SEX_TEXT.setText(c.getString(c.getColumnIndex("sex")));
            WEIGHT_TEXT.setText(c.getString(c.getColumnIndex("height")));
            HEIGHT_TEXT.setText(c.getString(c.getColumnIndex("weight")));
        }
        BT_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Add_Info_Page.class);
                startActivity(intent);
            }
        });
    }
}