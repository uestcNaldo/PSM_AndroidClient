package com.uestc.naldo.psm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.util.ActivityCollector;

/**
 * Created by Naldo on 2017/4/17.
 */

public class OwnerInfoFragment extends Fragment {
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
//                Toast.makeText(getActivity(),"EXIT",Toast.LENGTH_SHORT).show();
                ActivityCollector.finishAll();
                Process.killProcess(Process.myPid());
            }
        });


    }
}
