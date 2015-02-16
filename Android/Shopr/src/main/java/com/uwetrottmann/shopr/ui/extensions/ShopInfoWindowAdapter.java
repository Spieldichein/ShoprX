package com.uwetrottmann.shopr.ui.extensions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.uwetrottmann.shopr.R;

/**
 * Created by yannick on 16.02.15.
 *
 * This class is used to display enhanced shop information on click on a shop marker on the map.
 */
public class ShopInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private LayoutInflater mLayoutInflater;

    public ShopInfoWindowAdapter(LayoutInflater inflater){
        super();

        mLayoutInflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View v = mLayoutInflater.inflate(R.layout.info_window_popup_for_map, null);

        TextView title = (TextView) v.findViewById(R.id.popup_shopTitle);
        title.setText(marker.getTitle());

        TextView items_sold = (TextView) v.findViewById(R.id.popup_shopSellsXItems);
        TextView crowded = (TextView) v.findViewById(R.id.popup_shopCrowded);
        TextView open = (TextView) v.findViewById(R.id.popup_shopOpeningHoursToday);
        TextView distance = (TextView) v.findViewById(R.id.popup_shopDistance);

        String[] parts = marker.getSnippet().split("\n");

        items_sold.setText(parts[0]);
        crowded.setText(parts[1]);
        open.setText(parts[2]);
        distance.setText(parts[3]);

        return v;
    }
}
