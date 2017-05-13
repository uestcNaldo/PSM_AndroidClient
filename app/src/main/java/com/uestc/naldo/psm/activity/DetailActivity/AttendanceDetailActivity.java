package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
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
import com.uestc.naldo.psm.model.Attendance;

public class AttendanceDetailActivity extends BaseActivity {

    private Attendance attendance = new Attendance();
    private String status = null;

    private final String STATUS_NORMAL = "正常出勤";
    private final String STATUS_LATE = "迟到";
    private final String STATUS_LEAVE = "请假";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //设置数据，需要trainerId

        //绑定View
        TextView textView_AttendanceStatus = (TextView) findViewById(R.id.attendance_status);
        TextView textView_AttendanceDate = (TextView) findViewById(R.id.attendance_date);

        //访问数据库进行读取

        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
        }
        return true;
    }
}
