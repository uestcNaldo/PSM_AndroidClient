package com.uestc.naldo.psm.activity.ItemActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.CommitActivity.PetHRModifyActivity;
import com.uestc.naldo.psm.model.PetItem;

public class PetTrainerActivity extends AppCompatActivity {

    public static final String PET_ITEM_TRAINER_DATA = "pet_item_tainer_data";
    public static final String PET_HR_DATA = "pet_hr_data";
    public static final String PET_HR = "pet_hr";
    private final int PET_HR_REQUESTCODE = 1;

    private TextView text_pet_hr;

    private String TAG = "PetTrainerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_trainer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //获取点击事件的数据
        final PetItem petItem = (PetItem) getIntent().getSerializableExtra(PET_ITEM_TRAINER_DATA);
        int petId = petItem.getId();
        String petName = petItem.getName();
        int petAge = petItem.getAge();
        String petSex = petItem.getSex();
        String petSpecies = petItem.getSpecies();

        //绑定View
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        TextView text_pet_name = (TextView)findViewById(R.id.text_pet_name);
        TextView text_pet_age = (TextView)findViewById(R.id.text_pet_age);
        TextView text_pet_sex = (TextView)findViewById(R.id.text_pet_sex);
        TextView text_pet_species = (TextView)findViewById(R.id.text_pet_species);
        TextView text_pet_start = (TextView)findViewById(R.id.text_course_inst);
        TextView text_pet_end = (TextView)findViewById(R.id.text_course_condition);
        TextView text_pet_courese = (TextView)findViewById(R.id.text_course_duration);
        text_pet_hr = (TextView)findViewById(R.id.text_pet_hr);

        //绑定从上一层的数据
        collapsingToolbar.setTitle(petName);
        text_pet_name.setText(petName);
        text_pet_age.setText(String.valueOf(petAge));
        text_pet_sex.setText(petSex);
        text_pet_species.setText(petSpecies);

        //查询数据并绑定


        //注册点击修改健康状况信息事件
        View action_modify_hr = findViewById(R.id.action_modify_hr);
        action_modify_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetTrainerActivity.this, PetHRModifyActivity.class);
                Log.d(TAG, "onClick: Modify");
                intent.putExtra(PET_HR_DATA, petItem);
                intent.putExtra(PET_HR, text_pet_hr.getText().toString());

                startActivityForResult(intent, PET_HR_REQUESTCODE);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PET_HR_REQUESTCODE:{
                if (resultCode == RESULT_OK){
                    String petHR = data.getStringExtra(PetHRModifyActivity.PET_HR_DATA_RETURN);
                    text_pet_hr.setText(petHR);
                }
                break;
            }
            default:
        }
    }
}
