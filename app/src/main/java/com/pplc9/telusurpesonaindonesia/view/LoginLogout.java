package com.pplc9.telusurpesonaindonesia.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pplc9.telusurpesonaindonesia.R;
import com.pplc9.telusurpesonaindonesia.util.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginLogout extends ActionBarActivity {

    JSONParser jParser = new JSONParser();

    private static String url = "http://ppl-c09.cs.ui.ac.id/pehape/checkLogin.php";

    private static String TAG_SUKSES = "sukses";

    public static final String LogKey = "LogKey";
    public static final String UserKey = "UserKey";
    public static final String MyPref = "MyPref";

    SharedPreferences sharedPreferences;

    EditText txtUser, txtPass;
    Button btnLogin, btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_logout);

        Intent i = getIntent();
        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(LogKey, "").equals("true")) {
            Intent balik = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(balik);
        }

        txtUser = (EditText)findViewById(R.id.txtuser);
        txtPass = (EditText)findViewById(R.id.txtpassword);

        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnRegist = (Button)findViewById(R.id.btnregist);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new CheckLogin().execute();
            }
        });

        btnRegist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), registrasi.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class CheckLogin extends AsyncTask<String, String, String> {

        String username = txtUser.getText().toString();
        String passwd = txtPass.getText().toString();

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> args = new ArrayList<NameValuePair>();
            args.add(new BasicNameValuePair("Username", username));
            args.add(new BasicNameValuePair("Password", passwd));

            JSONObject json = jParser.makeHttpRequest(url, "GET", args);
            try {
                int sukses = json.getInt(TAG_SUKSES);
                if(sukses == 1) {
                    String loggedIn = "true";
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(LogKey, loggedIn);
                    editor.putString(UserKey, username);
                    editor.commit();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
                // do something here
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
