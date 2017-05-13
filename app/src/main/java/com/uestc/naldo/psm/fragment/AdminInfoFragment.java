package com.uestc.naldo.psm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Process;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.DetailActivity.AttendanceTrainerListActivity;
import com.uestc.naldo.psm.util.ActivityCollector;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminInfoFragment extends Fragment {


    public AdminInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View action_recordAttendance = getActivity().findViewById(R.id.action_record_attendance);
        action_recordAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttendanceTrainerListActivity.class);
                startActivity(intent);
            }
        });


        View action_exit = getActivity().findViewById(R.id.info_exit);
        action_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),"EXIT",Toast.LENGTH_SHORT).show();
                ActivityCollector.finishAll();
                Process.killProcess(Process.myPid());
            }
        });
    }
}
