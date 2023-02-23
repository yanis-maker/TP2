package com.example.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.activity2.Activity2;
import com.example.mainapplicationtp2.MainActivity;
import com.example.mainapplicationtp2.R;
import com.google.android.material.button.MaterialButton;

public class Activity4 extends AppCompatActivity {

    SensorManager sensorManager;

    SensorEventListener directionListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        getSupportActionBar().hide();

        MaterialButton returnButton=findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity4.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView directionText=findViewById(R.id.directionText);

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sDirection=sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        float[] gravity = new float[3];
        directionListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                final float alpha = 0.8f;
                gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
                gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
                gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

                // Calcul de la direction du mouvement
                float x = event.values[0] - gravity[0];
                float y = event.values[1] - gravity[1];
                float z = event.values[2] - gravity[2];
                float acceleration = (float) Math.sqrt(x * x + y * y + z * z);
                if (acceleration > 10) {
                    if (Math.abs(x) > Math.abs(y)) {
                        if (x > 0) {
                            directionText.setText("Right");
                        } else {
                            directionText.setText("Left");
                        }
                    } else {

                        if (y > 0) {
                            directionText.setText("Down ");
                        } else {
                            directionText.setText("Up");
                        }
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(directionListener,sDirection,SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(directionListener);
    }
}