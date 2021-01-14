package com.example.cultuurkompas.activities.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.detail.RouteDetailScreenActivity;
import com.example.cultuurkompas.data.datamodel.Route;
import com.example.cultuurkompas.interfaces.DataConnector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RouteRVAdapter extends RecyclerView.Adapter<RouteRVAdapter.ViewHolder> {

    private List<Route> routes;
    private Context context;
    public RouteRVAdapter(Context context) {
        this.routes = DataConnector.getInstance().getRoutes();
        this.context=context;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_route, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Route route = routes.get(position);
        Picasso.get().load(route.getImgLink()).into(holder.image);
        holder.name.setText(route.getName());
        holder.info.setText(route.getInfo());
        if(route.isActive()){
            holder.name.setTextColor(ContextCompat.getColor(context, R.color.gold_tips));
        }
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        ImageView image;
        TextView name;
        TextView info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_routescreen_item);
            name = itemView.findViewById(R.id.tv_routescreen_item_name);
            info = itemView.findViewById(R.id.tv_routescreen_item_info);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Route route = routes.get(getLayoutPosition());
            Intent intent = new Intent(itemView.getContext(), RouteDetailScreenActivity.class);
            intent.putExtra("route", route);
            itemView.getContext().startActivity(intent);
        }
    }
}
