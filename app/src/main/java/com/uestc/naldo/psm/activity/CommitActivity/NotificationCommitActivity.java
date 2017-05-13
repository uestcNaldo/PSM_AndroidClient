package com.uestc.naldo.psm.activity.CommitActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.fragment.AdminNotificationFragment;
import com.uestc.naldo.psm.model.NotificationItem;
import com.uestc.naldo.psm.util.DateUtils;


public class NotificationCommitActivity extends BaseActivity {

    private NotificationItem notificationItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_commit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //绑定View
        final EditText editText_notifi_title = (EditText) findViewById(R.id.edit_text_notifi_title);
        final EditText editText_notifi_content = (EditText) findViewById(R.id.edit_text_notifi_content);



        //完成
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = DateUtils.getTodayDate();
                String content = editText_notifi_content.getText().toString();
                notificationItem = new NotificationItem(1, editText_notifi_title.getText().toString(), date, "Admin");


                //存储到数据库中

                //

                AdminNotificationFragment.AddNotificationItem(notificationItem);
                AdminNotificationFragment.notificationItemAdapter.notifyDataSetChanged();
                finish();
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
