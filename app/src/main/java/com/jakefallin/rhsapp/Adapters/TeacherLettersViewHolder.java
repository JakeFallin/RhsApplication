package com.jakefallin.rhsapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jakefallin.rhsapp.R;

/**
 * Created by Jake on 5/31/2016.
 */
public class TeacherLettersViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {
    public TextView letter;

    public TeacherLettersViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        letter = (TextView) itemView.findViewById(R.id.letter);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),
                "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT)
                .show();
    }
}