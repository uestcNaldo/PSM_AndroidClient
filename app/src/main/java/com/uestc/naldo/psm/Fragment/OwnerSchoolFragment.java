package com.uestc.naldo.psm.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.naldo.psm.R;

/**
 * Created by Naldo on 2017/4/17.
 */

public class OwnerSchoolFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_owner_school, container, false);

    }
}
