package com.uestc.naldo.psm.activity.ItemActivity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.Course;
import com.uestc.naldo.psm.model.CourseItem;

public class CourseActivity extends BaseActivity {

    public static String COURSE_ITEM_DATA = "course_item_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获取点击事件的课程数据
        Course courseItem = (Course) getIntent().getSerializableExtra(COURSE_ITEM_DATA);
        Long courseId = courseItem.getId();
        String courseName = courseItem.getName();
        String courseDuration = courseItem.getDuration();
        String courseInst = courseItem.getInst();
        String courseContent = courseItem.getContent();
        String courseCondition = courseItem.getCondition();
        String coursePrise = courseItem.getPrise();
        String courseNote = courseItem.getNote();


        //绑定View
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        TextView text_course_inst = (TextView) findViewById(R.id.text_course_inst);
        TextView text_course_condition = (TextView) findViewById(R.id.text_course_condition);
        TextView text_course_duration = (TextView) findViewById(R.id.text_course_duration);
        TextView text_course_content = (TextView) findViewById(R.id.text_course_content);
        TextView text_course_prise = (TextView) findViewById(R.id.text_course_prise);
        TextView text_course_note = (TextView) findViewById(R.id.text_course_note);


        //设置数据
        collapsingToolbarLayout.setTitle(courseName);
        text_course_inst.setText(courseInst);
        text_course_condition.setText(courseCondition);
        text_course_duration.setText(courseDuration);
        text_course_content.setText(courseContent);
        text_course_prise.setText(coursePrise);
        text_course_note.setText(courseNote);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_star);
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
        //设置返回键
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
