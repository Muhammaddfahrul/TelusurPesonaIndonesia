package com.pplc9.telusurpesonaindonesia.view;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

public class ititenaryManager extends ListActivity {

    JSONParser jParser = new JSONParser();

    private static String url_get_ititenary = "http://ppl-c09.cs.ui.ac.id/pehape/selectItitenary.php";

    ArrayList<HashMap<String, String>> ititenaryList;

    private static final String TAG_SUKSES = "sukses";
    private static final String TAG_ITITENARY = "Ititenary";
    private static final String TAG_NOMOR = "Nomor";
    private static final String TAG_ISI = "Isi";
    private static final String TAG_IDMEMBER = "ID_Member";

    JSONArray ititenary = null;

    SharedPreferences sharedPreferences;
    public static final String LogKey = "LogKey";
    public static final String UserKey = "UserKey";
    public static final String MyPref = "MyPref";
    public static final String IDMemberKey = "IDMemberKey";

    String user, ID_Member, idmember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_manager);

        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        user = sharedPreferences.getString(UserKey, "");
        System.out.println("User yang terdaftar: " + user);
        ititenaryList = new ArrayList<HashMap<String, String>>();

        new LoadItitenary().execute();

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomor = ((TextView) view.findViewById(R.id.pid2)).getText().toString();
                String isi = ((TextView) view.findViewById(R.id.name2)).getText().toString();

                Intent in = new Intent(getApplicationContext(), EditItitenary.class);
                in.putExtra(TAG_NOMOR, nomor);
                in.putExtra(TAG_ISI, isi);
                startActivityForResult(in, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Ititenary sudah diedit", Toast.LENGTH_SHORT).show();
        } else if (resultCode == 101) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Ititenary sudah dihapus", Toast.LENGTH_SHORT).show();
        } else if(resultCode == 250) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Ititenary sudah dibuat", Toast.LENGTH_SHORT).show();
        }
    }

    class LoadItitenary extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            List<NameValuePair> args = new ArrayList<NameValuePair>();
            args.add(new BasicNameValuePair("Username_User", user));

            JSONObject json = jParser.makeHttpRequest(url_get_ititenary, "GET", args);
            try {
                int sukses = json.getInt(TAG_SUKSES);

                if(sukses == 1) {
                    ititenary = json.getJSONArray(TAG_ITITENARY);

                    for(int i = 0; i <ititenary.length(); i++) {
                        JSONObject c = ititenary.getJSONObject(i);
                        String nomor = c.getString(TAG_NOMOR);
                        String isi = c.getString(TAG_ISI);

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_NOMOR, nomor);
                        map.put(TAG_ISI, isi);

                        ititenaryList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String url) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(
                            ititenaryManager.this, ititenaryList,
                            R.layout.list_item_2, new String[] { TAG_NOMOR,
                            TAG_ISI},
                            new int[] { R.id.pid2, R.id.name2 });
                    setListAdapter(adapter);
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intent_manager, menu);
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
            case R.id.action_addItitenary:
                Intent i = new Intent(getApplicationContext(), addItitenary.class);
                i.putExtra(TAG_IDMEMBER, ID_Member);
                startActivityForResult(i, 100);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
