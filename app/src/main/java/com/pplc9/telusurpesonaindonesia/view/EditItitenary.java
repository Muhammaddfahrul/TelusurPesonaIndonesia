package com.pplc9.telusurpesonaindonesia.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pplc9.telusurpesonaindonesia.R;
import com.pplc9.telusurpesonaindonesia.util.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EditItitenary extends ActionBarActivity {

    JSONParser jParser = new JSONParser();

    private static final String url_edit = "http://ppl-c09.cs.ui.ac.id/pehape/editItitenary.php";
    private static final String url_delete="http://ppl-c09.cs.ui.ac.id/pehape/deleteItitenary.php";

    SharedPreferences sharedPreferences;
    public static final String LogKey = "LogKey";
    public static final String UserKey = "UserKey";
    public static final String MyPref = "MyPref";

    private static final String TAG_SUKSES = "sukses";
    private static final String TAG_ITITENARY = "Ititenary";
    private static final String TAG_NOMOR = "Nomor";
    private static final String TAG_ISI = "Isi";

    Button btnSaveItitenary;
    EditText txtItitenary;

    String nomor, username, isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_itinerary);

        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(UserKey, "");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        txtItitenary = (EditText)findViewById(R.id.txtItitenary);
        btnSaveItitenary = (Button)findViewById(R.id.btnSaveItitenary);

        Intent i = getIntent();
        nomor = i.getStringExtra(TAG_NOMOR);
        isi= i.getStringExtra(TAG_ISI);

        txtItitenary.setText(isi);

        btnSaveItitenary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 new SaveItitenary().execute();
            }
        });
    }

    class SaveItitenary extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String ititenaryText = txtItitenary.getText().toString();

            List<NameValuePair> args = new ArrayList<NameValuePair>();
            args.add(new BasicNameValuePair("Nomor", nomor));
            args.add(new BasicNameValuePair("Username_User", username));
            args.add(new BasicNameValuePair("Isi", ititenaryText));

            JSONObject json = jParser.makeHttpRequest(url_edit, "GET", args);
            try {
                int sukses = json.getInt(TAG_SUKSES);
                if(sukses == 1) {
                    Intent i = getIntent();
                    setResult(100, i);
                    finish();
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class DeleteItitenary extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> args = new ArrayList<NameValuePair>();
            args.add(new BasicNameValuePair("Nomor", nomor));
            args.add(new BasicNameValuePair("Username_User", username));

            JSONObject json = jParser.makeHttpRequest(url_delete, "GET", args);
            try {
                int sukses = json.getInt(TAG_SUKSES);
                if(sukses == 1) {
                    Intent i = getIntent();
                    setResult(101, i);
                    finish();
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_itinerary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_deleteItitenary:
                new DeleteItitenary().execute();

        }

        return super.onOptionsItemSelected(item);
    }
}
