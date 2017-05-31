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
import com.uestc.naldo.psm.activity.DetailActivity.NotificationDetailActivity;
import com.uestc.naldo.psm.model.Notification;
import com.uestc.naldo.psm.model.NotificationItem;

import java.util.List;


/**
 * Created by Naldo on 2017/5/11.
 */

public class NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.ViewHolder>{

    private Context mContext;
    private String TAG = "NotificationItemAdapter";
    private List<Notification> mNotificationItemList;
    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView notifiTitle;
        TextView notifiDate;
//        TextView notifiAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            notifiTitle = (TextView) itemView.findViewById(R.id.item_notification_title);
            notifiDate = (TextView) itemView.findViewById(R.id.item_notification_date);
//            notifiAuthor = (TextView) itemView.findViewById(R.id.item_notification_author);

        }
    }

    public NotificationItemAdapter(List<Notification> notificationItemList){
        mNotificationItemList = notificationItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_notification, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Notification notificationItem = mNotificationItemList.get(position);
                Intent intent = new Intent(mContext, NotificationDetailActivity.class);
                intent.putExtra(NotificationDetailActivity.NOTIFI_ITEM_DATA, notificationItem);


//                Toast.makeText(v.getContext(), "Clicked Notification CardView: ID = "+notificationItem.getId(),Toast.LENGTH_SHORT).show();
                mContext.startActivity(intent);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Notification notificationItem = mNotificationItemList.get(position);
        holder.notifiTitle.setText(notificationItem.getTitle());
        holder.notifiDate.setText(notificationItem.getDate().toString());
//        holder.notifiAuthor.setText(notificationItem.getAuthor());


        
    }

    @Override
    public int getItemCount() {
        return mNotificationItemList.size();
    }


}
