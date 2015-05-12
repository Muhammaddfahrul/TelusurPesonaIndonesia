package com.pplc9.telusurpesonaindonesia.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pplc9.telusurpesonaindonesia.R;

public class SplashViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int timer = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashViewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, timer);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view);
    }
}
