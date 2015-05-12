package com.pplc9.telusurpesonaindonesia.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pplc9.telusurpesonaindonesia.R;
import com.pplc9.telusurpesonaindonesia.util.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InfoTaman extends ActionBarActivity {

    private static String url_get_detail = "http://ppl-c09.cs.ui.ac.id/pehape/selectTamanDetails.php";
    JSONParser jParser = new JSONParser();

    String nama, lokasi, deskripsi;
    String id;
    TextView namaTaman;
    TextView lokasiTaman;
    EditText deskripsiTaman;
    ImageView imageViewTaman;
    ImageButton btnGaleri, btnRekomendasi;

    private static final String TAG_SUKSES = "sukses";
    private static final String TAG_ID = "ID";
    private static final String TAG_NAMA = "Nama";
    private static final String TAG_LOKASI = "Lokasi";
    private static final String TAG_DESKRIPSI = "Deskripsi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_taman);

        System.out.println("Masuk Halaman Info Taman");

        Intent in = getIntent();

        namaTaman = (TextView)findViewById(R.id.namaTaman);
        lokasiTaman = (TextView)findViewById(R.id.lokasiTaman);
        deskripsiTaman = (EditText)findViewById(R.id.deskripsiTaman);

        imageViewTaman = (ImageView)findViewById(R.id.imageViewTaman);

        id = in.getStringExtra(TAG_ID);
        nama = in.getStringExtra(TAG_NAMA);
        lokasi = in.getStringExtra(TAG_LOKASI);
        deskripsi = in.getStringExtra(TAG_DESKRIPSI);

        namaTaman.setText(nama);

        if(lokasi == null && deskripsi == null) {
            new getTamanDetails().execute();
        } else {
            lokasiTaman.setText(lokasi);
            deskripsiTaman.setText(deskripsi);
        }

        switch(id) {
            case "1":
                imageViewTaman.setImageResource(R.drawable.taman1);
                break;
            case "2":
                imageViewTaman.setImageResource(R.drawable.taman2);
                break;
            case "3":
                imageViewTaman.setImageResource(R.drawable.taman3);
                break;
            case "4":
                imageViewTaman.setImageResource(R.drawable.taman4);
                break;
            case "5":
                imageViewTaman.setImageResource(R.drawable.taman5);
                break;
            case "6":
                imageViewTaman.setImageResource(R.drawable.taman6);
                break;
            case "7":
                imageViewTaman.setImageResource(R.drawable.taman7);
                break;
            case "8":
                imageViewTaman.setImageResource(R.drawable.taman8);
                break;
            case "9":
                imageViewTaman.setImageResource(R.drawable.taman9);
                break;
            case "10":
                imageViewTaman.setImageResource(R.drawable.taman10);
                break;
            case "11":
                imageViewTaman.setImageResource(R.drawable.taman11);
                break;
            case "12":
                imageViewTaman.setImageResource(R.drawable.taman12);
                break;
            case "13":
                imageViewTaman.setImageResource(R.drawable.taman13);
                break;
        }

        btnGaleri = (ImageButton)findViewById(R.id.btnGaleri);
        btnRekomendasi = (ImageButton)findViewById(R.id.btnRekomendasi);

        btnGaleri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showGaleri.class);
                i.putExtra(TAG_ID, id);
                startActivity(i);
            }
        });

        btnRekomendasi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), showRekomendasi.class);
                i.putExtra(TAG_ID, id);
                startActivity(i);
            }
        });

    }

    class getTamanDetails extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> args = new ArrayList<NameValuePair>();
            args.add(new BasicNameValuePair("ID", id));

            JSONObject json = jParser.makeHttpRequest(url_get_detail, "GET", args);
            try {
                int sukses = json.getInt(TAG_SUKSES);
                if(sukses == 1) {
                    lokasi = json.getString(TAG_LOKASI);
                    deskripsi = json.getString(TAG_DESKRIPSI);
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String url) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    lokasiTaman.setText(lokasi);
                    deskripsiTaman.setText(deskripsi);
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_taman, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.action_itinerary:
                Intent i = new Intent(getApplicationContext(), ititenaryManager.class);
                startActivity(i);
                break;
            case R.id.action_favorite:
                break;
            case R.id.action_forum:
                break;
            case R.id.action_catatan:
                break;
            case R.id.action_edit:
                Intent in2 = new Intent(getApplicationContext(), editTaman.class);
                in2.putExtra(TAG_ID, id);
                in2.putExtra(TAG_NAMA, nama);
                in2.putExtra(TAG_LOKASI, lokasi);
                in2.putExtra(TAG_DESKRIPSI, deskripsi);
                startActivityForResult(in2, 100);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if(resultcode == 100) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
}
