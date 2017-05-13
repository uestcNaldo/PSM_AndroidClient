package com.uestc.naldo.psm.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.StyleAdapater;
import com.uestc.naldo.psm.model.StyleItem;

import java.util.ArrayList;
import java.util.List;


public class StyleTeamFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    private StyleAdapater styleAdapater;
    private List<StyleItem> styleItemList = new ArrayList<>();

    public StyleTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_style_team, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initStyleItemList();
        RecyclerView recyclerView_StyleItemList = (RecyclerView) getActivity().findViewById(R.id.recyclerview_teamStyle);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView_StyleItemList.setLayoutManager(layoutManager);
        styleAdapater = new StyleAdapater(styleItemList);
        recyclerView_StyleItemList.setAdapter(styleAdapater);

    }


    public void initStyleItemList(){
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_1));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_2));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_3));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_4));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_5));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_6));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_7));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_8));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_10));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_11));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_12));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_13));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_14));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_16));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_17));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_18));
        styleItemList.add(new StyleItem("2016训犬团队", R.drawable.style_example_19));


    }
}
