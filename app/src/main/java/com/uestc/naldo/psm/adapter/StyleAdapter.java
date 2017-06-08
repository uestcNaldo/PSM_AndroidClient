package com.uestc.naldo.psm.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.Photo;
import com.uestc.naldo.psm.model.StyleItem;
import com.uestc.naldo.psm.util.Static;

import java.util.List;


public class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.ViewHolder>{

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/image/";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private Context mContext;
    private List<Photo> mStyleItemList;
    private String TAG = "StyleAdapter";
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

    public StyleAdapter(List<Photo> styleItemList){
        mStyleItemList = styleItemList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_style, parent, false);
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
        Photo photo = mStyleItemList.get(position);
        String URI = URL+photo.getName();
        holder.styleName.setText(photo.getName());
        Glide.with(mContext).load(URI).into(holder.styleImage);
    }

    @Override
    public int getItemCount() {
        return mStyleItemList.size();
    }




}
