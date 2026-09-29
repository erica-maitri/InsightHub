package com.example.insighthub;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TemperatureActivity extends AppCompatActivity {

    private SensorHelper sensorHelper;

    private TextView txtTemperature;
    private TextView txtMessage;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_temperature);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtTemperature =
                findViewById(R.id.txtTemperature);

        txtMessage =
                findViewById(R.id.txtMessage);

        sensorHelper = new SensorHelper(this);

        if (!sensorHelper.hasTemperatureSensor()) {

            txtTemperature.setText("-- °C");

            txtMessage.setText(
                    "Ambient temperature sensor is unavailable on this device."
            );

        } else {

            sensorHelper.startTemperatureSensor();

            updateTemperature();
        }
    }

    private void updateTemperature() {

        float temperature =
                sensorHelper.getTemperatureValue();

        txtTemperature.setText(
                String.format(
                        "%.2f °C",
                        temperature
                )
        );

        handler.postDelayed(
                this::updateTemperature,
                1000
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorHelper.stopSensors();

        handler.removeCallbacksAndMessages(null);
    }
}