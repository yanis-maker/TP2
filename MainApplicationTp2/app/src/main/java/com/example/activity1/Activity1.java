package com.example.activity1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import com.example.mainapplicationtp2.MainActivity;
import com.example.mainapplicationtp2.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class Activity1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        getSupportActionBar().hide();

        final SensorManager sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList=sensorManager.getSensorList(Sensor.TYPE_ALL);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity1.this));
        recyclerView.setAdapter(new SensorAdapter(getApplicationContext(),sensorList));

        MaterialButton returnButton=findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity1.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}