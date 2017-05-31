package com.uestc.naldo.psm.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.DetailActivity.CourseListActivity;
import com.uestc.naldo.psm.activity.DetailActivity.PetOwnerListActivity;
import com.uestc.naldo.psm.activity.DetailActivity.SchoolDetailActivity;
import com.uestc.naldo.psm.activity.DetailActivity.SchoolStyleActivity;
import com.uestc.naldo.psm.activity.DetailActivity.TrainerListActivity;

/**
 * Created by Naldo on 2017/4/17.
 */

public class OwnerSchoolFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_owner_school, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar_owner_school = (Toolbar)getActivity().findViewById(R.id.toolbar_owner_school);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_owner_school);

        CardView cardView_school = (CardView) getActivity().findViewById(R.id.card_item_school);
        cardView_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SchoolDetailActivity.class);
                startActivity(intent);

//                Toast.makeText(getActivity(), "Click CardView: "+v.getContext(),Toast.LENGTH_SHORT).show();
            }
        });

//        CardView cardView_school_calendar = (CardView) getActivity().findViewById(R.id.card_item_school_calendar);
//        cardView_school_calendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getActivity(), "Click CardView: "+v.getContext(),Toast.LENGTH_SHORT).show();
//            }
//        });

        CardView cardView_school_detail = (CardView) getActivity().findViewById(R.id.card_item_school_detail);
        cardView_school_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SchoolStyleActivity.class);
                startActivity(intent);
//                Toast.makeText(getActivity(), "Click CardView: "+v.getContext(),Toast.LENGTH_SHORT).show();
            }
        });

        CardView cardView_course = (CardView) getActivity().findViewById(R.id.card_item_course);
        cardView_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseListActivity.class);
                startActivity(intent);

//                Toast.makeText(getActivity(), "Click CardView: "+v.getContext(),Toast.LENGTH_SHORT).show();
            }
        });

        CardView cardView_pet = (CardView) getActivity().findViewById(R.id.card_item_pet);
        cardView_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PetOwnerListActivity.class);
                startActivity(intent);

//                Toast.makeText(getActivity(), "Click CardView: "+v.getContext(),Toast.LENGTH_SHORT).show();
            }
        });

        CardView cardView_trainer = (CardView) getActivity().findViewById(R.id.card_item_trainer);
        cardView_trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrainerListActivity.class);
                startActivity(intent);
//                Toast.makeText(getActivity(), "Click CardView: "+v.getContext(),Toast.LENGTH_SHORT).show();
            }
        });




    }
}
