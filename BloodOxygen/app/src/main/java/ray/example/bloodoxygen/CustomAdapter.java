package ray.example.bloodoxygen;

import android.content.Context;

import java.util.ArrayList;

public class CustomAdapter extends User_Info{
    private Context context;
    private ArrayList NAME,AGE,SEX,HEIGHT,WEIGHT;

    CustomAdapter(Context context,ArrayList NAME,ArrayList AGE,ArrayList SEX,
                  ArrayList HEIGHT,ArrayList WEIGHT){
        this.context= context;
        this.NAME = NAME;
        this.AGE = AGE;
        this.SEX = SEX;
        this.HEIGHT = HEIGHT;
        this.WEIGHT = WEIGHT;



    }

}
