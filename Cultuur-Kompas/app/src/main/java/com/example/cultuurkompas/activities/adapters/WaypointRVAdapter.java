package com.example.cultuurkompas.activities.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.detail.BuildingDetailScreenActivity;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.DataConnector;

import java.util.List;

public class WaypointRVAdapter extends RecyclerView.Adapter<WaypointRVAdapter.ViewHolder> {

    private List<Waypoint> waypoints;

    public WaypointRVAdapter(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_waypoint, parent, false);
        return new WaypointRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaypointRVAdapter.ViewHolder holder, int position) {
        Waypoint waypoint = waypoints.get(position);
        String text = waypoint.getName();
        holder.name.setText(text);
    }

    @Override
    public int getItemCount() {
        return waypoints.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_waypoint_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Waypoint waypoint = waypoints.get(getLayoutPosition());
            Intent intent = new Intent(itemView.getContext(), BuildingDetailScreenActivity.class);
            intent.putExtra("waypoint", waypoint);
            itemView.getContext().startActivity(intent);
        }
    }
}