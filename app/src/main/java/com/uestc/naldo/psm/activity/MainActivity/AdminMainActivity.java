package com.uestc.naldo.psm.activity.MainActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.fragment.AdminInfoFragment;
import com.uestc.naldo.psm.fragment.AdminNotificationFragment;
import com.uestc.naldo.psm.fragment.AdminSchoolFragment;
import com.uestc.naldo.psm.R;

public class AdminMainActivity extends BaseActivity {

    private AdminNotificationFragment mAdminNotificationFragment;
    private AdminSchoolFragment mAdminSchoolFragment;
    private AdminInfoFragment mAdminInfoFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.admin_navigation_notification:
                    replaceFragment(mAdminNotificationFragment);
                    return true;
                case R.id.admin_navigation_school:
                    replaceFragment(mAdminSchoolFragment);
                    return true;
                case R.id.admin_navigation_information:
                    replaceFragment(mAdminInfoFragment);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        initView();
        initDefaultFragment(mAdminSchoolFragment);
    }

    private void initView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mAdminNotificationFragment = new AdminNotificationFragment();
        mAdminSchoolFragment = new AdminSchoolFragment();
        mAdminInfoFragment = new AdminInfoFragment();

    }
    private void initDefaultFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .add(R.id.admin_main_container, fragment).commit();


    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.admin_main_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}
