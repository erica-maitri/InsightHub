package com.example.insighthub;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpeedActivity extends AppCompatActivity {

    private static final int LOCATION_REQUEST = 200;

    private LocationHelper locationHelper;

    private TextView txtLatitude;
    private TextView txtLongitude;
    private TextView txtSpeed;
    private TextView txtWarning;

    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_speed);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtLatitude =
                findViewById(R.id.txtLatitude);

        txtLongitude =
                findViewById(R.id.txtLongitude);

        txtSpeed =
                findViewById(R.id.txtSpeed);

        txtWarning =
                findViewById(R.id.txtWarning);

        locationHelper =
                new LocationHelper(this);

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

        } else {

            startTracking();
        }
    }

    private void startTracking() {

        locationListener =
                new LocationListener() {

                    @Override
                    public void onLocationChanged(
                            Location location) {

                        txtLatitude.setText(
                                String.format(
                                        "Latitude: %.6f",
                                        location.getLatitude()
                                )
                        );

                        txtLongitude.setText(
                                String.format(
                                        "Longitude: %.6f",
                                        location.getLongitude()
                                )
                        );

                        float speed = 0;

                        if (location.hasSpeed()) {

                            speed =
                                    location.getSpeed() * 3.6f;
                        }

                        txtSpeed.setText(
                                String.format(
                                        "%.2f km/h",
                                        speed
                                )
                        );

                        if (speed > 40) {

                            txtWarning.setText(
                                    "⚠ WARNING: Speed exceeds 40 km/h"
                            );

                        } else {

                            txtWarning.setText(
                                    "✓ Speed is within 40 km/h"
                            );
                        }
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

            startTracking();
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