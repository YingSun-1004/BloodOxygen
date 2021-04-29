package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        text2.setText("Please start%");
        text2.setTextSize(100);
        text3.setText("Analysis：Start please");

        addinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    jsonParse(Home_Page_Activity.this);

                }catch (Exception e){
                    e.printStackTrace();
                }
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
                                text2.setText(message+"%");
                                text2.setTextSize(100);
                                if (message<90){
                                    text3.setText("Analysis：Danger");
                                }else if (message<96){
                                    text3.setText("Analysis：Normal");
                                }else {
                                    text3.setText("Analysis：Helathy often");
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
                text2.setText("Failed to get data");
                text2.setTextSize(30);
                text3.setText("Failed to get data");
                Toasty.error(context, error.toString(), Toast.LENGTH_SHORT, true).show();
            }
        });
        mQueue.add(request);

        request.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }


}