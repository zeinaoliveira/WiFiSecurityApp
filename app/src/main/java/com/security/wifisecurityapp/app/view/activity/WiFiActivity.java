package com.security.wifisecurityapp.app.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.security.wifisecurityapp.R;
import com.security.wifisecurityapp.app.bo.WiFiInfoBO;
import com.security.wifisecurityapp.app.model.InfoWiFi;
import com.security.wifisecurityapp.app.view.adapter.WiFiRouterAdapter;

import java.util.ArrayList;
import java.util.List;

public class WiFiActivity extends AppCompatActivity {


    private ListView lstWifiRouter;
    private InfoWiFi wifiInfo;
    private List<InfoWiFi> wifiList;
    private WifiManager wifiManager;
    private WiFiInfoBO wifiInfoBO;
    private WiFiRouterAdapter adapter;
    private AlertDialog informationDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * Initialize objects.
     */
    private void initialize() {

        lstWifiRouter = (ListView) findViewById(R.id.list_wifiRouter);

        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

        wifiInfoBO = new WiFiInfoBO();
        wifiList = new ArrayList<>();

        informationDialog = new AlertDialog.Builder(this).create();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.blue_high, R.color.red_high, R.color.green_pass);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);
        getSupportActionBar().setIcon(R.drawable.icon_navdrawer);
        initialize();
        createListRouters();

        lstWifiRouter.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                InfoWiFi infoWifi = (InfoWiFi) parent.getAdapter().getItem(position);
                setWiFiInformationDialog(infoWifi);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                refreshContent();

            }
        });
    }

    private void refreshContent() {
        wifiList.clear();
        createListRouters();
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this,R.string.refreshed, Toast.LENGTH_SHORT).show();
    }


    /**
     * Show informations about network in a dialog.
     */
    private void setWiFiInformationDialog(InfoWiFi infoWifi) {
        String title;

        informationDialog.setTitle(R.string.network_information);

        if (infoWifi.getSsid().isEmpty())
            title = "• " + getString(R.string.ssid)+ ": " + getString (R.string.hiddenSSID) + "\n\n";
        else
            title = "• " + getString(R.string.ssid)+ ": " + infoWifi.getSsid() + "\n\n";

        title += 	"• " + getString(R.string.frequency) + ": " + infoWifi.getFrequency() + " MHz\n\n" +
                "• " + getString(R.string.capabilities) + ": "  + infoWifi.getCapabilities() + "\n\n" +
                "• " + getString(R.string.security_level) + ": " + infoWifi.getSecurityofRouter() + "%\n";

        informationDialog.setMessage(title);
        informationDialog.show();
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
            case R.id.action_about:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAbout() {
        Intent intent = new Intent (WiFiActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    private void createListRouters() {
        //TODO INCLUIR TRATAMENTO PARA AGUARDAR TERMINO DA PESQUISA E DISPONIBILIDADE DA INTERNET
        if (wifiManager.getScanResults().size() > 0) {

            for (int i = 0; i < wifiManager.getScanResults().size(); i++) {
                wifiInfo = new InfoWiFi();
                wifiInfo.setCapabilities(wifiManager.getScanResults().get(i).capabilities);
                wifiInfo.setSsid(wifiManager.getScanResults().get(i).SSID);
                wifiInfo.setFrequency(wifiManager.getScanResults().get(i).frequency);
                wifiInfo.setSecurityofRouter(wifiInfoBO.getSecurityofRouter(wifiInfo));
                wifiList.add(wifiInfo);

                adapter = new WiFiRouterAdapter(this, wifiList);
                lstWifiRouter.setAdapter(adapter);
            }
        } else {
            //TODO incluir tratamento
        }
    }
}
