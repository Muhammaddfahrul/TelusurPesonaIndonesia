package com.pplc9.telusurpesonaindonesia.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pplc9.telusurpesonaindonesia.R;


public class MainActivity extends ActionBarActivity {

    public static final String LogKey = "LogKey";
    public static final String UserKey = "UserKey";
    public static final String MyPref = "MyPref";

    Button btnViewListTaman;
    Button btnViewMap;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);

        if(sharedPreferences.getString(LogKey, "").equals("true")) {
            Toast.makeText(getApplicationContext(), "anda telah login", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "anda sudah logout", Toast.LENGTH_SHORT).show();
        }

        btnViewListTaman = (Button)findViewById(R.id.btnViewList);
        btnViewMap = (Button)findViewById(R.id.btnViewMap);

        btnViewListTaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showAllTaman.class);
                startActivity(i);
            }
        });

        btnViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showMapTaman.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_feedback:
                Intent i = new Intent(getApplicationContext(), kirimFeedback.class);
                startActivity(i);
                break;
            case R.id.menu_info:
                Toast.makeText(getApplicationContext(), "something", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_login:
                Intent int1 = new Intent(getApplicationContext(), LoginLogout.class);
                startActivity(int1);
                break;
            case R.id.menu_logout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(LogKey, "false");
                editor.putString(UserKey, "");
                editor.commit();
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
                finish();


            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
