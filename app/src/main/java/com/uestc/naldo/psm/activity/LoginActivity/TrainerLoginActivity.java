package com.uestc.naldo.psm.activity.LoginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.MainActivity.AdminMainActivity;
import com.uestc.naldo.psm.activity.MainActivity.OwnerMainActivity;
import com.uestc.naldo.psm.activity.MainActivity.TrainerMainActivity;
import com.uestc.naldo.psm.json.LoginResult;
import com.uestc.naldo.psm.model.Trainer;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A login screen that offers login via email/password.
 */
public class TrainerLoginActivity extends BaseActivity {

    private Trainer trainer = new Trainer();

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/trainerlogin";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private CheckBox rememberPass;

    private final String REMEMBER_PASSWORD = "remember_trainer_password";
    private final String USERNAME_DEFAULT = "trainer";
    private final String PASSWORD_DEFAULT = "1234567890";

    private final String USERNAME_KEY = "trainer_username";
    private final String PASSWORD_KEY = "trainer_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.trainer_username);

        mPasswordView = (EditText) findViewById(R.id.trainer_password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);

        //实现记住密码功能
        boolean isRemember = pref.getBoolean(REMEMBER_PASSWORD, false);
        if (isRemember){
            //将账号和密码都设置到输入框中
            String username = pref.getString(USERNAME_KEY, "");
            String password = pref.getString(PASSWORD_KEY, "");
            mUsernameView.setText(username);
            mPasswordView.setText(password);

            rememberPass.setChecked(true);
        }

        Button mTrainerSignInButton = (Button) findViewById(R.id.trainer_sign_in_button);
        mTrainerSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUsernameView.getText().toString() == null || mPasswordView.getText().toString() == null){
                    Toast.makeText(TrainerLoginActivity.this, "用户名和密码输入不完整，请继续输入",Toast.LENGTH_SHORT).show();
                    return;
                }

                trainer.setUsername(mUsernameView.getText().toString());
                trainer.setPassword(mPasswordView.getText().toString());

                doLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void doLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                final OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();

                RequestBody requestBody = new FormBody.Builder()
                        .add("username", trainer.getUsername())
                        .add("password", trainer.getPassword())
                        .build();

                Request request = builder.post(requestBody).url(URL).build();

                Call call = okHttpClient.newCall(request);


                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(TrainerLoginActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData =response.body().string();
                        Log.d("onResponse",responseData);
                        final Gson gson = new Gson();
                        final LoginResult loginResult = gson.fromJson(responseData, LoginResult.class);
                        final Trainer trainerResult = gson.fromJson(loginResult.getUser().toString(), Trainer.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loginResult.getCode()==1){
                                    Toast.makeText(MyApplication.getContext(), "登录成功", Toast.LENGTH_SHORT).show();

                                    editor = pref.edit();
                                    if (rememberPass.isChecked()){//检查记住密码是否选中
                                        editor.putBoolean(REMEMBER_PASSWORD, true);
                                        editor.putString(USERNAME_KEY, trainer.getUsername());
                                        editor.putString(PASSWORD_KEY, trainer.getPassword());
                                    }else {
                                        editor.clear();
                                    }
                                    editor.apply();
                                    Intent intent = new Intent(MyApplication.getContext(), TrainerMainActivity.class);
                                    intent.putExtra("trainer",trainerResult);
                                    startActivity(intent);
                                    finish();

                                }
                                if (loginResult.getCode()==0){
                                    Toast.makeText(MyApplication.getContext(), "登录失败，用户名密码错误", Toast.LENGTH_LONG).show();

                                }


                            }
                        });

                    }
                });

            }
        }).start();


    }


}

