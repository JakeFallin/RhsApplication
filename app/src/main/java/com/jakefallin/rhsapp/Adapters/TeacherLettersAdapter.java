package com.jakefallin.rhsapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakefallin.rhsapp.Objects.Letter;
import com.jakefallin.rhsapp.R;

import java.util.List;

/**
 * Created by Jake on 5/31/2016.
 */
public class TeacherLettersAdapter extends RecyclerView.Adapter<TeacherLettersViewHolder>
{
    private List<Letter> itemList;
    private Context context;

    public TeacherLettersAdapter(Context context,
                                     List<Letter> itemList)
    {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public TeacherLettersViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.teacher_letter_name, null);
        TeacherLettersViewHolder rcv = new TeacherLettersViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(TeacherLettersViewHolder holder, int position)
    {
        holder.letter.setText(itemList.get(position).get_letter());
    }

    @Override
    public int getItemCount()
    {
        return this.itemList.size();
    }
}
