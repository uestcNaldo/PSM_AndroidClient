package com.uestc.naldo.psm.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.uestc.naldo.psm.adapter.NotificationItemAdapter;
import com.uestc.naldo.psm.model.NotificationItem;

import java.util.ArrayList;
import java.util.List;


public class TrainerNotificationFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshNotification;
    private List<NotificationItem> notificationItemList = new ArrayList<>();

    public TrainerNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        notificationItemList.add(new NotificationItem(1, "通知标题", "2017-5-5", "Admin"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trainer_notification, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar_owner_pet = (Toolbar) getActivity().findViewById(R.id.toolbar_trainer_notification);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_owner_pet);

        RecyclerView recyclerView_NotifiItemList = (RecyclerView) getActivity().findViewById(R.id.recycler_view_notification);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView_NotifiItemList.setLayoutManager(layoutManager);
        NotificationItemAdapter notificationItemAdapter = new NotificationItemAdapter(notificationItemList);
        recyclerView_NotifiItemList.setAdapter(notificationItemAdapter);

        swipeRefreshNotification = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_notification);
        swipeRefreshNotification.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshNotification.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Toast.makeText(getActivity(),"Refresh Notification",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu_trainer_notification, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:{
                //Refresh Notification


                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
