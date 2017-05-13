package com.uestc.naldo.psm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.CommitActivity.NotificationCommitActivity;
import com.uestc.naldo.psm.adapter.NotificationItemAdapter;
import com.uestc.naldo.psm.model.NotificationItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminNotificationFragment extends Fragment {

    private static final String TAG = "AdminNotifiFragment";

    private SwipeRefreshLayout swipeRefreshNotification;

    private RecyclerView recyclerView_NotifiItemList;

    public static NotificationItemAdapter notificationItemAdapter;

    private static List<NotificationItem> notificationItemList = new ArrayList<>();

    public static void AddNotificationItem(NotificationItem notificationItem){
        notificationItemList.add(notificationItem);
        Log.d(TAG, "AddNotificationItem: title = "+notificationItem.getTitle()+
        "date = "+notificationItem.getDate());
    }

    public AdminNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initNotifiItemList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_notification, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Toolbar toolbar_owner_pet = (Toolbar) getActivity().findViewById(R.id.toolbar_admin_notification);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_owner_pet);

        recyclerView_NotifiItemList = (RecyclerView) getActivity().findViewById(R.id.recycler_view_notification);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView_NotifiItemList.setLayoutManager(layoutManager);
        notificationItemAdapter = new NotificationItemAdapter(notificationItemList);

        recyclerView_NotifiItemList.setAdapter(notificationItemAdapter);



        swipeRefreshNotification = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_notification);
        swipeRefreshNotification.setEnabled(false);
        swipeRefreshNotification.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshNotification.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Toast.makeText(getActivity(),"Refresh Notification",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab_edit = (FloatingActionButton)getActivity().findViewById(R.id.fab_admin_notification_add);
        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationCommitActivity.class);
                startActivity(intent);


                Toast.makeText(getActivity(), "Notification Commit", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu_admin_notification, menu);

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


    private void initNotifiItemList(){
        Log.d(TAG, "initNotifiItemList: initNotifiItemList");

        notificationItemList.add(new NotificationItem(1,"通知标题","2017-4-12","Admin1"));
        notificationItemList.add(new NotificationItem(2,"通知标题","2017-4-1","Admin1"));
        notificationItemList.add(new NotificationItem(3,"通知标题","2017-4-2","Admin1"));
        notificationItemList.add(new NotificationItem(4,"通知标题","2017-3-5","Admin1"));

    }

}
