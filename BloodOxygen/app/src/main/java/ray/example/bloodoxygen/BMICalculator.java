package ray.example.bloodoxygen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMICalculator extends AppCompatActivity {
    EditText height,weight;
    TextView result,enter;
    Button resultbutton,resetbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i_calculator);

        height = (EditText) findViewById(R.id.et_ht);
        weight = (EditText) findViewById(R.id.et_wt);
        result = (TextView) findViewById(R.id.tv_bmi);
        enter = (TextView) findViewById(R.id.tv_hint);
        resultbutton = (Button) findViewById(R.id.bt_result);
        resetbutton = (Button) findViewById(R.id.bt_reset);

        Button btn = (Button) findViewById(R.id.cardHomeBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                startActivity(intent);
            }
        });

        resultbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strwt = weight.getText().toString();
                String strht = height.getText().toString();

                if(strwt.equals("")){
                    weight.setError("Please enter your weight");
                    weight.requestFocus();
                    return;
                }
                if(strht.equals("")){
                    height.setError("Please enter your height");
                    height.requestFocus();
                    return;
                }

                float wt = Float.parseFloat(strwt);
                float ht = Float.parseFloat(strht);

                float bmivalue = BMICalculate(wt,ht);

                enter.setText(interpretBMI(bmivalue));
                result.setText("BMI = "+bmivalue);
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height.setText("");
                weight.setText("");
                enter.setText("");
                result.setText("");
            }
        });
    }

    public float BMICalculate(float weight,float height){
        return weight / (height * height);
    }
    public String interpretBMI(float bmiValue){
        if(bmiValue<16){
            return "SEVERELY UNDERWEIGHT";
        }
        else if(bmiValue <18.5){
            return "UNDERWEIGHT";
        }
        else if(bmiValue <25){
            return "NORMAL";
        }
        else if (bmiValue <30){
            return "OVERWEIGHT";
        }
        else return "OBESE";
    }
}