package com.uestc.naldo.psm.activity.DetailActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uestc.naldo.psm.MyApplication;
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

public class OwnerDetailActivity extends AppCompatActivity {

    private Owner owner;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX_UPDATE = "/app/updateowner";
    private String URL_SUFFIX_GET = "/app/getowner";
    private String URL_UPDATE = URL_PROTOCOL+URL_IP+URL_SUFFIX_UPDATE;
    private String URL_GET = URL_PROTOCOL+URL_IP+URL_SUFFIX_GET;

    private TextView owner_username;
    private TextView owner_name;
    private TextView owner_password;
    private TextView owner_email;
    private TextView owner_age;
    private TextView owner_sex;
    private Button owner_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        owner_username = (TextView) findViewById(R.id.text_owner_username);
        owner_name = (TextView) findViewById(R.id.text_owner_name);
        owner_password = (TextView) findViewById(R.id.text_owner_password);
        owner_email = (TextView) findViewById(R.id.text_owner_email);
        owner_age = (TextView) findViewById(R.id.text_owner_age);
        owner_sex = (TextView) findViewById(R.id.text_owner_sex);
        owner_update = (Button) findViewById(R.id.owner_update_button);

        owner = (Owner) getIntent().getSerializableExtra("owner");

        owner_username.setText(owner.getUsername());
        owner_name.setText(owner.getName());
        owner_password.setText(owner.getPassword());
        owner_email.setText(owner.getEmail());
        owner_sex.setText(owner.getSex());
        owner_age.setText(String.valueOf(owner.getAge()));

        getOwner();

        owner_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOwner();
            }
        });

        owner_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(OwnerDetailActivity.this);
                AlertDialog.Builder dialog = new AlertDialog.Builder(OwnerDetailActivity.this);
                dialog.setTitle("输入新的姓名").setView(editText);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        owner_name.setText(editText.getText().toString());
                        owner.setName(owner_name.getText().toString());
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        owner_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(OwnerDetailActivity.this);
                AlertDialog.Builder dialog = new AlertDialog.Builder(OwnerDetailActivity.this);
                dialog.setTitle("输入新的密码").setView(editText);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        owner_password.setText(editText.getText().toString());
                        owner.setPassword(owner_password.getText().toString());
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        owner_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(OwnerDetailActivity.this);
                AlertDialog.Builder dialog = new AlertDialog.Builder(OwnerDetailActivity.this);
                dialog.setTitle("输入新的邮箱").setView(editText);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        owner_email.setText(editText.getText().toString());
                        owner.setEmail(owner_email.getText().toString());
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        owner_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(OwnerDetailActivity.this);
                AlertDialog.Builder dialog = new AlertDialog.Builder(OwnerDetailActivity.this);
                dialog.setTitle("输入新的年龄").setView(editText);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        owner_age.setText(editText.getText().toString());
                        owner.setAge(Integer.valueOf(owner_age.getText().toString()));
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getOwner() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                RequestBody requestBody = new FormBody.Builder()
                        .add("id", String.valueOf(owner.getId()))
                        .build();
                final Request request = builder.post(requestBody).url(URL_GET).build();
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
                        final Owner ownerResult = gson.fromJson(responseData, Owner.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (ownerResult != null){
                                    owner_username.setText(ownerResult.getUsername());
                                    owner_name.setText(ownerResult.getName());
                                    owner_password.setText(ownerResult.getPassword());
                                    owner_email.setText(ownerResult.getEmail());
                                    owner_sex.setText(ownerResult.getSex());
                                    owner_age.setText(String.valueOf(ownerResult.getAge()));
                                }
                                if (ownerResult == null){
                                    Toast.makeText(MyApplication.getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }
                });


            }
        }).start();

    }

    private void updateOwner() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                RequestBody requestBody = new FormBody.Builder()
                        .add("id", String.valueOf(owner.getId()))
                        .add("username", owner.getUsername())
                        .add("password", owner.getPassword())
                        .add("name", owner.getName())
                        .add("email", owner.getEmail())
                        .add("age", String.valueOf(owner.getAge()))
                        .add("sex", owner.getSex())
                        .build();
                final Request request = builder.post(requestBody).url(URL_UPDATE).build();
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
                        final OperationResult updateOwnerResult = gson.fromJson(responseData, OperationResult.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (updateOwnerResult.getCode() == 1){
                                    Toast.makeText(MyApplication.getContext(), updateOwnerResult.getMessage(), Toast.LENGTH_SHORT).show();

                                    finish();
                                }
                                if (updateOwnerResult.getCode() == 0){
                                    Toast.makeText(MyApplication.getContext(), updateOwnerResult.getMessage(), Toast.LENGTH_SHORT).show();

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
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
