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

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private List<Schedule> scheduleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView periodText, startText, endText;

        public MyViewHolder(View view) {
            super(view);
            periodText = (TextView) view.findViewById(R.id.period);
            startText = (TextView) view.findViewById(R.id.start);
            endText = (TextView) view.findViewById(R.id.end);
        }
    }


    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Schedule s = scheduleList.get(position);
        holder.periodText.setText(s.getPeriod());
        holder.startText.setText(s.getStart());
        holder.endText.setText(s.getEnd());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}