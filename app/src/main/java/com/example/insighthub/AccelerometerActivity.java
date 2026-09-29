package com.example.insighthub;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccelerometerActivity extends AppCompatActivity {

    private SensorHelper sensorHelper;

    private TextView txtX;
    private TextView txtY;
    private TextView txtZ;
    private TextView txtShake;

    private float previousX = 0;
    private float previousY = 0;
    private float previousZ = 0;

    private long lastShakeTime = 0;

    private Handler handler =
            new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_accelerometer
        );

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);
        txtShake = findViewById(R.id.txtShake);

        sensorHelper =
                new SensorHelper(this);

        if (!sensorHelper.hasAccelerometer()) {

            txtShake.setText(
                    "Accelerometer unavailable"
            );

        } else {

            sensorHelper.startAccelerometer();

            startMonitoring();
        }
    }

    private void startMonitoring() {

        handler.postDelayed(
                new Runnable() {

                    @Override
                    public void run() {

                        float x =
                                sensorHelper.getX();

                        float y =
                                sensorHelper.getY();

                        float z =
                                sensorHelper.getZ();

                        txtX.setText(
                                String.format(
                                        "X : %.2f m/s²",
                                        x
                                )
                        );

                        txtY.setText(
                                String.format(
                                        "Y : %.2f m/s²",
                                        y
                                )
                        );

                        txtZ.setText(
                                String.format(
                                        "Z : %.2f m/s²",
                                        z
                                )
                        );

                        float movement =
                                Math.abs(x - previousX)
                                        + Math.abs(y - previousY)
                                        + Math.abs(z - previousZ);

                        long currentTime =
                                System.currentTimeMillis();

                        if (movement > 12 &&
                                currentTime - lastShakeTime > 1000) {

                            txtShake.setText(
                                    "📳 SHAKE DETECTED!"
                            );

                            lastShakeTime =
                                    currentTime;

                        } else if (
                                currentTime - lastShakeTime > 1000) {

                            txtShake.setText(
                                    "Device is stable"
                            );
                        }

                        previousX = x;
                        previousY = y;
                        previousZ = z;

                        handler.postDelayed(
                                this,
                                100
                        );
                    }
                },
                100
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