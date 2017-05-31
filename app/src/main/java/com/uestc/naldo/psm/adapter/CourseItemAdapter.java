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

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.ItemActivity.CourseActivity;
import com.uestc.naldo.psm.model.Course;
import com.uestc.naldo.psm.model.CourseItem;

import java.util.List;

/**
 * Created by Naldo on 2017/5/12.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder> {

    private Context mContext;
    private List<Course> mCourseItemList;

    private String TAG = "CourseItemAdapter";

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView courseName;
        TextView courseDuration;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            courseName = (TextView) itemView.findViewById(R.id.item_course_name);
            courseDuration = (TextView) itemView.findViewById(R.id.item_course_duration);
        }
    }

    public CourseItemAdapter(List<Course> courseItemList){
        mCourseItemList = courseItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        Log.d(TAG, "onCreateViewHolder: mContext = "+mContext+", parent.getContext() = "+parent.getContext());
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_course, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Course courseItem = mCourseItemList.get(position);
                Intent intent = new Intent(mContext, CourseActivity.class);
                intent.putExtra(CourseActivity.COURSE_ITEM_DATA, courseItem);

                mContext.startActivity(intent);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Course courseItem = mCourseItemList.get(position);
        holder.courseName.setText(courseItem.getName());
        holder.courseDuration.setText(courseItem.getDuration());

    }

    @Override
    public int getItemCount() {
        return mCourseItemList.size();
    }






}
