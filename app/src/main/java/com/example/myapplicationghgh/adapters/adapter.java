package com.example.myapplicationghgh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationghgh.R;
import com.example.myapplicationghgh.userdata.datalist;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {
    ArrayList<datalist> arrayList;
    Context context;

    public adapter(ArrayList<datalist> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false);

        return new adapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        datalist datalistt = (datalist) arrayList.get(position);
        holder.item1.setText(datalistt.getTitle());
        holder.item2.setText(datalistt.getDiscription());
        holder.item3.setText(datalistt.getSuger());
        holder.item4.setText(datalistt.getCalories());
        holder.item5.setText(datalistt.getFat());
        holder.item6.setText(datalistt.getCholistrol());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class viewholder extends RecyclerView.ViewHolder {
        TextView item1, item2, item3, item4, item5, item6;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            item1 = itemView.findViewById(R.id.i);
            item2 = itemView.findViewById(R.id.j);
            item3 = itemView.findViewById(R.id.k);
            item4 = itemView.findViewById(R.id.l);
            item5 = itemView.findViewById(R.id.m);
            item6 = itemView.findViewById(R.id.cl);


        }
    }
}
