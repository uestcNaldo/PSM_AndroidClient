package com.uestc.naldo.psm.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.StyleItem;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Naldo on 2017/5/12.
 */

public class StyleAdapater  extends RecyclerView.Adapter<StyleAdapater.ViewHolder>{

    private Context mContext;
    private List<StyleItem> mStyleIteList;
    private String TAG = "StyleAdapater";
    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView styleImage;
        TextView styleName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            styleImage = (ImageView) itemView.findViewById(R.id.style_img);
            styleName = (TextView) itemView.findViewById(R.id.style_name);
        }
    }

    public StyleAdapater(List<StyleItem> styleItemList){
        mStyleIteList = styleItemList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_style, parent, false);
        Log.d(TAG, "onCreateViewHolder: mContext = "+mContext+", parent = "+parent.toString());
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StyleItem styleItem = mStyleIteList.get(position);
        holder.styleName.setText(styleItem.getName());
        Glide.with(mContext).load(styleItem.getImageId()).into(holder.styleImage);
    }

    @Override
    public int getItemCount() {
        return mStyleIteList.size();
    }




}
