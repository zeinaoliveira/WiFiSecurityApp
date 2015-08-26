package com.security.wifisecurityapp.app.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import com.security.wifisecurityapp.R;

public class WiFiActivity extends AppCompatActivity {

    private TextView txtEmptyList;

    private ListView lstWifiRouter;

    private void initialize(){
        txtEmptyList = (TextView) findViewById(R.id.txtEmptyList);
        lstWifiRouter = (ListView) findViewById(R.id.list_wifiRouter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wi_fi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.refresh:
                //   refresh();
                return true;
            case R.id.action_about:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAbout() {
        Intent intent = new Intent (WiFiActivity.this, AboutActivity.class);
        startActivity(intent);
    }
}
