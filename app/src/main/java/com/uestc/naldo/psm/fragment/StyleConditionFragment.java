package com.uestc.naldo.psm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.StyleAdapter;
import com.uestc.naldo.psm.model.Photo;
import com.uestc.naldo.psm.model.StyleItem;

import java.util.ArrayList;
import java.util.List;


public class StyleConditionFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    private StyleAdapter styleAdapter;
    private List<StyleItem> styleItemList = new ArrayList<>();

    private List<Photo> photoListAll = new ArrayList<>();
    public StyleConditionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_style_condition, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView_StyleItemList = (RecyclerView) getActivity().findViewById(R.id.recyclerview_conditionStyle);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView_StyleItemList.setLayoutManager(layoutManager);
        styleAdapter = new StyleAdapter(photoListAll);
        recyclerView_StyleItemList.setAdapter(styleAdapter);
    }


}
