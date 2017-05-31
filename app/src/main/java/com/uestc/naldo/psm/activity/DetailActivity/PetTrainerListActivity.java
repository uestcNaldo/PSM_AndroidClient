package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.PetItemAdapter;
import com.uestc.naldo.psm.json.PetListResult;
import com.uestc.naldo.psm.model.Pet;
import com.uestc.naldo.psm.model.PetItem;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//用于训练师和管理员查看宠物列表
public class PetTrainerListActivity extends BaseActivity {

    private String TAG = "PetTrainerListActivity";
    private List<Pet> petItemList = new ArrayList<>();

    private PetItemAdapter petItemAdapter;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/getpetlistall";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_trainer_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView_PetItemList = (RecyclerView) findViewById(R.id.recyclerview_petlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_PetItemList.setLayoutManager(layoutManager);
        petItemAdapter = new PetItemAdapter(petItemList);
        recyclerView_PetItemList.setAdapter(petItemAdapter);



        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        getPetListAll();
    }

    private void getPetListAll() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();

                Request request = builder.url(URL).build();

                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MyApplication.getContext(), "获取宠物列表失败,请重试", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.d(TAG, "onResponse: "+responseData);
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                        final PetListResult petListResult = gson.fromJson(responseData, PetListResult.class);

//                        List<Pet> petList = gson.fromJson(String.valueOf(petListResult.getPetList()), new TypeToken<List<Pet>>(){}.getType());

                        petItemList.clear();
                        for (Pet pet : petListResult.getPetList()){
                            petItemList.add(pet);
                        }
//                        petItemList = petListResult.getPetList();


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                petItemAdapter.notifyDataSetChanged();
                                if (petListResult.getCode()==1){

                                    Toast.makeText(MyApplication.getContext(), "获取宠物列表成功", Toast.LENGTH_SHORT).show();

                                }
                                if (petListResult.getCode()==0){
                                    Toast.makeText(MyApplication.getContext(), "宠物列表为空", Toast.LENGTH_LONG).show();
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

}
