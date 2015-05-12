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

public class addItitenary extends ActionBarActivity {

    JSONParser jParser = new JSONParser();


    public static final String LogKey = "LogKey";
    public static final String UserKey = "UserKey";
    public static final String MyPref = "MyPref";

    SharedPreferences sharedPreferences;

    private static final String url_insertItitenary = "http://ppl-c09.cs.ui.ac.id/pehape/insertItitenary.php";

    private static String TAG_SUKSES = "sukses";
    private static String TAG_IDMEMBER = "sukses";

    EditText txtItitenary;
    Button btnNewItitenary;

    String ID_Member, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ititenary);

        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        user = sharedPreferences.getString(UserKey, "");

        txtItitenary = (EditText)findViewById(R.id.txtItitenary2);
        btnNewItitenary = (Button)findViewById(R.id.btnInsertItitenary);

        btnNewItitenary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new CreateItitenary().execute();
            }
        });
    }

    class CreateItitenary extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String newItitenary = txtItitenary.getText().toString();

            List<NameValuePair> args = new ArrayList<NameValuePair>();
            args.add(new BasicNameValuePair("ID_Member", "1"));
            args.add(new BasicNameValuePair("Username_User",user ));
            args.add(new BasicNameValuePair("Isi", newItitenary));

            JSONObject json = jParser.makeHttpRequest(url_insertItitenary, "GET", args);
            try {
                int sukses = json.getInt(TAG_SUKSES);
                if(sukses == 1) {
                    Intent i = getIntent();
                    setResult(250);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_ititenary, menu);
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
}
