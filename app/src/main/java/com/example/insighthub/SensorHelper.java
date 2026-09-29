package com.example.insighthub;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorHelper implements SensorEventListener {

    private SensorManager sensorManager;

    private Sensor lightSensor;
    private Sensor temperatureSensor;
    private Sensor accelerometer;

    private float lightValue = 0;
    private float temperatureValue = 0;

    private float x = 0;
    private float y = 0;
    private float z = 0;

    public SensorHelper(Context context) {

        sensorManager =
                (SensorManager) context.getSystemService(
                        Context.SENSOR_SERVICE
                );

        lightSensor =
                sensorManager.getDefaultSensor(
                        Sensor.TYPE_LIGHT
                );

        temperatureSensor =
                sensorManager.getDefaultSensor(
                        Sensor.TYPE_AMBIENT_TEMPERATURE
                );

        accelerometer =
                sensorManager.getDefaultSensor(
                        Sensor.TYPE_ACCELEROMETER
                );
    }

    public boolean hasLightSensor() {
        return lightSensor != null;
    }

    public boolean hasTemperatureSensor() {
        return temperatureSensor != null;
    }

    public boolean hasAccelerometer() {
        return accelerometer != null;
    }

    public void startLightSensor() {

        if (lightSensor != null) {

            sensorManager.registerListener(
                    this,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL
            );
        }
    }

    public void startTemperatureSensor() {

        if (temperatureSensor != null) {

            sensorManager.registerListener(
                    this,
                    temperatureSensor,
                    SensorManager.SENSOR_DELAY_NORMAL
            );
        }
    }

    public void startAccelerometer() {

        if (accelerometer != null) {

            sensorManager.registerListener(
                    this,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_GAME
            );
        }
    }

    public void stopSensors() {
        sensorManager.unregisterListener(this);
    }

    public float getLightValue() {
        return lightValue;
    }

    public float getTemperatureValue() {
        return temperatureValue;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {

            lightValue = event.values[0];

        } else if (
                event.sensor.getType()
                        == Sensor.TYPE_AMBIENT_TEMPERATURE) {

            temperatureValue = event.values[0];

        } else if (
                event.sensor.getType()
                        == Sensor.TYPE_ACCELEROMETER) {

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
        }
    }

    @Override
    public void onAccuracyChanged(
            Sensor sensor,
            int accuracy) {
    }
}