package com.uestc.naldo.psm.activity.CommitActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.Attendance;
import com.uestc.naldo.psm.model.TrainerItem;
import com.uestc.naldo.psm.util.DateUtils;

public class AttendanceRecordActivity extends BaseActivity {

    public static String TRAINER_ITEM_DATA = "trainer_item_data";
    private Attendance attendance = new Attendance();
    private String status = null;

    private final String STATUS_NORMAL = "正常出勤";
    private final String STATUS_LATE = "迟到";
    private final String STATUS_LEAVE = "请假";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获取点击事件后上一层的数据
        TrainerItem trainerItem = (TrainerItem) getIntent().getSerializableExtra(TRAINER_ITEM_DATA);
        int trainerId = trainerItem.getId();

        attendance.setTrainer_id(trainerId);


        //绑定View
        TextView textView_TrainerName = (TextView) findViewById(R.id.text_trainer_name);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.attendance_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.action_choose_normal:{
                        status = STATUS_NORMAL;
                        attendance.setStatus(status);
                        attendance.setDate(DateUtils.getTodayDate());
                        break;
                    }
                    case R.id.action_choose_late: {
                        status = STATUS_LATE;
                        attendance.setStatus(status);
                        attendance.setDate(DateUtils.getTodayDate());
                        break;
                    }
                    case R.id.action_choose_leave: {
                        status = STATUS_LEAVE;
                        attendance.setStatus(status);
                        attendance.setDate(DateUtils.getTodayDate());
                        break;
                    }
                }
            }
        });


        //设置界面数据
        textView_TrainerName.setText(trainerItem.getName());

        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_trainer_attendance, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
            case R.id.action_confirm_trainer_attendance:{
                //写入数据库

                finish();
                break;
            }
        }
        return true;
    }
}
