package com.uestc.naldo.psm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.DetailActivity.ContactActivity;
import com.uestc.naldo.psm.activity.DetailActivity.CourseListActivity;
import com.uestc.naldo.psm.activity.DetailActivity.PetTrainerListActivity;
import com.uestc.naldo.psm.activity.DetailActivity.SchoolDetailActivity;
import com.uestc.naldo.psm.activity.DetailActivity.SchoolStyleActivity;
import com.uestc.naldo.psm.activity.DetailActivity.TrainerListActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminSchoolFragment extends Fragment {


    public AdminSchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_school, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar_admin_school = (Toolbar)getActivity().findViewById(R.id.toolbar_admin_school);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_admin_school);

        CardView cardView_school = (CardView) getActivity().findViewById(R.id.card_item_school);
        cardView_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SchoolDetailActivity.class);
                startActivity(intent);

            }
        });

        CardView cardView_school_calendar = (CardView) getActivity().findViewById(R.id.card_item_school_calendar);
        cardView_school_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent(getActivity(), PetTrainerListActivity.class);
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
