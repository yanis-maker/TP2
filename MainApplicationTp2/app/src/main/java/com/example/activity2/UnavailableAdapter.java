package com.example.activity2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainapplicationtp2.R;

import java.util.List;

public class UnavailableAdapter extends RecyclerView.Adapter<UnavailableViewHolder> {

    Context context;

    List<String> unavailableSensors;

    public UnavailableAdapter(Context context,List<String> unavailableSensors) {
        this.context=context;
        this.unavailableSensors=unavailableSensors;
    }

    @NonNull
    @Override
    public UnavailableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UnavailableViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.unavailable_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UnavailableViewHolder holder, int position) {
        holder.sensorName.setText(unavailableSensors.get(position));
    }


    @Override
    public int getItemCount() {
        return unavailableSensors.size();
    }
}
