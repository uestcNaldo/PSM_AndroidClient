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
import com.uestc.naldo.psm.fragment.TrainerInfoFragment;
import com.uestc.naldo.psm.fragment.TrainerNotificationFragment;
import com.uestc.naldo.psm.fragment.TrainerSchoolFragment;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.Trainer;


public class TrainerMainActivity extends BaseActivity {


    public static Trainer trainer;

    private TrainerNotificationFragment mTrainerNotificationFragment;
    private TrainerSchoolFragment mTrainerSchoolFragment;
    private TrainerInfoFragment mTrainerInfoFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.trainer_navigation_notification:
                    replaceFragment(mTrainerNotificationFragment);
                    return true;
                case R.id.trainer_navigation_school:
                    replaceFragment(mTrainerSchoolFragment);
                    return true;
                case R.id.trainer_navigation_information:
                    replaceFragment(mTrainerInfoFragment);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_main);

        trainer = (Trainer) getIntent().getSerializableExtra("trainer");

        initView();
        initDefaultFragment(mTrainerSchoolFragment);
    }

    private void initView(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.trainer_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mTrainerNotificationFragment = new TrainerNotificationFragment();
        mTrainerSchoolFragment = new TrainerSchoolFragment();
        mTrainerInfoFragment = new TrainerInfoFragment();

    }
    private void initDefaultFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .add(R.id.trainer_main_container, fragment).commit();


    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.trainer_main_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
