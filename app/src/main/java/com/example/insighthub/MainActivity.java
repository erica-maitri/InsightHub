package com.example.insighthub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLight;
    private Button btnTemperature;
    private Button btnLocation;
    private Button btnSpeed;
    private Button btnCapture;
    private Button btnAverage;
    private Button btnAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnLight = findViewById(R.id.btnLight);
        btnTemperature = findViewById(R.id.btnTemperature);
        btnLocation = findViewById(R.id.btnLocation);
        btnSpeed = findViewById(R.id.btnSpeed);
        btnCapture = findViewById(R.id.btnCapture);
        btnAverage = findViewById(R.id.btnAverage);
        btnAccelerometer =
                findViewById(R.id.btnAccelerometer);

        btnLight.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                LightActivity.class
                        )
                )
        );

        btnTemperature.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                TemperatureActivity.class
                        )
                )
        );

        btnLocation.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                LocationActivity.class
                        )
                )
        );

        btnSpeed.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                SpeedActivity.class
                        )
                )
        );

        btnCapture.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                CaptureActivity.class
                        )
                )
        );

        btnAverage.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                AverageLightActivity.class
                        )
                )
        );

        btnAccelerometer.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                AccelerometerActivity.class
                        )
                )
        );
    }
}