package com.security.wifisecurityapp.app.view.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.security.wifisecurityapp.R;

/**
 * Created by zeina.oliveira
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
////        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////        this.getSupportActionBar().setIcon(R.color.green_pass);
//        getSupportActionBar().setTitle(R.string.about);
        setContentView(R.layout.about_activity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
