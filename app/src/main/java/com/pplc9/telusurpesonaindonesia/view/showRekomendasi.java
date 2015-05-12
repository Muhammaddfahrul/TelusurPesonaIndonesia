package com.pplc9.telusurpesonaindonesia.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.pplc9.telusurpesonaindonesia.R;
import com.pplc9.telusurpesonaindonesia.util.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class showRekomendasi extends ActionBarActivity {

    private static String url = "http://ppl-c09.cs.ui.ac.id/pehape/selectRekomendasi.php";

    JSONParser jParser = new JSONParser();

    private static String TAG_ID = "ID";
    private static String TAG_SUKSES = "sukses";
    private static String TAG_REKOMENDASI = "rekomendasi";
    private static String TAG_ISI = "Isi";

    EditText txtRekomendasi;

    String asd, saran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rekomendasi);

        Intent i = getIntent();
        asd = i.getStringExtra(TAG_ID);

        txtRekomendasi = (EditText)findViewById(R.id.txtRekomendasi);
        txtRekomendasi.setKeyListener(null);
        txtRekomendasi.setEnabled(false);

         new GetRekomendasi().execute();
    }

    class GetRekomendasi extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> args = new ArrayList<NameValuePair>();
            args.add(new BasicNameValuePair("ID_Taman", asd));

            JSONObject json = jParser.makeHttpRequest(url, "GET", args);

            try {
                int sukses = json.getInt(TAG_SUKSES);
                if(sukses == 1) {
                    JSONArray rekomendasi = json.getJSONArray(TAG_REKOMENDASI);
                    JSONObject rekomen = rekomendasi.getJSONObject(0);
                    saran = rekomen.getString(TAG_ISI);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return saran;
        }

        protected void onPostExecute(String url) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtRekomendasi.setText(saran);
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_rekomendasi, menu);
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

