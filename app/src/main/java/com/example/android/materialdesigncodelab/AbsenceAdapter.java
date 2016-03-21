package com.example.android.materialdesigncodelab;

/**
 * Created by Jake on 3/11/2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AbsenceAdapter extends RecyclerView.Adapter<AbsenceAdapter.MyViewHolder> {

    private List<Absence> absenceList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, location, reason;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            location = (TextView) view.findViewById(R.id.location);
            reason = (TextView) view.findViewById(R.id.reason);
        }
    }


    public AbsenceAdapter(List<Absence> absenceList1) {
        absenceList = absenceList1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.absence, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Absence a = absenceList.get(position);
        holder.name.setText(a.getName());
        holder.location.setText(a.getLocation());
        holder.reason.setText(a.getReason());
    }

    @Override
    public int getItemCount() {
        return absenceList.size();
    }
}