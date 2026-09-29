package com.example.insighthub;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LightActivity extends AppCompatActivity {

    private SensorHelper sensorHelper;

    private TextView txtLight;
    private TextView txtEnvironment;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_light);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtLight = findViewById(R.id.txtLight);
        txtEnvironment =
                findViewById(R.id.txtEnvironment);

        sensorHelper = new SensorHelper(this);

        if (!sensorHelper.hasLightSensor()) {

            txtLight.setText("Unavailable");

            txtEnvironment.setText(
                    "Ambient light sensor is not available."
            );

        } else {

            sensorHelper.startLightSensor();

            updateReading();
        }
    }

    private void updateReading() {

        float value =
                sensorHelper.getLightValue();

        txtLight.setText(
                String.format(
                        "%.2f lux",
                        value
                )
        );

        if (value < 100) {

            txtEnvironment.setText(
                    "🌙 Dark Environment"
            );

        } else if (value < 1000) {

            txtEnvironment.setText(
                    "☀ Normal Light"
            );

        } else {

            txtEnvironment.setText(
                    "🔆 Bright Environment"
            );
        }

        handler.postDelayed(
                this::updateReading,
                500
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