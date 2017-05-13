package com.uestc.naldo.psm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.CommitActivity.AttendanceRecordActivity;
import com.uestc.naldo.psm.activity.DetailActivity.AttendanceTrainerListActivity;
import com.uestc.naldo.psm.activity.ItemActivity.TrainerActivity;
import com.uestc.naldo.psm.model.TrainerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naldo on 2017/5/12.
 */

public class TrainerItemAdapter extends RecyclerView.Adapter<TrainerItemAdapter.ViewHolder>{
    private Context mContext;
    private List<TrainerItem> mTrainerItemList;
    private String TAG = "TrainerItemAdapter";

    public TrainerItemAdapter(List<TrainerItem> trainerItemList) {
        mTrainerItemList = trainerItemList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView trainerName;
        TextView trainerSex;
        TextView trainerPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            trainerName = (TextView) itemView.findViewById(R.id.item_trainer_name);
            trainerSex = (TextView) itemView.findViewById(R.id.item_trainer_age);
            trainerPosition = (TextView) itemView.findViewById(R.id.item_trainer_position);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_trainer, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                TrainerItem trainerItem = mTrainerItemList.get(position);
                if (mContext.getClass().equals(AttendanceTrainerListActivity.class)){
                    Intent intent = new Intent(mContext, AttendanceRecordActivity.class);
                    intent.putExtra(AttendanceRecordActivity.TRAINER_ITEM_DATA, trainerItem);
                    mContext.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(mContext, TrainerActivity.class);
                    intent.putExtra(TrainerActivity.TRAINER_ITEM_DATA, trainerItem);
                    mContext.startActivity(intent);
                }
            }
        });

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrainerItem trainerItem = mTrainerItemList.get(position);
        holder.trainerName.setText(trainerItem.getName());
        holder.trainerSex.setText(trainerItem.getSex());
        holder.trainerPosition.setText(trainerItem.getName());

    }

    @Override
    public int getItemCount() {
        return mTrainerItemList.size();
    }



}
