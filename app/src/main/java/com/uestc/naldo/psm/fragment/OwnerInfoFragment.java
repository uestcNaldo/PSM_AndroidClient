package com.uestc.naldo.psm.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.CommitActivity.OwnerFeedbackCommitActivity;
import com.uestc.naldo.psm.activity.DetailActivity.OwnerDetailActivity;
import com.uestc.naldo.psm.activity.DetailActivity.OwnerNotifiListActivity;
import com.uestc.naldo.psm.activity.MainActivity.OwnerMainActivity;
import com.uestc.naldo.psm.model.Owner;
import com.uestc.naldo.psm.util.ActivityCollector;


public class OwnerInfoFragment extends Fragment {

    public static Owner owner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        owner = OwnerMainActivity.owner;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_owner_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View action_exit = getActivity().findViewById(R.id.info_exit);
        action_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                Process.killProcess(Process.myPid());
            }
        });

        View action_info_show = getActivity().findViewById(R.id.info_owner);
        action_info_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看宠物主人信息
                Intent intent = new Intent(getActivity(), OwnerDetailActivity.class);
                intent.putExtra("owner", owner);
                startActivity(intent);
            }
        });
        final TextView info_name = (TextView) getActivity().findViewById(R.id.info_name);
        info_name.setText(owner.getName()+"("+owner.getUsername()+")");

        View action_info_feedback = getActivity().findViewById(R.id.info_owner_feedback);
        action_info_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OwnerFeedbackCommitActivity.class);
                intent.putExtra("owner", owner);
                startActivity(intent);

            }
        });

        View action_show_notifi = getActivity().findViewById(R.id.info_notifi);
        action_show_notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OwnerNotifiListActivity.class);
                startActivity(intent);

            }
        });



    }
}
