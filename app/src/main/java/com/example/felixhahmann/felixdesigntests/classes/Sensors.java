package com.example.felixhahmann.felixdesigntests.classes;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class Sensors implements SensorEventListener
{

    //Einfach kopieren geht nicht, weil die andere App nur auf einer Act arbeitet


    @Override
    public void onSensorChanged(SensorEvent event)
    {

    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy)
    {

    }
}
