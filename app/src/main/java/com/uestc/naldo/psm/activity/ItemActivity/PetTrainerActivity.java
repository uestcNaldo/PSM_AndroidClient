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

import com.google.gson.Gson;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.CommitActivity.PetHRModifyActivity;
import com.uestc.naldo.psm.model.Course;
import com.uestc.naldo.psm.model.Pet;
import com.uestc.naldo.psm.model.PetItem;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.sql.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PetTrainerActivity extends AppCompatActivity {

    public static final String PET_ITEM_TRAINER_DATA = "pet_item_tainer_data";
    public static final String PET_HR_DATA = "pet_hr_data";
    public static final String PET_HR = "pet_hr";
    private final int PET_HR_REQUESTCODE = 1;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/petgetcourse";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private Pet petItem;
    private TextView text_pet_hr;
    private TextView text_pet_course;
    private TextView text_pet_name;
    private TextView text_pet_age;
    private TextView text_pet_sex;
    private TextView text_pet_species;
    private TextView text_pet_start;
    private TextView text_pet_end;
    private String TAG = "PetTrainerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_trainer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //获取点击事件的数据
        petItem = (Pet) getIntent().getSerializableExtra(PET_ITEM_TRAINER_DATA);
        Long petId = petItem.getId();
        String petName = petItem.getName();
        Integer petAge = petItem.getAge();
        String petSex = petItem.getSex();
        String petSpecies = petItem.getSpecies();
        Date petStart = petItem.getStart();
        Date petEnd = petItem.getEnd();
        String petHrStatus = petItem.getHrStatus();


        //绑定View
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        text_pet_name = (TextView)findViewById(R.id.text_pet_name);
        text_pet_age = (TextView)findViewById(R.id.text_pet_age);
        text_pet_sex = (TextView)findViewById(R.id.text_pet_sex);
        text_pet_species = (TextView)findViewById(R.id.text_pet_species);
        text_pet_start = (TextView)findViewById(R.id.text_course_inst);
        text_pet_end = (TextView)findViewById(R.id.text_course_condition);
        text_pet_course = (TextView)findViewById(R.id.text_course_duration);
        text_pet_hr = (TextView)findViewById(R.id.text_pet_hr);

        //绑定从上一层的数据
        collapsingToolbar.setTitle(petName);
        text_pet_name.setText(petName);
        text_pet_age.setText(String.valueOf(petAge));
        text_pet_sex.setText(petSex);
        text_pet_species.setText(petSpecies);
        text_pet_start.setText(String.valueOf(petStart));
        text_pet_end.setText(String.valueOf(petEnd));
        text_pet_hr.setText(petHrStatus);

        getCourse(petItem.getCid());

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



    }

    private void getCourse(final Long cid) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request.Builder builder = new Request.Builder();

                    RequestBody requestBody = new FormBody.Builder()
                            .add("courseId", String.valueOf(cid))
                            .build();
                    Request request = builder.post(requestBody).url(URL).build();
                    Response response = okHttpClient.newCall(request).execute();

                    String responseData = response.body().string();

                    Gson gson = new Gson();
                    final Course course = gson.fromJson(responseData, Course.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text_pet_course.setText(course.getName());
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();

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
