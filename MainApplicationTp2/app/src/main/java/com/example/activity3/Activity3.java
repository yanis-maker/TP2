package com.example.activity3;

import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Context;
import android.content.Intent;
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

import com.example.activity6.Activity6;
import com.example.mainapplicationtp2.MainActivity;
import com.example.mainapplicationtp2.R;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;
import java.util.List;

public class Activity3 extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sAccelerometre;
    SensorEventListener accelerometreListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        getSupportActionBar().hide();

        LinearLayout coloredLayout=findViewById(R.id.layoutColored);

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sAccelerometre=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        TextView accuracyText=findViewById(R.id.accuracyText);
        accelerometreListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                double x = event.values[0];
                double y = event.values[1];
                double z = event.values[2];

                float thresholdLow = 10.0f;
                float thresholdHigh = 15.0f;

                double accelartion=sqrt(x*x + y*y + z*z);

                if (accelartion<thresholdLow) {
                    coloredLayout.setBackgroundColor(Color.GREEN);
                    accuracyText.setText("Low");
                } else if (accelartion>thresholdHigh) {
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

        MaterialButton returnButton=findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(accelerometreListener, sAccelerometre, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelerometreListener);
    }
}