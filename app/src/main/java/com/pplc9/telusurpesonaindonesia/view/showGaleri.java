package com.pplc9.telusurpesonaindonesia.view;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.pplc9.telusurpesonaindonesia.R;
import com.pplc9.telusurpesonaindonesia.util.GridViewAdapter;
import com.pplc9.telusurpesonaindonesia.util.ImageItem;

import java.util.ArrayList;

public class showGaleri extends ActionBarActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    private static final String TAG_ID = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_galeri);

        Intent i = getIntent();
        String pid = i.getStringExtra(TAG_ID);

        gridView = (GridView)findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item, getData(pid));
        gridView.setAdapter(gridAdapter);
    }

    private ArrayList<ImageItem> getData(String id) {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        int pid = Integer.parseInt(id);
        for (int i = 3*(pid-1); i < 3*(pid); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, "Galeri " + i));
        }
        return imageItems;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_galeri, menu);
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
