package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.NotificationItem;

public class NotificationDetailActivity extends BaseActivity {

    public static String NOTIFI_ITEM_DATA = "notifi_item_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        NotificationItem notificationItem = (NotificationItem)getIntent().getSerializableExtra(NOTIFI_ITEM_DATA);
        int notifiId = notificationItem.getId();
        String notifiTitle = notificationItem.getTitle();
        String notifiDate = notificationItem.getDate();
        String notifiAuthor = notificationItem.getAuthor();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_notification_detail);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        TextView text_notifi_date = (TextView) findViewById(R.id.item_notification_detail_date);
        TextView text_notifi_author = (TextView) findViewById(R.id.item_notification_detail_author);
        TextView text_notifi_content = (TextView) findViewById(R.id.text_notifi_detail_content);

        collapsingToolbarLayout.setTitle(notifiTitle);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_notifi_Star);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
