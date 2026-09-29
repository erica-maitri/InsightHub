package com.example.insighthub;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AverageLightActivity extends AppCompatActivity {

    private SensorHelper sensorHelper;

    private TextView txtCurrent;
    private TextView txtAverage;
    private TextView txtCount;

    private ArrayList<Float> readings =
            new ArrayList<>();

    private Handler handler =
            new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_average_light
        );

        txtCurrent =
                findViewById(R.id.txtCurrent);

        txtAverage =
                findViewById(R.id.txtAverage);

        txtCount =
                findViewById(R.id.txtCount);

        sensorHelper =
                new SensorHelper(this);

        if (!sensorHelper.hasLightSensor()) {

            txtCurrent.setText(
                    "Light sensor unavailable"
            );

            txtAverage.setText("--");

        } else {

            sensorHelper.startLightSensor();

            startReading();
        }
    }

    private void startReading() {

        handler.postDelayed(
                new Runnable() {

                    @Override
                    public void run() {

                        float value =
                                sensorHelper.getLightValue();

                        readings.add(value);

                        if (readings.size() > 5) {

                            readings.remove(0);
                        }

                        calculateAverage();

                        handler.postDelayed(
                                this,
                                1000
                        );
                    }
                },
                1000
        );
    }

    private void calculateAverage() {

        float total = 0;

        for (float value : readings) {

            total += value;
        }

        float average = 0;

        if (readings.size() > 0) {

            average =
                    total / readings.size();
        }

        txtCurrent.setText(
                String.format(
                        "Current: %.2f lux",
                        sensorHelper.getLightValue()
                )
        );

        txtAverage.setText(
                String.format(
                        "%.2f lux",
                        average
                )
        );

        txtCount.setText(
                "Samples used: "
                        + readings.size()
                        + " / 5"
        );
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorHelper.stopSensors();

        handler.removeCallbacksAndMessages(null);
    }
}