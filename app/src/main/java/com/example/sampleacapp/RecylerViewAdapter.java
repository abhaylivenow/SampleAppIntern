package com.example.sampleacapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class RecylerviewAdapter extends RecyclerView.Adapter<RecylerviewAdapter.AcHolder> {

    private final List<AC> AcList;

    public RecylerviewAdapter(ArrayList<AC> AcList) {
        this.AcList = AcList;
    }

    @NonNull
    @Override
    public AcHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new AcHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerviewAdapter.AcHolder holder, int position) {

        holder.txt_head.setText(AcList.get(position).getModel());
        holder.txtDate.setText(AcList.get(position).getDate());
        holder.txt_installedPlace.setText(AcList.get(position).getInstalledPlace());

        if(AcList.get(position).getAcType().equals("Window")){
            holder.acImage.setImageResource(R.drawable.winac);
        }else {
            holder.acImage.setImageResource(R.drawable.ac);
        }
        System.out.println(AcList.get(position).getAcType());

    }

    @Override
    public int getItemCount() {
        return AcList.size();
    }

    class AcHolder extends RecyclerView.ViewHolder{
        private TextView txt_head, txtDate, txt_installedPlace;
        private ImageView acImage;

        public AcHolder(@NonNull View itemView) {
            super(itemView);
            txt_head = itemView.findViewById(R.id.list_item_heading);
            txtDate = itemView.findViewById(R.id.list_item_text);
            txt_installedPlace = itemView.findViewById(R.id.list_item_installedPlace);
            acImage = itemView.findViewById(R.id.list_item_imageView);
        }
    }
}