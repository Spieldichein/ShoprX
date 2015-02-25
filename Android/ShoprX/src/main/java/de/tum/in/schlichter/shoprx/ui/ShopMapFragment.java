
package de.tum.in.schlichter.shoprx.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import de.tum.in.schlichter.shoprx.R;

import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import de.tum.in.schlichter.androidutils.Lists;
import de.tum.in.schlichter.shoprx.ShoprApp;
import de.tum.in.schlichter.shoprx.context.model.DistanceToShop;
import de.tum.in.schlichter.shoprx.context.model.ScenarioContext;
import de.tum.in.schlichter.shoprx.loaders.ShopLoader;
import de.tum.in.schlichter.shoprx.model.Shop;
import de.tum.in.schlichter.shoprx.settings.AppSettings;
import de.tum.in.schlichter.shoprx.ui.extensions.ShopInfoWindowAdapter;

public class ShopMapFragment extends SupportMapFragment implements LoaderCallbacks<List<Shop>> {

    private static final int RADIUS_METERS = 2000;
    private static final int ZOOM_LEVEL_INITIAL = 14;
    public static final String TAG = "Shops Map";
    private static final int LOADER_ID = 22;

    public static ShopMapFragment newInstance() {
        return new ShopMapFragment();
    }

    private List<Marker> mShopMarkers;
    private boolean mIsInitialized;
    private Map<Integer, Integer> mShopsWithItems;
    private ShopInfoWindowAdapter mInfoWindowAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mIsInitialized = false;

        mInfoWindowAdapter = new ShopInfoWindowAdapter(getLayoutInflater(savedInstanceState));
        // enable my location feature
        getMap().setMyLocationEnabled(!AppSettings.isUsingFakeLocation(getActivity()));

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this, MainActivity.LocationUpdateEvent.class, ItemListFragment.ShopUpdateEvent.class);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void onInitializeMap() {
        if (!mIsInitialized) {
            Log.d(TAG, "Initializing map.");
            getMap().clear();

            LatLng userPosition = ShoprApp.getLastLocation();
            if (userPosition == null) {
                return;
            }

            //Log.d("User location", ""+ userPosition);

            //Draw a circle for current position, when fake location is active
            if (AppSettings.isUsingFakeLocation(getActivity())){
                getMap().addCircle(new CircleOptions()
                        .center(userPosition)
                        .radius(10)
                        .strokeColor(getResources().getColor(R.color.blue))
                        .strokeWidth(4)
                        .fillColor(getResources().getColor(R.color.blue))
                        .zIndex(200));
            }

            // move camera to current position
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(userPosition, ZOOM_LEVEL_INITIAL));

            int radius = getRadius();

            // draw a circle around it
            getMap().addCircle(new CircleOptions()
                    .center(userPosition)
                    .radius(radius)
                    .strokeColor(getResources().getColor(R.color.lilac))
                    .strokeWidth(4)
                    .fillColor(getResources().getColor(R.color.lilac_transparent)));

            mIsInitialized = true;
        }
    }

    /**
     * Retrieves the radius currently specified by the person.
     * @return the radius in meters.
     */
    private int getRadius(){
        //Check whether we have a context scenario and draw the line accordingly.
        ScenarioContext context = ScenarioContext.getInstance();
        DistanceToShop distanceToShop = context.getDistanceToShop();
        if (distanceToShop != null){
            return (int) distanceToShop.getDistance();
        }

        return RADIUS_METERS;
    }

    private void onUpdateShops(List<Shop> shops) {
        ShoprApp.setShopList(shops);

        // remove existing markers
        if (mShopMarkers != null) {
            for (Marker marker : mShopMarkers) {
                marker.remove();
            }
        }

        getMap().setInfoWindowAdapter(mInfoWindowAdapter);

        List<Marker> shopMarkersNew = Lists.newArrayList();

        for (Shop shop : shops) {
            // determine color and recommended items in this shop
            float color;
            int itemCount;
            if (mShopsWithItems != null && mShopsWithItems.containsKey(shop.id())) {
                itemCount = mShopsWithItems.get(shop.id());
                color = BitmapDescriptorFactory.HUE_VIOLET;
            } else {
                itemCount = 0;
                color = BitmapDescriptorFactory.HUE_AZURE;
            }


            StringBuilder snippetBuilder = new StringBuilder(getString(R.string.has_x_recommendations, itemCount));
            snippetBuilder.append("\n");
            if (shop.isUsuallyCrowded()){
                snippetBuilder.append(getString(R.string.crowded));
            } else {
                snippetBuilder.append(getString(R.string.not_crowded));
            }

            snippetBuilder.append("\n");
            snippetBuilder.append(shop.openToday());

            snippetBuilder.append("\n");
            snippetBuilder.append(ShoprApp.getDistanceToCurrentLocationInKmAsString(shop.getLocationObject()));

            // place marker
            Marker marker = getMap().addMarker(
                    new MarkerOptions()
                            .position(shop.location())
                            .title(shop.name())
                            .snippet(snippetBuilder.toString())
                            .icon(BitmapDescriptorFactory.defaultMarker(color)));
            shopMarkersNew.add(marker);
        }

        mShopMarkers = shopMarkersNew;
    }

    public void onEvent(MainActivity.LocationUpdateEvent event) {
        onInitializeMap();
    }

    public void onEvent(ItemListFragment.ShopUpdateEvent event) {
        mShopsWithItems = event.shopMap;
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Shop>> onCreateLoader(int loaderId, Bundle args) {
        return new ShopLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Shop>> loader, List<Shop> data) {
        onUpdateShops(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Shop>> loader) {
    }

}
