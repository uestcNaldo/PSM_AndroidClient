package com.uestc.naldo.psm.activity.CommitActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.json.OperationResult;
import com.uestc.naldo.psm.model.Owner;
import com.uestc.naldo.psm.util.DateUtils;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OwnerFeedbackCommitActivity extends BaseActivity{

    private Owner owner;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/sendfeedback";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private EditText text_feedback_title;
    private EditText text_feedback_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_feedback_commit);
        owner = (Owner) getIntent().getSerializableExtra("owner");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text_feedback_title = (EditText) findViewById(R.id.text_feedback_title);
        text_feedback_content = (EditText) findViewById(R.id.text_feedback_content);


        //完成
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送反馈
                sendFeedback();
            }
        });

        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void sendFeedback() {
        final String date = DateUtils.getTodayDate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                RequestBody requestBody = new FormBody.Builder()
                        .add("title", text_feedback_title.getText().toString())
                        .add("content", text_feedback_content.getText().toString())
                        .add("date", date)
                        .add("ownerId", String.valueOf(owner.getId()))
                        .build();
                Request request = builder.post(requestBody).url(URL).build();
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
                        final OperationResult sendFeedbackResult = gson.fromJson(responseData, OperationResult.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (sendFeedbackResult.getCode() == 1){
                                    Toast.makeText(MyApplication.getContext(), sendFeedbackResult.getMessage(), Toast.LENGTH_SHORT).show();

                                    finish();
                                }
                                if (sendFeedbackResult.getCode() == 0){
                                    Toast.makeText(MyApplication.getContext(), sendFeedbackResult.getMessage(), Toast.LENGTH_SHORT).show();
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
