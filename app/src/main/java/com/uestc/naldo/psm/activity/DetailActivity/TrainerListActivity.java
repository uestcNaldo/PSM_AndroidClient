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
import com.uestc.naldo.psm.adapter.TrainerItemAdapter;
import com.uestc.naldo.psm.model.TrainerItem;

import java.util.ArrayList;
import java.util.List;

public class TrainerListActivity extends BaseActivity {

    private List<TrainerItem> trainerItemList = new ArrayList<>();
    private TrainerItemAdapter trainerItemAdapter;
    private RecyclerView recyclerView_TrainerItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_trainerlist);
        setSupportActionBar(toolbar);

        //初始化预设数居
        initTrainerItemList();

        recyclerView_TrainerItemList = (RecyclerView) findViewById(R.id.recyclerview_trainerItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_TrainerItemList.setLayoutManager(layoutManager);
        trainerItemAdapter = new TrainerItemAdapter(trainerItemList);
        recyclerView_TrainerItemList.setAdapter(trainerItemAdapter);


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

    private void initTrainerItemList() {
        trainerItemList.add(new TrainerItem(1, "训练师1", "男", "训犬师"));
        trainerItemList.add(new TrainerItem(1, "训练师2", "男", "高级训犬师"));
        trainerItemList.add(new TrainerItem(1, "训练师3", "男", "训犬师"));
        trainerItemList.add(new TrainerItem(1, "训练师4", "女", "主管"));
        trainerItemList.add(new TrainerItem(1, "训练师5", "男", "训犬师"));
        trainerItemList.add(new TrainerItem(1, "训练师6", "女", "训犬师"));
    }

}
