package com.example.activity6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mainapplicationtp2.R;

public class Activity6 extends AppCompatActivity {

    private SensorManager sensorManager;
    private SensorEventListener proximityListener;
    Sensor sProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
        getSupportActionBar().hide();

        TextView proximityText=findViewById(R.id.proximityText);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sProximity=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        proximityListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float proximityValue = event.values[0];

                if (proximityValue < sProximity.getMaximumRange()) {
                    proximityText.setText("Objet proche");
                } else {
                    proximityText.setText("Objet loin");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(proximityListener, sProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(proximityListener);
    }
}