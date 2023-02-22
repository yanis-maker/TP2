package com.example.activity2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import com.example.activity1.Activity1;
import com.example.activity1.SensorAdapter;
import com.example.mainapplicationtp2.MainActivity;
import com.example.mainapplicationtp2.R;
import com.google.android.material.button.MaterialButton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        getSupportActionBar().hide();


        List<Integer> types= Arrays.asList(Sensor.TYPE_ACCELEROMETER,Sensor.TYPE_AMBIENT_TEMPERATURE,
                Sensor.TYPE_GRAVITY,Sensor.TYPE_GYROSCOPE,Sensor.TYPE_LIGHT,Sensor.TYPE_LINEAR_ACCELERATION,
                Sensor.TYPE_MAGNETIC_FIELD,Sensor.TYPE_ORIENTATION,Sensor.TYPE_PRESSURE,
                Sensor.TYPE_PROXIMITY,Sensor.TYPE_RELATIVE_HUMIDITY,Sensor.TYPE_ROTATION_VECTOR,Sensor.TYPE_TEMPERATURE);

        Map<Integer,String> sensorsName = new HashMap<Integer,String>();
        sensorsName.put(types.get(0),"Motion detection");
        sensorsName.put(types.get(1),"Monitoring air temperatures");
        sensorsName.put(types.get(2),"Motion detection");
        sensorsName.put(types.get(3),"Rotation detection");
        sensorsName.put(types.get(4),"Controlling screen brightness");
        sensorsName.put(types.get(5),"Monitoring acceleration along a single axis");
        sensorsName.put(types.get(6),"Creating a compass");
        sensorsName.put(types.get(7),"Determining device position");
        sensorsName.put(types.get(8),"Monitoring air pressure changes");
        sensorsName.put(types.get(9),"Phone position during a call");
        sensorsName.put(types.get(10),"Monitoring viewpoint, absolute and relative humidity");
        sensorsName.put(types.get(11),"Motion detection and rotation detection");
        sensorsName.put(types.get(12),"Monitoring temperatures");

        List<String> inexistentSensors=new ArrayList<>();

        SensorManager sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        for(Integer t : types){
            if(sensorManager.getDefaultSensor(t)==null){
                inexistentSensors.add(sensorsName.get(t));
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity2.this));
        recyclerView.setAdapter(new UnavailableAdapter(getApplicationContext(),inexistentSensors));

        MaterialButton returnButton=findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity2.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}