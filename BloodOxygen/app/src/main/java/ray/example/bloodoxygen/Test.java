package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class Test extends AppCompatActivity {
    Button BT_home;

    protected TextView mTvUser = null;

    TextView T_Tv2,Tv_2;
    DatabaseHelper Db; //database object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        BT_home = findViewById(R.id.bt_home);


        BT_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                startActivity(intent);
            }
        });

        T_Tv2 = findViewById(R.id.T_Tv2);
        Tv_2 = findViewById(R.id.Tv_2);
        T_Tv2.setText("Please start%");
        T_Tv2.setTextSize(80);
        Tv_2.setText("Analysis：Start please");
        findViewById(R.id.bt_Begin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    jsonParse(Test.this);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        findViewById(R.id.bt_Check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Test.this,HistoryDataActivity.class);
                startActivity(intent);
            }
        });

    }
    public void jsonParse(Context context) {
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, DataUrl.address, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);
                                String result= employee.getString("Data");

                                int message = Integer.parseInt(result);
                                T_Tv2.setText(message+"%");
//                                T_Tv2.setTextSize(80);
                                if (message<90){
                                    Tv_2.setText("Analysis：Danger");
                                }else if (message<96){
                                    Tv_2.setText("Analysis：normal");
                                }else {
                                    Tv_2.setText("Analysis：Healthy often");
                                }

                                Db = new DatabaseHelper(Test.this);
                                Boolean resultBoolean = Db.insertData(String.valueOf(message));
                                if (!resultBoolean){
                                    Toasty.error(Test.this, "insert fault", Toast.LENGTH_SHORT, true).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                T_Tv2.setText("Failed to get data");
                T_Tv2.setTextSize(30);
                Tv_2.setText("Failed to get data");
                Toasty.error(context, error.toString(), Toast.LENGTH_SHORT, true).show();
            }
        });
        mQueue.add(request);

        request.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

}