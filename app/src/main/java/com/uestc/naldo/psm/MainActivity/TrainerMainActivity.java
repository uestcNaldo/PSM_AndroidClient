package com.uestc.naldo.psm.MainActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.uestc.naldo.psm.Fragment.TrainerInfoFragment;
import com.uestc.naldo.psm.Fragment.TrainerPetFragment;
import com.uestc.naldo.psm.Fragment.TrainerSchoolFragment;
import com.uestc.naldo.psm.R;


public class TrainerMainActivity extends AppCompatActivity {

    private TrainerPetFragment mTrainerPetFragment;
    private TrainerSchoolFragment mTrainerSchoolFragment;
    private TrainerInfoFragment mTrainerInfoFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.trainer_navigation_pet:
                    replaceFragment(mTrainerPetFragment);
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

        initView();
        replaceFragment(mTrainerPetFragment);
    }

    private void initView(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.trainer_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mTrainerPetFragment = new TrainerPetFragment();
        mTrainerSchoolFragment = new TrainerSchoolFragment();
        mTrainerInfoFragment = new TrainerInfoFragment();


    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.trainer_main_container,fragment);
        transaction.commit();

    }
}
