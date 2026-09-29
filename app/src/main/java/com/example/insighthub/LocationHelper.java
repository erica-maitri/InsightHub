package com.example.insighthub;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;

public class LocationHelper {

    private LocationManager locationManager;

    public LocationHelper(Activity activity) {

        locationManager =
                (LocationManager) activity.getSystemService(
                        Activity.LOCATION_SERVICE
                );
    }

    public boolean hasPermission(Activity activity) {

        return activity.checkSelfPermission(
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED;
    }

    public void startUpdates(
            Activity activity,
            LocationListener listener) {

        if (!hasPermission(activity)) {
            return;
        }

        try {

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1,
                    listener
            );

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void stopUpdates(
            LocationListener listener) {

        try {

            locationManager.removeUpdates(listener);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}