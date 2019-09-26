package com.example.uas_pwpb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {
    Context context;
    List<data_input> SelectUserData;
    OnUserActionListener listener;


    public RecyclerViewAdapter(Context context, List<data_input> selectUserData, OnUserActionListener listener) {
        this.context = context;
        this.SelectUserData = selectUserData;
        this.listener = listener;
    }

    public interface OnUserActionListener {
        void onUserAction(data_input dataInput);
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row, parent, false);
        DataViewHolder vh = new DataViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        final data_input mdataInput = SelectUserData.get(position);
        holder.date.setText(mdataInput.getDatentime());
        holder.tittle.setText(mdataInput.getTitle());
        holder.desc.setText(mdataInput.getDesription());
        holder.cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserAction(mdataInput);
            }
        });

    }

    @Override
    public int getItemCount() {
        return SelectUserData.size();
    }



    public class DataViewHolder extends RecyclerView.ViewHolder {
        CardView cv1;
        RelativeLayout relativeLayout1;
        TextView date, tittle, desc;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            cv1 = (CardView) itemView.findViewById(R.id.cardview_1);
            relativeLayout1 = (RelativeLayout) itemView.findViewById(R.id.RelativeLayout_1);
            date = (TextView) itemView.findViewById(R.id.date_n_time);
            tittle = (TextView) itemView.findViewById(R.id.edt_Tittle);
            desc = (TextView) itemView.findViewById(R.id.edt_desc);

        }
    }
}




