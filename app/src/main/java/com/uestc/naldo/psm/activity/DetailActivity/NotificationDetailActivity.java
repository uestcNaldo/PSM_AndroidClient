package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.Admin;
import com.uestc.naldo.psm.model.Notification;
import com.uestc.naldo.psm.model.NotificationItem;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.sql.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotificationDetailActivity extends BaseActivity {

    public static String NOTIFI_ITEM_DATA = "notifi_item_data";
    private Notification notificationItem;
    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/notifigetadmin";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;
    private TextView text_notifi_author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        notificationItem = (Notification)getIntent().getSerializableExtra(NOTIFI_ITEM_DATA);
        Long notifiId = notificationItem.getId();
        String notifiTitle = notificationItem.getTitle();
        Date notifiDate = notificationItem.getDate();
        String notifiContent = notificationItem.getContent();
        Long notifiAid = notificationItem.getAid();

        getAdmin(notifiAid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_notification_detail);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        TextView text_notifi_date = (TextView) findViewById(R.id.item_notification_detail_date);
        text_notifi_author = (TextView) findViewById(R.id.item_notification_detail_author);
        TextView text_notifi_content = (TextView) findViewById(R.id.text_notifi_detail_content);


        collapsingToolbarLayout.setTitle(notifiTitle);
        text_notifi_content.setText(notifiContent);
        text_notifi_date.setText(String.valueOf(notifiDate));



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_notifi_Star);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getAdmin(final Long aid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request.Builder builder = new Request.Builder();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("adminId", String.valueOf(aid))
                            .build();
                    Request request = builder.post(requestBody).url(URL).build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();

                    Gson gson = new Gson();
                    final Admin admin = gson.fromJson(responseData, Admin.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text_notifi_author.setText(admin.getName());
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);

    }
}
