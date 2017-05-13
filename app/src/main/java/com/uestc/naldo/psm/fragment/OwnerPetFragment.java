package com.uestc.naldo.psm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.PetItemAdapter;
import com.uestc.naldo.psm.model.PetDialogItem;
import com.uestc.naldo.psm.model.PetItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naldo on 2017/4/17.
 */

public class OwnerPetFragment extends Fragment {

    private static final String TAG = "OwnerPetFragment";
    public static List<PetItem> petItemList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        initPetItemList();

    }

    private void initPetItemList() {
        petItemList.add(new PetItem(1,"狗狗1",2,"公","金毛"));
        petItemList.add(new PetItem(2,"狗狗2",3,"公","金毛"));
        petItemList.add(new PetItem(3,"狗狗3",1,"母","金毛"));
        petItemList.add(new PetItem(4,"狗狗4",4,"公","金毛"));
        petItemList.add(new PetItem(5,"狗狗5",2,"母","金毛"));
        petItemList.add(new PetItem(6,"狗狗6",3,"公","金毛"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_owner_pet, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toolbar toolbar_owner_pet = (Toolbar) getActivity().findViewById(R.id.toolbar_owner_pet);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_owner_pet);

        RecyclerView recyclerView_PetItemList = (RecyclerView) getActivity().findViewById(R.id.recycler_view_pet);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView_PetItemList.setLayoutManager(layoutManager);
        PetItemAdapter petItemAdapter = new PetItemAdapter(petItemList);
        recyclerView_PetItemList.setAdapter(petItemAdapter);


        FloatingActionButton fab_add = (FloatingActionButton)getActivity().findViewById(R.id.fab_owner_pet_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectPetDialog();
                Toast.makeText(getActivity(), "FAB ADD Clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu_owner_pet, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_button_add:{
                //Add Pet

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showSelectPetDialog(){
        DialogFragment dialogFragment = new SelectPetDialogFragment();
        dialogFragment.show(getFragmentManager(), "select_pet");
    }


}
