package com.uestc.naldo.psm.activity.CommitActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.fragment.AdminNotificationFragment;
import com.uestc.naldo.psm.json.OperationResult;
import com.uestc.naldo.psm.model.Admin;
import com.uestc.naldo.psm.model.Notification;
import com.uestc.naldo.psm.model.NotificationItem;
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


public class NotificationCommitActivity extends BaseActivity {

    private Notification notificationItem;
    private Admin admin;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/sendnotifi";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private EditText editText_notifi_title;
    private EditText editText_notifi_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_commit);
        admin = (Admin) getIntent().getSerializableExtra("admin");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText_notifi_title = (EditText) findViewById(R.id.edit_text_notifi_title);
        editText_notifi_content = (EditText) findViewById(R.id.edit_text_notifi_content);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotifi();
//                AdminNotificationFragment.AddNotificationItem(notificationItem);

            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void sendNotifi() {
        final String date = DateUtils.getTodayDate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                RequestBody requestBody = new FormBody.Builder()
                        .add("title", editText_notifi_title.getText().toString())
                        .add("content", editText_notifi_content.getText().toString())
                        .add("adminId", String.valueOf(admin.getId()))
                        .add("date", date)
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
                        final OperationResult sendNotifiResult = gson.fromJson(responseData, OperationResult.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                AdminNotificationFragment.AddNotificationItem();
                                AdminNotificationFragment.notificationItemAdapter.notifyDataSetChanged();
                                if (sendNotifiResult.getCode() == 1){
                                    Toast.makeText(MyApplication.getContext(), sendNotifiResult.getMessage(), Toast.LENGTH_SHORT).show();

                                    finish();
                                }
                                if (sendNotifiResult.getCode() == 0){
                                    Toast.makeText(MyApplication.getContext(), sendNotifiResult.getMessage(), Toast.LENGTH_SHORT).show();
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
