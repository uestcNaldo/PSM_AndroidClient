package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.CourseItemAdapter;
import com.uestc.naldo.psm.model.CourseItem;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends BaseActivity {

    private List<CourseItem> courseItemList = new ArrayList<>();
    private CourseItemAdapter courseItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_courselist);
        setSupportActionBar(toolbar);

        //初始化预设值
        initCourseItemList();

        RecyclerView recyclerView_CourseItemList = (RecyclerView)findViewById(R.id.recyclerview_courseItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_CourseItemList.setLayoutManager(layoutManager);
        courseItemAdapter = new CourseItemAdapter(courseItemList);
        recyclerView_CourseItemList.setAdapter(courseItemAdapter);

        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
        courseItemList.add(new CourseItem(1, "启蒙教育", "10天"));
        courseItemList.add(new CourseItem(2, "家庭犬坏习惯就正训练", "15天到20天"));
        courseItemList.add(new CourseItem(3, "基本服从训练", "15天到18天"));
        courseItemList.add(new CourseItem(4, "行为改良训练", "20天到30天"));
        courseItemList.add(new CourseItem(5, "中级训练", "35天到40天"));


    }
}
