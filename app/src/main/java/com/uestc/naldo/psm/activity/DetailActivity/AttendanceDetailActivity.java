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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.Attendance;
import com.uestc.naldo.psm.model.Trainer;
import com.uestc.naldo.psm.util.DateUtils;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AttendanceDetailActivity extends BaseActivity {

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/getattendancelist";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private Attendance attendance = new Attendance();
    private String status = null;
    private Trainer trainer;
    private List<Attendance> mAttendanceList = new ArrayList<>();

    private TextView textView_AttendanceStatus;
    private TextView textView_AttendanceDate;
    private final String STATUS_NORMAL = "正常出勤";
    private final String STATUS_LATE = "迟到";
    private final String STATUS_LEAVE = "请假";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_detail);
        trainer = (Trainer) getIntent().getSerializableExtra("trainer");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //设置数据，需要trainerId

        //绑定View
        textView_AttendanceStatus = (TextView) findViewById(R.id.attendance_status);
        textView_AttendanceDate = (TextView) findViewById(R.id.attendance_date);

        getAttendanceList();

        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getAttendanceList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                RequestBody requestBody = new FormBody.Builder()
                        .add("trainerId", String.valueOf(trainer.getId()))
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
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                        final List<Attendance> attendanceList = gson.fromJson(responseData, new TypeToken<List<Attendance>>(){}.getType());
                        mAttendanceList = attendanceList;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (attendanceList.size()==0){
                                    Toast.makeText(MyApplication.getContext(), "没有考勤记录", Toast.LENGTH_SHORT).show();
                                }

                                for (Attendance attendance : attendanceList){
                                    if (attendance.getDate() == Date.valueOf(DateUtils.getTodayDate())){
                                        textView_AttendanceDate.setText(String.valueOf(attendance.getDate()));
                                        textView_AttendanceStatus.setText(attendance.getStatus());
                                    }
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
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
        }
        return true;
    }
}
