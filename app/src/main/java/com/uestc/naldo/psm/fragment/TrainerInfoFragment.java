package com.uestc.naldo.psm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Process;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.DetailActivity.AttendanceDetailActivity;
import com.uestc.naldo.psm.activity.MainActivity.TrainerMainActivity;
import com.uestc.naldo.psm.model.Trainer;
import com.uestc.naldo.psm.util.ActivityCollector;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainerInfoFragment extends Fragment {

    private Trainer trainer;

    public TrainerInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainer = TrainerMainActivity.trainer;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trainer_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View action_CheckAttendanceRecord = getActivity().findViewById(R.id.action_check_attendance);
        action_CheckAttendanceRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttendanceDetailActivity.class);
                intent.putExtra("trainer", trainer);
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

        View action_info_show = getActivity().findViewById(R.id.info_trainer);
        action_info_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看训练师个人信息
            }
        });
        TextView info_name = (TextView) getActivity().findViewById(R.id.info_name);
        info_name.setText(trainer.getName()+"("+trainer.getUsername()+")");


    }
}
