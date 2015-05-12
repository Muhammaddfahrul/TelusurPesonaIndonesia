package com.pplc9.telusurpesonaindonesia.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.pplc9.telusurpesonaindonesia.R;
import com.pplc9.telusurpesonaindonesia.util.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class showAllTaman extends ListActivity {

    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> tamanList;


    private static String url_select_all = "http://ppl-c09.cs.ui.ac.id/pehape/selectall.php";

    private static final String TAG_SUKSES = "sukses";
    private static final String TAG_TAMAN = "taman";
    private static final String TAG_ID = "ID";
    private static final String TAG_NAMA = "Nama";
    private static final String TAG_LOKASI = "Lokasi";
    private static final String TAG_DESKRIPSI = "Deskripsi";

    JSONArray taman = null;

    String namataman, lokasitaman, deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_taman);

        tamanList = new ArrayList<HashMap<String, String>>();

        new LoadAllTaman().execute();

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pid = ((TextView) view.findViewById(R.id.pid)).getText().toString();
                namataman =  ((TextView) view.findViewById(R.id.name)).getText().toString();

                Intent in = new Intent(getApplicationContext(), InfoTaman.class);
                in.putExtra(TAG_ID, pid);
                in.putExtra(TAG_NAMA, namataman);
                in.putExtra(TAG_LOKASI, lokasitaman);
                in.putExtra(TAG_DESKRIPSI, deskripsi);
                startActivity(in);
            }
        });
    }

    class LoadAllTaman extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> args = new ArrayList<NameValuePair>();

            JSONObject json = jParser.makeHttpRequest(url_select_all, "GET", args);
            try {
                int success = json.getInt(TAG_SUKSES);

                if(success == 1) {
                    taman = json.getJSONArray(TAG_TAMAN);

                    for(int i = 0; i <taman.length(); i++) {
                        JSONObject c = taman.getJSONObject(i);
                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAMA);
                        String lokasi = c.getString(TAG_LOKASI);
                        String deskripsi = c.getString(TAG_DESKRIPSI);

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_ID, id);
                        map.put(TAG_NAMA, name);

                        tamanList.add(map);
                    }
                } else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            } catch(JSONException e) {
                System.out.println("Error coba");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String url) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(
                            showAllTaman.this, tamanList,
                            R.layout.activity_list_item, new String[] {TAG_ID, TAG_NAMA},
                            new int[] {R.id.pid, R.id.name}
                    );
                    setListAdapter(adapter);
                }
            });
        }
    }
}
