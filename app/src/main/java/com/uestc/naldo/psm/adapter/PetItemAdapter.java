package com.uestc.naldo.psm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.DetailActivity.PetOwnerListActivity;
import com.uestc.naldo.psm.activity.DetailActivity.PetTrainerListActivity;
import com.uestc.naldo.psm.activity.ItemActivity.PetOwnerActivity;
import com.uestc.naldo.psm.activity.ItemActivity.PetTrainerActivity;
import com.uestc.naldo.psm.activity.MainActivity.OwnerMainActivity;
import com.uestc.naldo.psm.activity.MainActivity.TrainerMainActivity;
import com.uestc.naldo.psm.model.Pet;
import com.uestc.naldo.psm.model.PetItem;

import java.util.List;


public class PetItemAdapter extends RecyclerView.Adapter<PetItemAdapter.ViewHolder>{

    private Context mContext;
    private List<Pet> mPetItemList;
    private String TAG = "PetItemAdapter";

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView petItemName;
        TextView petItemAge;
        TextView petItemSex;
        TextView petItemSpecies;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            petItemName = (TextView) itemView.findViewById(R.id.item_pet_name);
            petItemAge = (TextView) itemView.findViewById(R.id.item_pet_age);
            petItemSex = (TextView) itemView.findViewById(R.id.item_pet_sex);
            petItemSpecies = (TextView) itemView.findViewById(R.id.item_pet_species);

        }
    }

    public PetItemAdapter(List<Pet> petItemList){
        mPetItemList = petItemList;
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_owner_pet, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Pet petItem = mPetItemList.get(position);
//                Toast.makeText(v.getContext(), "Clicked Pet CardView"+petItem.getId(),Toast.LENGTH_SHORT).show();

                //如果宠物主人使用PetItemAdapter，从宠物主人主页查看
                if (mContext.getClass().equals(OwnerMainActivity.class)){
                    Log.d(TAG, "mContext.class = OwnerMainActivity.class");
                    Intent intent = new Intent(mContext, PetOwnerActivity.class);
                    intent.putExtra(PetOwnerActivity.PET_ITEM_DATA, petItem);
                    mContext.startActivity(intent);
                }

                //如果宠物主人使用PetItemAdapter，从学校宠物信息库查看
                if (mContext.getClass().equals(PetOwnerListActivity.class)){
                    Log.d(TAG, "mContext.class = OwnerMainActivity.class");
                    Intent intent = new Intent(mContext, PetOwnerActivity.class);
                    intent.putExtra(PetOwnerActivity.PET_ITEM_DATA, petItem);
                    mContext.startActivity(intent);
                }

                //如果训练师和管理员使用PetItemAdapter，从学校宠物信息库查看
                if (mContext.getClass().equals(PetTrainerListActivity.class)){
                    Log.d(TAG, "mContext.class = OwnerMainActivity.class");
                    Intent intent = new Intent(mContext, PetTrainerActivity.class);
                    intent.putExtra(PetTrainerActivity.PET_ITEM_TRAINER_DATA, petItem);
                    mContext.startActivity(intent);
                }




            }
        });
        viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Pet petItem = mPetItemList.get(position);

//                Toast.makeText(v.getContext(), "Long Clicked Pet CardView: ID = "+petItem.getId(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pet petItem = mPetItemList.get(position);
        holder.petItemName.setText(petItem.getName());
        holder.petItemAge.setText(String.valueOf(petItem.getAge()));
        holder.petItemSex.setText(petItem.getSex());
        holder.petItemSpecies.setText(petItem.getSpecies());

    }

    @Override
    public int getItemCount() {
        return mPetItemList.size();
    }


}
