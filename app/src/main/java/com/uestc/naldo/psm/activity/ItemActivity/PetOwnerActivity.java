package com.uestc.naldo.psm.activity.ItemActivity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.fragment.OwnerPetFragment;
import com.uestc.naldo.psm.model.PetItem;

public class PetOwnerActivity extends BaseActivity {

    public static String PET_ITEM_DATA = "pet_item_data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner);
        //获取点击事件的宠物数据
        final PetItem petItem = (PetItem) getIntent().getSerializableExtra(PET_ITEM_DATA);
        int petId = petItem.getId();
        String petName = petItem.getName();
        int petAge = petItem.getAge();
        String petSex = petItem.getSex();
        String petSpecies = petItem.getSpecies();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pet_detail);
        setSupportActionBar(toolbar);


        //绑定View
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        TextView text_pet_name = (TextView)findViewById(R.id.text_pet_name);
        TextView text_pet_age = (TextView)findViewById(R.id.text_pet_age);
        TextView text_pet_sex = (TextView)findViewById(R.id.text_pet_sex);
        TextView text_pet_species = (TextView)findViewById(R.id.text_pet_species);
        TextView text_pet_start = (TextView)findViewById(R.id.text_course_inst);
        TextView text_pet_end = (TextView)findViewById(R.id.text_course_condition);
        TextView text_pet_courese = (TextView)findViewById(R.id.text_course_duration);
        TextView text_pet_hr = (TextView)findViewById(R.id.text_pet_hr);

        //绑定点击事件传来的数据
        collapsingToolbar.setTitle(petName);
        text_pet_name.setText(petName);
        text_pet_age.setText(String.valueOf(petAge));
        text_pet_sex.setText(petSex);
        text_pet_species.setText(petSpecies);

        //查询数据库获得数据并绑定



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_star);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
