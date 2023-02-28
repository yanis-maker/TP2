package com.example.activity5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.activity4.Activity4;
import com.example.mainapplicationtp2.MainActivity;
import com.example.mainapplicationtp2.R;
import com.google.android.material.button.MaterialButton;

public class Activity5 extends AppCompatActivity {

    private SensorManager sensorManager;
    private SensorEventListener flashListener;
    Sensor sAccelerometre;
    private CameraManager camera;
    boolean flashOn=false;

    private String cameraID;
    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        getSupportActionBar().hide();

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sAccelerometre=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        MaterialButton switchOffButton=findViewById(R.id.switchOffButton);
        TextView flashText=findViewById(R.id.flashText);

        boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(!isFlashAvailable){
            flashText.setTextSize(20.0f);
            flashText.setText("Flash not available on this device");
        }

        camera = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraID = camera.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        flashListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];


                // Calculer l'accélération nette
                float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

                if (acceleration > 25 ) {
                    if (flashOn) {
                        flashText.setText("Flash Off");
                        flashOn = switchFlashLight(false);
                        switchOffButton.setVisibility(View.GONE);
                    } else {
                        flashText.setText("Flash On");
                        flashOn = switchFlashLight(true);
                        switchOffButton.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


        switchOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchOffButton.setVisibility(View.GONE);
                switchFlashLight(false);
                flashText.setText("Shake your phone");
            }
        });

        MaterialButton returnButton=findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity5.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean switchFlashLight(boolean status) {
        try {
            camera.setTorchMode(cameraID, status);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return status;
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(flashListener,sAccelerometre,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(flashListener);
    }
}