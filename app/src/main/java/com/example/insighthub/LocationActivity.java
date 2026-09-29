package com.example.insighthub;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {

    private static final int LOCATION_REQUEST = 100;

    private LocationHelper locationHelper;

    private TextView txtLatitude;
    private TextView txtLongitude;

    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtLatitude =
                findViewById(R.id.txtLatitude);

        txtLongitude =
                findViewById(R.id.txtLongitude);

        Button btnGetLocation =
                findViewById(R.id.btnGetLocation);

        locationHelper =
                new LocationHelper(this);

        btnGetLocation.setOnClickListener(v ->
                getLocation()
        );
    }

    private void getLocation() {

        if (checkSelfPermission(
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_REQUEST
            );

            return;
        }

        startOneLocationReading();
    }

    private void startOneLocationReading() {

        locationListener =
                new LocationListener() {

                    @Override
                    public void onLocationChanged(
                            Location location) {

                        txtLatitude.setText(
                                String.format(
                                        "Latitude\n%.6f",
                                        location.getLatitude()
                                )
                        );

                        txtLongitude.setText(
                                String.format(
                                        "Longitude\n%.6f",
                                        location.getLongitude()
                                )
                        );

                        // We only need ONE location.
                        locationHelper.stopUpdates(
                                this
                        );
                    }

                    @Override
                    public void onProviderEnabled(
                            String provider) {
                    }

                    @Override
                    public void onProviderDisabled(
                            String provider) {
                    }

                    @Override
                    public void onStatusChanged(
                            String provider,
                            int status,
                            Bundle extras) {
                    }
                };

        locationHelper.startUpdates(
                this,
                locationListener
        );
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );

        if (requestCode == LOCATION_REQUEST &&
                grantResults.length > 0 &&
                grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {

            startOneLocationReading();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (locationListener != null) {
            locationHelper.stopUpdates(
                    locationListener
            );
        }
    }
}