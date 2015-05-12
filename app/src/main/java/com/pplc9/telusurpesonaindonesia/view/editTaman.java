package com.pplc9.telusurpesonaindonesia.view;

import android.content.Intent;
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

public class editTaman extends ActionBarActivity {

    EditText txtNamaEdit, txtLokasiEdit, txtDeskripsiEdit;
    Button btnSaveTaman, btnDeleteTaman;

    String id, nama, lokasi, deskripsi;

    JSONParser jParser = new JSONParser();

    private static String url_save = "http://ppl-c09.cs.ui.ac.id/pehape/editTaman.php";
    private static String url_delete = "http://ppl-c09.cs.ui.ac.id/pehape/deleteTaman.php";

    private static final String TAG_ID = "ID";
    private static final String TAG_NAMA = "Nama";
    private static final String TAG_LOKASI = "Lokasi";
    private static final String TAG_DESKRIPSI = "Deskripsi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_taman);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        txtNamaEdit = (EditText)findViewById(R.id.txtNamaEdit);
        txtLokasiEdit = (EditText)findViewById(R.id.txtLokasiEdit);
        txtDeskripsiEdit = (EditText)findViewById(R.id.txtDeskripsiEdit);

        btnSaveTaman = (Button)findViewById(R.id.btnSaveTaman);
        btnDeleteTaman = (Button)findViewById(R.id.btnDeleteTaman);

        Intent i = getIntent();
        id = i.getStringExtra(TAG_ID);
        nama = i.getStringExtra(TAG_NAMA);
        lokasi = i.getStringExtra(TAG_LOKASI);
        deskripsi = i.getStringExtra(TAG_DESKRIPSI);

        txtNamaEdit.setText(nama);
        txtLokasiEdit.setText(lokasi);
        txtDeskripsiEdit.setText(deskripsi);

        btnSaveTaman.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new UpdateTamanDetails().execute();
            }
        });

        btnDeleteTaman.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DeleteTaman().execute();
            }
        });

    }

    class UpdateTamanDetails extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }

    class DeleteTaman extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_taman, menu);
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
