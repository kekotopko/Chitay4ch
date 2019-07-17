package com.example.odmen.chitay4ch.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Wall.Answers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 25.03.2018.
 */

public class AdapterPoll extends RecyclerView.Adapter<AdapterPoll.ViewHolder>{
    List<Answers>answers=new ArrayList<>();

    public AdapterPoll(){

    }

    public void setData(List<Answers>data){
        answers.clear();
        answers.addAll(data);
        notifyDataSetChanged();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.poll,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Answers answer=answers.get(position);
        holder.answer.setText(answer.getText());
        holder.procent.setText(String.valueOf(answer.getRate())+"%");
        holder.progressVotes.setMax(100);
        holder.progressVotes.setProgress((int) answer.getRate());

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView votes,answer,procent;
        public ProgressBar progressVotes;

        public ViewHolder(View itemView) {
            super(itemView);
            procent= (TextView) itemView.findViewById(R.id.procent);
            answer = (TextView) itemView.findViewById(R.id.pollAnswer);
            progressVotes= (ProgressBar) itemView.findViewById(R.id.progressVotes);
        }
    }
}
