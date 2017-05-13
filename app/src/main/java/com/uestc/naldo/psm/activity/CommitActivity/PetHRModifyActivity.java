package com.uestc.naldo.psm.activity.CommitActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.ItemActivity.PetTrainerActivity;
import com.uestc.naldo.psm.model.PetItem;

public class PetHRModifyActivity extends BaseActivity {

    public static String PET_HR_DATA_RETURN = "pet_hr_data_return";

    private PetItem petItem;
    private String petHR;

    private EditText editText_PetHR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_hrmodify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获取上一层Pet数据
        petItem = (PetItem) getIntent().getSerializableExtra(PetTrainerActivity.PET_HR_DATA);
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


                //将新的健康数据添加进intent
                Intent intent = new Intent();
                intent.putExtra(PET_HR_DATA_RETURN, editText_PetHR.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
            }
        }
        return true;
    }

}
