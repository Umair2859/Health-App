package com.example.myapplicationghgh.adapters;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationghgh.R;
import com.example.myapplicationghgh.RecyclerViewInterface;
import com.example.myapplicationghgh.userdata.datar1;

import java.util.ArrayList;

public class adapterr1 extends RecyclerView.Adapter<adapterr1.viewholder> {
  ArrayList<datar1> arrayList;
  Context context;
private final RecyclerViewInterface recyclerViewInterface;
    public adapterr1(ArrayList<datar1> arrayList, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.arrayList = arrayList;
        this.context = context;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.r1, parent, false);
        return new adapterr1.viewholder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
datar1 info= (datar1) arrayList.get(position);
holder.dayss.setText(info.getDay());

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView dayss;
        public viewholder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            dayss=itemView.findViewById(R.id.day);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos= getAdapterPosition();
if(pos!=RecyclerView.NO_POSITION){
    recyclerViewInterface.onitemclick(pos);

}

                    }
                }
            });
        }
    }
}
