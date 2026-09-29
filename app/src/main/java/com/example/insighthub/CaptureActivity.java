package com.example.insighthub;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CaptureActivity extends AppCompatActivity {

    private static final int LOCATION_REQUEST = 300;

    private SensorHelper sensorHelper;
    private LocationHelper locationHelper;

    private TextView txtReading;

    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_capture);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtReading =
                findViewById(R.id.txtReading);

        Button btnCapture =
                findViewById(R.id.btnCapture);

        sensorHelper =
                new SensorHelper(this);

        locationHelper =
                new LocationHelper(this);

        sensorHelper.startLightSensor();
        sensorHelper.startTemperatureSensor();

        btnCapture.setOnClickListener(v ->
                captureReading()
        );
    }

    private void captureReading() {

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

        getOneLocationForSnapshot();
    }

    private void getOneLocationForSnapshot() {

        txtReading.setText(
                "Getting current reading..."
        );

        locationListener =
                new LocationListener() {

                    @Override
                    public void onLocationChanged(
                            Location location) {

                        createSnapshot(location);

                        // Stop immediately.
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

    private void createSnapshot(Location location) {

        double latitude =
                location.getLatitude();

        double longitude =
                location.getLongitude();

        float light =
                sensorHelper.getLightValue();

        String temperature;

        if (sensorHelper.hasTemperatureSensor()) {

            temperature =
                    String.format(
                            Locale.getDefault(),
                            "%.2f °C",
                            sensorHelper.getTemperatureValue()
                    );

        } else {

            temperature = "Unavailable";
        }

        String dateTime =
                new SimpleDateFormat(
                        "dd MMM yyyy, hh:mm:ss a",
                        Locale.getDefault()
                ).format(new Date());

        String result =
                "✦  CAPTURED READING\n\n" +

                        "📍 LOCATION\n" +
                        "Latitude: " +
                        String.format(
                                Locale.getDefault(),
                                "%.6f",
                                latitude
                        ) +
                        "\nLongitude: " +
                        String.format(
                                Locale.getDefault(),
                                "%.6f",
                                longitude
                        ) +

                        "\n\n☀ LIGHT\n" +
                        String.format(
                                Locale.getDefault(),
                                "%.2f lux",
                                light
                        ) +

                        "\n\n🌡 TEMPERATURE\n" +
                        temperature +

                        "\n\n🕒 CAPTURED AT\n" +
                        dateTime;

        txtReading.setText(result);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
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

            getOneLocationForSnapshot();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorHelper.stopSensors();

        if (locationListener != null) {

            locationHelper.stopUpdates(
                    locationListener
            );
        }
    }
}