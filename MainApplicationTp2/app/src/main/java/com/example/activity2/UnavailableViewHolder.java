package com.example.activity2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainapplicationtp2.R;

public class UnavailableViewHolder extends RecyclerView.ViewHolder {

    TextView sensorName;
    public UnavailableViewHolder(@NonNull View itemView) {
        super(itemView);
        sensorName=itemView.findViewById(R.id.sensorName);
    }
}
