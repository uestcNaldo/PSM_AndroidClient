package com.uestc.naldo.psm.activity.ItemActivity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.Course;
import com.uestc.naldo.psm.model.Pet;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PetOwnerActivity extends BaseActivity {

    public static String PET_ITEM_DATA = "pet_item_data";

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/petgetcourse";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private Pet petItem;

    private TextView text_pet_name;
    private TextView text_pet_age;
    private TextView text_pet_sex;
    private TextView text_pet_species;
    private TextView text_pet_start;
    private TextView text_pet_end;
    private TextView text_pet_course;
    private TextView text_pet_hr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner);
        text_pet_name = (TextView)findViewById(R.id.text_pet_name);
        text_pet_age = (TextView)findViewById(R.id.text_pet_age);
        text_pet_sex = (TextView)findViewById(R.id.text_pet_sex);
        text_pet_species = (TextView)findViewById(R.id.text_pet_species);
        text_pet_start = (TextView)findViewById(R.id.text_course_inst);
        text_pet_end = (TextView)findViewById(R.id.text_course_condition);
        text_pet_course = (TextView)findViewById(R.id.text_course_duration);
        text_pet_hr = (TextView)findViewById(R.id.text_pet_hr);
        petItem = (Pet) getIntent().getSerializableExtra(PET_ITEM_DATA);
        Long petId = petItem.getId();
        String petName = petItem.getName();
        int petAge = petItem.getAge();
        String petSex = petItem.getSex();
        String petSpecies = petItem.getSpecies();
        Date petStart = petItem.getStart();
        Date petEnd = petItem.getEnd();
        String petHrStatus = petItem.getHrStatus();

        getCourse(petItem.getCid());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pet_detail);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        collapsingToolbar.setTitle(petName);


        text_pet_name.setText(petName);
        text_pet_age.setText(String.valueOf(petAge));
        text_pet_sex.setText(petSex);
        text_pet_species.setText(petSpecies);
        text_pet_start.setText(petStart.toString());
        text_pet_end.setText(petEnd.toString());
        text_pet_hr.setText(petHrStatus);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_star);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "编辑宠物信息", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
