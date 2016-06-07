package com.jakefallin.rhsapp.Adapters;

/**
 * Created by Jake on 6/3/2016.
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

import com.jakefallin.rhsapp.Objects.Schedule;
import com.jakefallin.rhsapp.Objects.Teacher;
import com.jakefallin.rhsapp.R;
import com.jakefallin.rhsapp.Util.AppController;

import java.util.List;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.MyViewHolder> {

    private List<Teacher> teacherList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView first, last, email;

        public MyViewHolder(View view) {
            super(view);
            first = (TextView) view.findViewById(R.id.teacher_first);
            last = (TextView) view.findViewById(R.id.teacher_last);
            email = (TextView) view.findViewById(R.id.teacher_email);
        }
    }


    public TeacherListAdapter(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_teacher, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Teacher t = teacherList.get(position);

        holder.first.setText(t.getFirst());
        holder.last.setText(t.getLast());
        holder.email.setText(t.getEmail());
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public void updateList(List<Teacher> data) {
        teacherList = data;
        notifyDataSetChanged();
    }


}