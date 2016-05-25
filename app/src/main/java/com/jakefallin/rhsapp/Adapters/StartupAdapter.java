package com.jakefallin.rhsapp.Adapters;

/**
 * Created by Jake on 5/18/2016.
 */

import android.app.Dialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.jakefallin.rhsapp.Objects.Startup;
import com.jakefallin.rhsapp.R;
import com.jakefallin.rhsapp.Util.AppController;

import java.util.List;

public class StartupAdapter extends RecyclerView.Adapter<StartupAdapter.MyViewHolder> {

    SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(AppController.getAppContext());

    private List<Startup> startupList;

    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView titleStartup, descriptionStartup;
        public CheckBox cb1, cb2, cb3, cb4;


        public MyViewHolder(View view) {
            super(view);

            titleStartup = (TextView) view.findViewById(R.id.titleStartup);
            descriptionStartup = (TextView) view.findViewById(R.id.descriptionStartup);
            cb1 = (CheckBox) view.findViewById(R.id.cb1);
            cb2 = (CheckBox) view.findViewById(R.id.cb2);
            cb3 = (CheckBox) view.findViewById(R.id.cb3);
            cb4 = (CheckBox) view.findViewById(R.id.cb4);


        }
    }

    public void doDialog(View v) {


    }






    public StartupAdapter() {}

    public StartupAdapter(List<Startup> startupList) {
        this.startupList = startupList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custom_schedule, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Startup s = startupList.get(position);
        holder.titleStartup.setText(s.getTitle());
        holder.descriptionStartup.setText(s.getDescription());
        holder.cb1.setChecked(s.isCb1());
        holder.cb2.setChecked(s.isCb2());
        holder.cb3.setChecked(s.isCb3());
        holder.cb4.setChecked(s.isCb4());

    }

    @Override
    public int getItemCount() {
        return startupList.size();
    }

    public void updateList(List<Startup> data)
    {
        startupList = data;
    }

}