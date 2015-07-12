package com.example.michael.mapsmulti;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;


public class MainActivity extends Activity implements GoogleMap.OnCameraChangeListener {

    private GoogleMap map1;
    private GoogleMap map2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MapFragment) getFragmentManager().findFragmentById(R.id.map1)).getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map1 = googleMap;
                map1.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                map1.setOnCameraChangeListener(MainActivity.this);

                ((MapFragment) getFragmentManager().findFragmentById(R.id.map2)).getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        map2 = googleMap;
                        map2.getUiSettings().setAllGesturesEnabled(false);
                        map2.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                        //CameraUpdate update = CameraUpdateFactory.newLatLngZoom(map1.getCameraPosition().target, 4);
                        //map2.moveCamera(update);

                        CameraPosition cameraPos = new CameraPosition.Builder().target(map1.getCameraPosition().target).zoom(8).build();
                        CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPos);
                        map2.moveCamera(update);
                    }
                });
            }
        });
    }


    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        CameraUpdate update = CameraUpdateFactory.newLatLng(cameraPosition.target);
        map2.animateCamera(update);
    }
}
