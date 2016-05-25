package com.jakefallin.rhsapp.Adapters;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.jakefallin.rhsapp.Objects.Startup;
import com.jakefallin.rhsapp.R;

public class DialogAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Startup s;

    public DialogAdapter(Context context, Startup myS) {
        layoutInflater = LayoutInflater.from(context);
        s = myS;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.dialog_add_class, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.cb1 = (CheckBox) view.findViewById(R.id.checkBox1);
            viewHolder.cb2 = (CheckBox) view.findViewById(R.id.checkBox2);
            viewHolder.cb3 = (CheckBox) view.findViewById(R.id.checkBox3);
            viewHolder.cb4 = (CheckBox) view.findViewById(R.id.checkBox4);
            viewHolder.teacher = (EditText) view.findViewById(R.id.etTeacher);
            viewHolder.title = (EditText) view.findViewById(R.id.etClass);
            viewHolder.save = (Button) view.findViewById(R.id.button_save);
            viewHolder.cancel = (Button) view.findViewById(R.id.button_cancel);
            viewHolder.switchCompat = (SwitchCompat) view.findViewById(R.id.switch_free);
            viewHolder.spinner = (Spinner) view.findViewById(R.id.spinner);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();

        }

        Context context = parent.getContext();

        viewHolder.title.setHint(s.getTitle());
        viewHolder.teacher.setHint(s.getDescription());
        viewHolder.cb1.setChecked(s.isCb1());
        viewHolder.cb2.setChecked(s.isCb2());
        viewHolder.cb3.setChecked(s.isCb3());
        viewHolder.cb4.setChecked(s.isCb4());
        viewHolder.title.requestFocus();

        return view;
    }

    static class ViewHolder {
        EditText title;
        EditText teacher;
        Button save;
        Button cancel;
        SwitchCompat switchCompat;
        Spinner spinner;
        CheckBox cb1;
        CheckBox cb2;
        CheckBox cb3;
        CheckBox cb4;

    }
}