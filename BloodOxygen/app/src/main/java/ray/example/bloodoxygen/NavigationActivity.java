package ray.example.bloodoxygen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity {


    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Button btn = (Button) findViewById(R.id.cardHomeBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        Toast.makeText(getApplicationContext(), "Home Panel is Open", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Add_Info_Page.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.menu_assessment:
                        Toast.makeText(getApplicationContext(), "Assessment Panel is Open", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(getApplicationContext(), Tips.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_view_data:
                        Toast.makeText(getApplicationContext(), "Profile Panel is Open", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(getApplicationContext(), displaydata.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_logout:

                        AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
                        builder.setTitle("Sign out");
                        builder.setMessage("Are you sure you want to sign out?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent4 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent4);
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        break;

                    }

                return false;
            }

        });


    }


}