package com.jakefallin.rhsapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jakefallin.rhsapp.Objects.Absence;
import com.jakefallin.rhsapp.R;
import java.util.List;

public class AbsenceAdapter extends RecyclerView.Adapter<AbsenceAdapter.MyViewHolder> {

    private List<Absence> absenceList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView location;
        public TextView reason;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);

        }
    }
    public AbsenceAdapter(List<Absence> absenceList1) {
        absenceList = absenceList1;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_absence, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Absence a = absenceList.get(position);
        holder.name.setText(a.getName());
    }


    @Override
    public int getItemCount() {
        return absenceList.size();
    }
}