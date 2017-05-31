package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.TrainerItemAdapter;
import com.uestc.naldo.psm.model.Trainer;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TrainerListActivity extends BaseActivity {

    private List<Trainer> trainerItemList = new ArrayList<>();
    private TrainerItemAdapter trainerItemAdapter;
    private RecyclerView recyclerView_TrainerItemList;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/gettrainerlistall";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_trainerlist);
        setSupportActionBar(toolbar);

        recyclerView_TrainerItemList = (RecyclerView) findViewById(R.id.recyclerview_trainerItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_TrainerItemList.setLayoutManager(layoutManager);
        trainerItemAdapter = new TrainerItemAdapter(trainerItemList);
        recyclerView_TrainerItemList.setAdapter(trainerItemAdapter);


        getTrainerListAll();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void getTrainerListAll() {
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
                                Toast.makeText(MyApplication.getContext(), "获取训练师列表失败,请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseData = response.body().string();

                        Gson gson = new Gson();
                        final List<Trainer> trainerListAll = gson.fromJson(responseData, new TypeToken<List<Trainer>>(){}.getType());

                        for (Trainer trainer : trainerListAll){
                            trainerItemList.add(trainer);
                        }
                        trainerItemAdapter.notifyDataSetChanged();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (trainerListAll.size()!=0){
                                    Toast.makeText(MyApplication.getContext(), "获取训练师列表成功", Toast.LENGTH_SHORT).show();

                                }
                                if (trainerListAll.size()==0){
                                    Toast.makeText(MyApplication.getContext(), "训练师列表为空", Toast.LENGTH_SHORT).show();
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

    private void initTrainerItemList() {
//        trainerItemList.add(new TrainerItem(1, "唐科", "男", "高级训犬师"));
//        trainerItemList.add(new TrainerItem(2, "刘鹅", "女", "训犬师"));
//        trainerItemList.add(new TrainerItem(3, "范伟", "男", "训犬师"));

    }

}
