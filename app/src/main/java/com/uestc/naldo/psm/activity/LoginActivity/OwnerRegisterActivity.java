package com.uestc.naldo.psm.activity.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.json.OperationResult;
import com.uestc.naldo.psm.model.Owner;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OwnerRegisterActivity extends BaseActivity {

    private Owner owner = new Owner();

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/register";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private EditText mNameView;
    private EditText mEmailView;
    private EditText mAgeView;
    private RadioGroup mSexGroup;
    private Button mRegisterButton;

    private final String SEX_MALE = "男";
    private final String SEX_FEMALE = "女";

    private final String TAG = "OwnerRegisterActivity";
    private boolean SEX_TAG = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mUsernameView = (AutoCompleteTextView) findViewById(R.id.owner_username);
        mPasswordView = (EditText) findViewById(R.id.owner_password);
        mNameView = (EditText) findViewById(R.id.owner_name);
        mEmailView = (EditText) findViewById(R.id.owner_email);
        mAgeView = (EditText) findViewById(R.id.owner_age);
        mSexGroup = (RadioGroup) findViewById(R.id.radio_group_sex);

        mSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.action_sex_choose_male:{
                        owner.setSex(SEX_MALE);
                        SEX_TAG = true;
                        break;
                    }
                    case R.id.action_sex_choose_female:{
                        owner.setSex(SEX_FEMALE);
                        SEX_TAG = true;
                        break;
                    }
                }
            }
        });

        mRegisterButton = (Button) findViewById(R.id.owner_register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //处理注册
                try {
                    if (mUsernameView.getText().toString() == null || mPasswordView.getText().toString() == null ||
                            mNameView.getText().toString() ==null ||mEmailView.getText().toString() == null ||
                            mAgeView.getText().toString() == null || SEX_TAG == false){
                        Toast.makeText(OwnerRegisterActivity.this, "注册信息不完整，请补充", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    owner.setUsername(mUsernameView.getText().toString());
                    owner.setPassword(mPasswordView.getText().toString());
                    owner.setName(mNameView.getText().toString());
                    owner.setEmail(mEmailView.getText().toString());
                    owner.setAge(Integer.valueOf(mAgeView.getText().toString()));

                    doRegister();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void doRegister() throws IOException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                RequestBody requestBody = new FormBody.Builder()
                        .add("username", owner.getUsername())
                        .add("password", owner.getPassword())
                        .add("name", owner.getName())
                        .add("email", owner.getEmail())
                        .add("sex", owner.getSex())
                        .add("age", String.valueOf(owner.getAge()))
                        .build();

                final Request request = builder.post(requestBody).url(URL).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        Log.d(TAG, "onFailure: Failure");
                        e.printStackTrace();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(OwnerRegisterActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String responseData = response.body().string();
                        System.out.println("Response = "+response.headers().toString());
                        System.out.println("responseData = "+responseData+", Length = "+responseData.length());

                        Gson gson = new Gson();
                        final OperationResult registerResult = gson.fromJson(responseData.trim(), OperationResult.class);
                        Log.d(TAG, "onResponse: "+registerResult.getOpt()+registerResult.getCode()+registerResult.getMessage());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (registerResult.getCode()==1){
                                    Toast.makeText(OwnerRegisterActivity.this, "账号注册成功，请登录", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(OwnerRegisterActivity.this, OwnerLoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                if (registerResult.getCode()==0){
                                    Toast.makeText(OwnerRegisterActivity.this, "账号注册失败，用户名重复，请重新输入", Toast.LENGTH_LONG).show();
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
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
        }
        return true;
    }

}
