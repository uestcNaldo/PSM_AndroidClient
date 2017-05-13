package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.PetItemAdapter;
import com.uestc.naldo.psm.model.PetItem;

import java.util.ArrayList;
import java.util.List;

//用于宠物主人查看在校宠物信息

public class PetOwnerListActivity extends BaseActivity {

    private String TAG = "PetOwnerListActivity";
    private List<PetItem> petItemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //模拟初始化数据
        initPetItemList();
        //从数据库中读取


        //设置RecyclerView
        RecyclerView recyclerView_PetItemList = (RecyclerView) findViewById(R.id.recycler_view_petlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_PetItemList.setLayoutManager(layoutManager);
        //设置Adapater，以上可复用
        PetItemAdapter petItemAdapter = new PetItemAdapter(petItemList);
        recyclerView_PetItemList.setAdapter(petItemAdapter);


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
    private void initPetItemList() {
        petItemList.add(new PetItem(1,"狗狗1",2,"公","金毛"));
        petItemList.add(new PetItem(2,"狗狗2",3,"公","金毛"));
        petItemList.add(new PetItem(3,"狗狗3",1,"母","金毛"));
        petItemList.add(new PetItem(4,"狗狗4",4,"公","金毛"));
        petItemList.add(new PetItem(5,"狗狗5",2,"母","金毛"));
        petItemList.add(new PetItem(6,"狗狗6",3,"公","金毛"));
    }


}
