package com.example.emon.apphikers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    protected String provider;

    TextView latView;
    TextView lngView;
    TextView accuracyView;
    TextView speedView;
    TextView bearingView;
    TextView altitudeView;
    TextView addressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latView = (TextView) findViewById(R.id.lat);
        lngView = (TextView) findViewById(R.id.lng);
        accuracyView = (TextView) findViewById(R.id.accuracy);
        speedView = (TextView) findViewById(R.id.speed);
        bearingView = (TextView) findViewById(R.id.bearing);
        altitudeView = (TextView) findViewById(R.id.altitude);
        addressView = (TextView) findViewById(R.id.address);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
        /*provider = locationManager.getBestProvider(new Criteria(),false);
        Location location = locationManager.getLastKnownLocation(provider);
        onLocationChanged(location);*/
    }

    @Override
    protected void onPause() {
        super.onPause();

        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Double lat = location.getLatitude();
        Double lng = location.getLongitude();
        Double alt = location.getAltitude();
        float bearing = location.getBearing();
        float speed = location.getSpeed();
        float accuracy = location.getAccuracy();

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String addressHolder = "";

            if(addressList!= null && addressList.size()>0){
                //Log.i("PlaceInfo",addressList.get(0).toString());
                for (int i=0;i<addressList.get(0).getMaxAddressLineIndex();i++){
                    addressHolder += addressList.get(0).getAddressLine(i);
                }
                addressView.setText("Address :\n"+addressHolder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        latView.setText("Latitude: "+lat.toString());
        lngView.setText("Longitude: "+lng.toString());
        altitudeView.setText("Altitude: "+alt.toString()+"m");
        bearingView.setText("Bearing: "+String.valueOf(bearing));
        speedView.setText("Speed: "+String.valueOf(speed)+"m/s");
        accuracyView.setText("Accuracy: "+String.valueOf(accuracy)+"m");

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}
