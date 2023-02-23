package com.example.activity3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mainapplicationtp2.R;

import java.util.Arrays;
import java.util.List;

public class Activity3 extends AppCompatActivity {

    SensorManager sensorManager;

    SensorEventListener accelerometreListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        getSupportActionBar().hide();

        LinearLayout coloredLayout=findViewById(R.id.layoutColored);

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sAccelerometre=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        TextView accuracyText=findViewById(R.id.accuracyText);
        accelerometreListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                float thresholdLow = 2.5f;
                float thresholdHigh = 5.0f;

                if (Math.abs(x) < thresholdLow || Math.abs(y) < thresholdLow || Math.abs(z) < thresholdLow) {
                    coloredLayout.setBackgroundColor(Color.GREEN);
                    accuracyText.setText("Low");
                } else if (Math.abs(x) > thresholdHigh || Math.abs(y) > thresholdHigh || Math.abs(z) > thresholdHigh) {
                    coloredLayout.setBackgroundColor(Color.RED);
                    accuracyText.setText("High");
                } else {
                    coloredLayout.setBackgroundColor(Color.BLACK);
                    accuracyText.setText("Mid");
                }

            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(accelerometreListener,sAccelerometre,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelerometreListener);
    }
}