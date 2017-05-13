package com.uestc.naldo.psm.activity.DetailActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;

public class SchoolDetailActivity extends BaseActivity {

    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_school);
        setSupportActionBar(toolbar);



    }
}
