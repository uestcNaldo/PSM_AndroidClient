package com.uestc.naldo.psm.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.naldo.psm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminPetFragment extends Fragment {


    public AdminPetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_pet, container, false);
    }

}
