package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.CourseItemAdapter;
import com.uestc.naldo.psm.model.Course;
import com.uestc.naldo.psm.model.CourseItem;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CourseListActivity extends BaseActivity {

    private List<Course> courseItemList = new ArrayList<>();
    private CourseItemAdapter courseItemAdapter;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/getcourselistall";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_courselist);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView_CourseItemList = (RecyclerView)findViewById(R.id.recyclerview_courseItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_CourseItemList.setLayoutManager(layoutManager);
        courseItemAdapter = new CourseItemAdapter(courseItemList);
        recyclerView_CourseItemList.setAdapter(courseItemAdapter);

        getCourseListAll();

        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getCourseListAll() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();

                Request request = builder.get().url(URL).build();

                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MyApplication.getContext(), "获取课程列表失败,请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseData = response.body().string();

                        Gson gson = new Gson();
                        final List<Course> courseListAll = gson.fromJson(responseData, new TypeToken<List<Course>>(){}.getType());

                        for (Course course : courseListAll){
                            courseItemList.add(course);
                        }
                        courseItemAdapter.notifyDataSetChanged();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (courseListAll.size()!=0){
                                    Toast.makeText(MyApplication.getContext(), "获取课程列表成功", Toast.LENGTH_SHORT).show();

                                }
                                if (courseListAll.size()==0){
                                    Toast.makeText(MyApplication.getContext(), "课程列表为空", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

            }
        }).start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void initCourseItemList(){
//        courseItemList.add(new CourseItem(1, "启蒙教育", "10天"));
//        courseItemList.add(new CourseItem(2, "家庭犬坏习惯就正训练", "15天到20天"));
//        courseItemList.add(new CourseItem(3, "基本服从训练", "15天到18天"));
//        courseItemList.add(new CourseItem(4, "行为改良训练", "20天到30天"));
//        courseItemList.add(new CourseItem(5, "中级训练", "35天到40天"));


    }
}
