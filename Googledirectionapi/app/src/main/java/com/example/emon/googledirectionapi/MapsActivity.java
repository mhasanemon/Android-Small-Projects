package com.example.emon.googledirectionapi;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String start_address;
    String end_address;
    String distance;
    String duration;
    Double sourceLat;
    Double sourceLng;
    Double destinationLat;
    Double destinationLng;

    LatLng source,destination;

    TextView distanceView;
    TextView durationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        start_address = getIntent().getStringExtra("start_address");
        end_address = getIntent().getStringExtra("end_address");
        distance = getIntent().getStringExtra("distance");
        duration = getIntent().getStringExtra("duration");
        sourceLat = getIntent().getDoubleExtra("sourceLat",0.0);
        sourceLng = getIntent().getDoubleExtra("sourceLng",0.0);
        destinationLat = getIntent().getDoubleExtra("destinationLat",0.0);
        destinationLng = getIntent().getDoubleExtra("destinationLng",0.0);

        source = new LatLng(sourceLat,sourceLng);
        destination = new LatLng(destinationLat,destinationLng);

        distanceView = (TextView) findViewById(R.id.distance);
        distanceView.setText("Distance : "+distance);

        durationView = (TextView) findViewById(R.id.duration);
        durationView.setText("Duration : "+duration);
        //Toast.makeText(this,start_address+"\n"+end_address+"\n"+distance+"\n"+duration+"\n"+source+"\n"+destination,Toast.LENGTH_SHORT).show();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(source).title(start_address));
        mMap.addMarker(new MarkerOptions().position(destination).title(end_address));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(source,17));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination,17));
    }
}
