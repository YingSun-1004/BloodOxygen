package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class displaydata extends AppCompatActivity {
    RecyclerView recyclerView;
    Button BT_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaydata);

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor c = db.readAllData();
        ArrayList<DataBean_information> dataBeanInformationArrayList = new ArrayList<>();
        DataBean_information dataBeanInformation = new DataBean_information();
        CustomAdapter adapter = null;
        BT_home = findViewById(R.id.bt_home);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        while (!c.isAfterLast()) {
//            NAME_TEXT.setText(c.getString(c.getColumnIndex("name")));
//            AGE_TEXT.setText(c.getString(c.getColumnIndex("age")));
//            SEX_TEXT.setText(c.getString(c.getColumnIndex("sex")));
//            WEIGHT_TEXT.setText(c.getString(c.getColumnIndex("height")));
//            HEIGHT_TEXT.setText(c.getString(c.getColumnIndex("weight")));
//        }
        /*while (!c.isAfterLast()) {
            String name = c.getString(c.getColumnIndex("name"));
            String age = c.getString(c.getColumnIndex("age"));
            String sex = c.getString(c.getColumnIndex("sex"));
            String ht = c.getString(c.getColumnIndex("height"));
            String wt = c.getString(c.getColumnIndex("weight"));
            dataBeanInformation = new DataBean_information(name, Integer.parseInt(age), sex, Double.parseDouble(ht), Double.parseDouble(wt));
            dataBeanInformationArrayList.add(dataBeanInformation);
        }

         */
        if (c.getCount() != 0) {
            c.moveToFirst();

            do {
                String name = c.getString(1);
                String age = c.getString(2);
                String sex = c.getString(3);
                String ht = c.getString(4);
                String wt = c.getString(5);
                dataBeanInformation = new DataBean_information(name, Integer.parseInt(age), sex, Double.parseDouble(ht), Double.parseDouble(wt));
                dataBeanInformationArrayList.add(dataBeanInformation);
            } while (c.moveToNext());
        }
        if (!dataBeanInformationArrayList.isEmpty()) {
            adapter = new CustomAdapter(this, dataBeanInformationArrayList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toasty.error(this, "Data not found..");
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