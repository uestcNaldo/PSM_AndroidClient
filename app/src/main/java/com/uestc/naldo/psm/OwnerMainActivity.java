package com.uestc.naldo.psm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

public class OwnerMainActivity extends AppCompatActivity {

    private Fragment mCurrentFragment;
    private ArrayList<Fragment> fragmentArrayList;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.owner_navigation_pet:

//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.owner_navigation_school:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.owner_navigation_information:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }//Hide ActionBar

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.owner_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void initFragment(){
        fragmentArrayList = new ArrayList<Fragment>(3);
        fragmentArrayList.add(new OwnerPetFragment());
        fragmentArrayList.add(new OwnerSchoolFragment());
        fragmentArrayList.add(new OwnerInfoFragment());



    }

}
