package ray.example.bloodoxygen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<DataBean> list = new ArrayList<>();
    DatabaseHelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_data);
        recyclerView = findViewById(R.id.recycler);

        Db = new DatabaseHelper(this);
        list =  Db.selectAllData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        HistoryDataAdapter historyDataAdapter = new HistoryDataAdapter(HistoryDataActivity.this,list);
        recyclerView.setAdapter(historyDataAdapter);

    }

}