package com.pplc9.telusurpesonaindonesia.view;

import android.content.Context;
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
import java.util.jar.Attributes;

public class kirimFeedback extends ActionBarActivity {

    JSONParser jsonParser = new JSONParser();
    EditText txtSaran;
    Button btnConfirmFeedback;

    public static final String LogKey = "LogKey";
    public static final String UserKey = "UserKey";
    public static final String MyPref = "MyPref";

    SharedPreferences sharedPreferences;

    private static String url = "http://ppl-c09.cs.ui.ac.id/pehape/insertFeedback.php";

    private static String TAG_SUKSES = "sukses";
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirim_feedback);

        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        user = sharedPreferences.getString(UserKey, "");

        txtSaran = (EditText) findViewById(R.id.txtSaran);

        btnConfirmFeedback = (Button) findViewById(R.id.btnConfFeedback);
        btnConfirmFeedback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new SendFeedback().execute();
                Toast.makeText(getApplicationContext(), "Feedback anda telah terkirim", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kirim_feedback, menu);
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

    class SendFeedback extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String saran = txtSaran.getText().toString();

            List<NameValuePair> args = new ArrayList<NameValuePair>();
            args.add(new BasicNameValuePair("Username", user));
            args.add(new BasicNameValuePair("Saran", saran));

            JSONObject json = jsonParser.makeHttpRequest(url, "GET", args);
            try {
                int sukses = json.getInt(TAG_SUKSES);
                if(sukses == 1) {
                    System.out.println("Saran anda telah terkirim");
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
