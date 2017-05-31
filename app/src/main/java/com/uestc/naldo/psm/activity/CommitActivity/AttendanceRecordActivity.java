package com.uestc.naldo.psm.activity.CommitActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.json.OperationResult;
import com.uestc.naldo.psm.model.Attendance;
import com.uestc.naldo.psm.model.AttendanceItem;
import com.uestc.naldo.psm.model.Trainer;
import com.uestc.naldo.psm.model.TrainerItem;
import com.uestc.naldo.psm.util.DateUtils;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.sql.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AttendanceRecordActivity extends BaseActivity {

    public static String TRAINER_ITEM_DATA = "trainer_item_data";
    private Attendance attendanceItem = new Attendance();
    private String status = null;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/updateattendance";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private final String STATUS_NORMAL = "正常出勤";
    private final String STATUS_LATE = "迟到";
    private final String STATUS_LEAVE = "请假";
    private Trainer trainerItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获取点击事件后上一层的数据
        trainerItem = (Trainer) getIntent().getSerializableExtra(TRAINER_ITEM_DATA);
        Long trainerId = trainerItem.getId();

        attendanceItem.setTrainerId(trainerId);
        attendanceItem.setDate(Date.valueOf(DateUtils.getTodayDate()));

        //绑定View
        TextView textView_TrainerName = (TextView) findViewById(R.id.text_trainer_name);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.attendance_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.action_choose_normal:{
                        status = STATUS_NORMAL;
                        attendanceItem.setStatus(status);

                        break;
                    }
                    case R.id.action_choose_late: {
                        status = STATUS_LATE;
                        attendanceItem.setStatus(status);

                        break;
                    }
                    case R.id.action_choose_leave: {
                        status = STATUS_LEAVE;
                        attendanceItem.setStatus(status);

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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        Request.Builder builder = new Request.Builder();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("trainerId", String.valueOf(attendanceItem.getTrainerId()))
                                .add("status", attendanceItem.getStatus())
                                .add("date", String.valueOf(attendanceItem.getDate()))
                                .build();
                        final Request request = builder.post(requestBody).url(URL).build();
                        Call call = okHttpClient.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MyApplication.getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String responseData = response.body().string();
                                Gson gson = new Gson();
                                final OperationResult updateAttendanceResult = gson.fromJson(responseData, OperationResult.class);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (updateAttendanceResult.getCode() == 1){
                                            Toast.makeText(MyApplication.getContext(), updateAttendanceResult.getMessage(), Toast.LENGTH_SHORT).show();

                                            finish();
                                        }
                                        if (updateAttendanceResult.getCode() == 0){

                                            Toast.makeText(MyApplication.getContext(), updateAttendanceResult.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                            }
                        });

                    }
                }).start();
                break;
            }
        }
        return true;
    }
}
