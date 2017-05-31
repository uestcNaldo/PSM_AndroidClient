package com.uestc.naldo.psm.activity.CommitActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.ItemActivity.PetTrainerActivity;
import com.uestc.naldo.psm.json.OperationResult;
import com.uestc.naldo.psm.model.Pet;
import com.uestc.naldo.psm.model.PetItem;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PetHRModifyActivity extends BaseActivity {

    public static String PET_HR_DATA_RETURN = "pet_hr_data_return";

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/updatepethr";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private Pet petItem;
    private String petHR;

    private EditText editText_PetHR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_hrmodify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获取上一层Pet数据
        petItem = (Pet) getIntent().getSerializableExtra(PetTrainerActivity.PET_HR_DATA);
        petHR = getIntent().getStringExtra(PetTrainerActivity.PET_HR);//健康数据


        //绑定View
        TextView textView_PetName = (TextView) findViewById(R.id.text_pet_hr_name);
        editText_PetHR = (EditText) findViewById(R.id.edit_text_pet_hr);

        //设置来自上一层的数据
        textView_PetName.setText(petItem.getName());
        editText_PetHR.setText(petHR);

        //修改健康状况数据


        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pet_hrmodify, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
            case R.id.action_confirm_pet_hrmodify:{
                //点击确认修改事件
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        Request.Builder builder = new Request.Builder();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("petId", String.valueOf(petItem.getId()))
                                .add("petHR", editText_PetHR.getText().toString())
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
                                final OperationResult updatePetHRRsult = gson.fromJson(responseData, OperationResult.class);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (updatePetHRRsult.getCode() == 1){
                                            Toast.makeText(MyApplication.getContext(), updatePetHRRsult.getMessage(), Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent();
                                            intent.putExtra(PET_HR_DATA_RETURN, editText_PetHR.getText().toString());
                                            setResult(RESULT_OK, intent);
                                            finish();
                                        }
                                        if (updatePetHRRsult.getCode() == 0){

                                            Toast.makeText(MyApplication.getContext(), updatePetHRRsult.getMessage(), Toast.LENGTH_SHORT).show();

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
