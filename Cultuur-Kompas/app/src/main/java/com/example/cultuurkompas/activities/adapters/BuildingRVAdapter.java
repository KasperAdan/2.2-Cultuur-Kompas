package com.example.cultuurkompas.activities.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.detail.BuildingDetailScreenActivity;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.DataConnector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BuildingRVAdapter extends RecyclerView.Adapter<BuildingRVAdapter.ViewHolder> {

    private List<Waypoint> buildings;

    //TODO Checkbox turns invisible when checked

    public BuildingRVAdapter() {
        this.buildings = DataConnector.getInstance().getWaypoints();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_building, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Waypoint building = buildings.get(position);
        Picasso.get().load(building.getImgLink()).into(holder.image);
        holder.name.setText(building.getName());
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        ImageView image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_buildingscreen_item);
            name = itemView.findViewById(R.id.tv_buildingscreen_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Waypoint waypoint = buildings.get(getLayoutPosition());
            Intent intent = new Intent(itemView.getContext(), BuildingDetailScreenActivity.class);
            intent.putExtra("waypoint", waypoint);
            itemView.getContext().startActivity(intent);
        }
    }
}
