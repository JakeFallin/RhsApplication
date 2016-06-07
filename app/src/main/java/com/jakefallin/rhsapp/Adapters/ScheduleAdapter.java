package com.jakefallin.rhsapp.Adapters;

/**
 * Created by Jake on 3/11/2016.
 */

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakefallin.rhsapp.R;
import com.jakefallin.rhsapp.Objects.Schedule;
import com.jakefallin.rhsapp.Util.AppController;

import java.util.List;

//Adapter to show dialog in ScheduleFragment

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private SharedPreferences.Editor mEditor;
    SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(AppController.getAppContext());
    boolean is24H = appPreferences.getBoolean("24h", false);

    private List<Schedule> scheduleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView periodText, startText, endText, teacherText;
        RelativeLayout list_row;


        public MyViewHolder(View view) {
            super(view);
            periodText = (TextView) view.findViewById(R.id.period);
            startText = (TextView) view.findViewById(R.id.start);
            endText = (TextView) view.findViewById(R.id.end);
            teacherText = (TextView) view.findViewById(R.id.teacher);
            list_row = (RelativeLayout) view.findViewById(R.id.schedule_row);
        }
    }


    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Schedule s = scheduleList.get(position);
        holder.periodText.setText(s.getPeriod());
        if (!is24H) {
            holder.startText.setText(s.getStart());
            holder.endText.setText(s.getEnd());
        } else {
            holder.startText.setText(s.getStart24());
            holder.endText.setText(s.getEnd24());
        }
        holder.teacherText.setText(s.getTeacher());

        if (scheduleList.get(position).isSelected()) {
            holder.list_row.setBackgroundColor(Color.parseColor("#37474F"));
        } else {
            holder.list_row.setBackgroundColor(Color.TRANSPARENT);
        }

    }

    public void setSelected(int pos) {
        try {
            if (scheduleList.size() > 1) {
                scheduleList.get(appPreferences.getInt("position", 0)).setSelected(false);
                mEditor.putInt("position", pos);
                mEditor.commit();
            }
            scheduleList.get(pos).setSelected(true);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public void updateList(List<Schedule> data) {
        scheduleList = data;
        notifyDataSetChanged();
    }


}