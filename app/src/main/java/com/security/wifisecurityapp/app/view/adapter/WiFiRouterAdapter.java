package com.security.wifisecurityapp.app.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.security.wifisecurityapp.R;
import com.security.wifisecurityapp.app.model.InfoWiFi;

import java.util.List;

/**
 * Created by zeina.oliveira on 19/08/2015.
 */
public class WiFiRouterAdapter extends ArrayAdapter<InfoWiFi> {

    private Context context;
    private static final int MIN_SECURITY = 70;
    private List<InfoWiFi> data;

    public WiFiRouterAdapter(Context context, List<InfoWiFi> infoWifi) {
        super(context, R.layout.wi_fi_list_item, infoWifi);

        this.context = context;
        this.data= infoWifi;
    }


//    @Override
//    public void notifyDataSetChanged() {
//      //  super.notifyDataSetChanged();
//
//        Collections.sort(data, new Comparator<InfoWiFi>() {
//
//            @Override
//            public int compare(final InfoWiFi lhs, final InfoWiFi rhs) {
//
//                int result = 0;
//
//                if (lhs.getSecurityofRouter() < rhs.getSecurityofRouter()) {
//                    result = 1;
//                } else if (lhs.getSecurityofRouter() > rhs.getSecurityofRouter()) {
//                    result = -1;
//                }
//                return result;
//            }
//        });
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        ViewHolder holder = new ViewHolder();

        if (convertView == null)
            convertView = inflater.inflate(R.layout.wi_fi_list_item, parent, false);


        holder.txtViewSSID = (TextView) convertView.findViewById(R.id.txtSSID);

        if (data.get(position).getSsid().isEmpty())
            holder.txtViewSSID.setText(R.string.hiddenSSID);
        else
            holder.txtViewSSID.setText(data.get(position).getSsid());

        holder.txtViewSecurity = (TextView) convertView.findViewById(R.id.txtSecurityofRouter);
        holder.txtViewSecurity.setText(data.get(position).getSecurityofRouter()+ "%");

        holder.image = (ImageView) convertView.findViewById(R.id.app_icon);

        if (data.get(position).getSecurityofRouter() > MIN_SECURITY) {
            holder.txtViewSecurity.setTextColor(Color.GREEN);
            holder.image.setBackgroundResource(R.drawable.lock_green);
        } else {
            holder.txtViewSecurity.setTextColor(Color.RED);
            holder.image.setBackgroundResource(R.drawable.lock_red);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView txtViewSSID;
        TextView txtViewSecurity;
        ImageView image;
    }

}