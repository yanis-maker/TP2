package com.example.activity1;


import static android.icu.lang.UCharacter.getType;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainapplicationtp2.R;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorViewHolder> {

    Context context;
    List<Sensor> sensors;

    public SensorAdapter(Context context, List<Sensor> sensors) {
        this.context = context;
        this.sensors = sensors;
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SensorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.captor_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        holder.name.setText(sensors.get(position).getName());
        holder.type.setText(Integer.toString(sensors.get(position).getType()));
        holder.version.setText(Integer.toString(sensors.get(position).getVersion()));
        holder.resolution.setText(Float.toString(sensors.get(position).getResolution()));
        holder.power.setText(Float.toString(sensors.get(position).getPower()));
        holder.vendor.setText(sensors.get(position).getVendor());
        holder.maxRange.setText(Float.toString(sensors.get(position).getMaximumRange()));
        holder.minDelay.setText(Integer.toString(sensors.get(position).getMinDelay()));

    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }
}
