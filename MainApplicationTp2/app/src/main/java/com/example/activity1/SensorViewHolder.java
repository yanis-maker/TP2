package com.example.activity1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainapplicationtp2.R;

public class SensorViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView type;
    TextView version;
    TextView resolution;
    TextView power;
    TextView vendor;
    TextView maxRange;
    TextView minDelay;

    public SensorViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        type=itemView.findViewById(R.id.type);
        version=itemView.findViewById(R.id.version);
        resolution=itemView.findViewById(R.id.resolution);
        power=itemView.findViewById(R.id.power);
        vendor=itemView.findViewById(R.id.vendor);
        maxRange=itemView.findViewById(R.id.maxRange);
        minDelay=itemView.findViewById(R.id.minDelay);
    }
}
