package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.TrainerItemAdapter;
import com.uestc.naldo.psm.model.TrainerItem;

import java.util.ArrayList;
import java.util.List;

public class AttendanceTrainerListActivity extends BaseActivity {

    private List<TrainerItem> attendanceTrainerItemList = new ArrayList<>();
    private TrainerItemAdapter trainerItemAdapter;

    private RecyclerView recyclerView_AttendanceTrainerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_trainer_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initTrainerItemList();

        recyclerView_AttendanceTrainerList = (RecyclerView) findViewById(R.id.recyclerview_AttendanceTrainerList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_AttendanceTrainerList.setLayoutManager(layoutManager);

        trainerItemAdapter = new TrainerItemAdapter(attendanceTrainerItemList);
        recyclerView_AttendanceTrainerList.setAdapter(trainerItemAdapter);


        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initTrainerItemList() {
        attendanceTrainerItemList.add(new TrainerItem(1, "训练师1", "男", "训犬师"));
        attendanceTrainerItemList.add(new TrainerItem(1, "训练师2", "男", "高级训犬师"));
        attendanceTrainerItemList.add(new TrainerItem(1, "训练师3", "男", "训犬师"));
        attendanceTrainerItemList.add(new TrainerItem(1, "训练师4", "女", "主管"));
        attendanceTrainerItemList.add(new TrainerItem(1, "训练师5", "男", "训犬师"));
        attendanceTrainerItemList.add(new TrainerItem(1, "训练师6", "女", "训犬师"));
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

}
