package com.uestc.naldo.psm.activity.LoginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
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
import com.uestc.naldo.psm.activity.MainActivity.OwnerMainActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.json.LoginResult;
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

/**
 * A login screen that offers login via email/password.
 */
public class OwnerLoginActivity extends BaseActivity {

    private Owner owner = new Owner();

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    // UI references.
    private TextInputLayout mUsernameWrapper;
    private TextInputLayout mPasswordWrapper;
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private CheckBox rememberPass;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/ownerlogin";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private final String REMEMBER_PASSWORD = "remember_owner_password";
    private final String USERNAME_DEFAULT = "owner";
    private final String PASSWORD_DEFAULT = "1234567890";

    private final String USERNAME_KEY = "owner_username";
    private final String PASSWORD_KEY = "owner_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);
        //获取SharedPreferences对象
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.owner_username);
        //populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.owner_password);

        mUsernameWrapper = (TextInputLayout) findViewById(R.id.owner_username_wrapper);
        mPasswordWrapper = (TextInputLayout) findViewById(R.id.owner_password_wrapper);
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

        Button mOwnerSignInButton = (Button) findViewById(R.id.owner_sign_in_button);
        mOwnerSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUsernameView.getText().toString() == null || mPasswordView.getText().toString() == null){
                    Toast.makeText(OwnerLoginActivity.this, "用户名和密码输入不完整，请继续输入",Toast.LENGTH_SHORT).show();
                    return;
                }

                owner.setUsername(mUsernameView.getText().toString());
                owner.setPassword(mPasswordView.getText().toString());

                doLogin();


            }
        });

        Button mOwnerRegisterButton = (Button) findViewById(R.id.owner_register_button);
        mOwnerRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerLoginActivity.this, OwnerRegisterActivity.class);
                startActivity(intent);

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
                        .add("username", owner.getUsername())
                        .add("password", owner.getPassword())
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
                                Toast.makeText(OwnerLoginActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData =response.body().string();
                        Gson gson = new Gson();
                        final LoginResult loginResult = gson.fromJson(responseData, LoginResult.class);

                        final Owner ownerResult = gson.fromJson(loginResult.getUser().toString(), Owner.class);
                        Log.d("onResponse:", ownerResult.getId()+ownerResult.getUsername()+ownerResult.getPassword()+ownerResult.getName());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loginResult.getCode()==1){
                                    Toast.makeText(OwnerLoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                                    editor = pref.edit();
                                    if (rememberPass.isChecked()){//检查记住密码是否选中
                                        editor.putBoolean(REMEMBER_PASSWORD, true);
                                        editor.putString(USERNAME_KEY, owner.getUsername());
                                        editor.putString(PASSWORD_KEY, owner.getPassword());
                                    }else {
                                        editor.clear();
                                    }
                                    editor.apply();
                                    Intent intent = new Intent(OwnerLoginActivity.this, OwnerMainActivity.class);
                                    intent.putExtra("owner",ownerResult);
                                    startActivity(intent);
                                    finish();

                                }
                                if (loginResult.getCode()==0){
                                    Toast.makeText(OwnerLoginActivity.this, "登录失败，用户名密码错误", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

                    }
                });

            }
        }).start();

    }

//    private void populateAutoComplete() {
//        if (!mayRequestContacts()
// ) {
//            return;
//        }
//
//        getLoaderManager().initLoader(0, null, this);
//    }
//
//    private boolean mayRequestContacts() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            return true;
//        }
//        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        }
//        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
//            Snackbar.make(mUsernameView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
//                    .setAction(android.R.string.ok, new View.OnClickListener() {
//                        @Override
//                        @TargetApi(Build.VERSION_CODES.M)
//                        public void onClick(View v) {
//                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//                        }
//                    });
//        } else {
//            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//        }
//        return false;
//    }
//
//    /**
//     * Callback received when a permissions request has been completed.
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_READ_CONTACTS) {
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                populateAutoComplete();
//            }
//        }
//    }
//
//
//    /**
//     * Attempts to sign in or register the account specified by the login form.
//     * If there are form errors (invalid email, missing fields, etc.), the
//     * errors are presented and no actual login attempt is made.
//     */
//    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }
//
//        // Reset errors.
//        mUsernameView.setError(null);
//        mPasswordView.setError(null);
//
//        // Store values at the time of the login attempt.
//        String username = mUsernameView.getText().toString();
//        String password = mPasswordView.getText().toString();
//
//        boolean cancel = false;
//        View focusView = null;
//
//        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            mPasswordView.setError(getString(R.string.error_invalid_password));
//            focusView = mPasswordView;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(username)) {
//            mUsernameView.setError(getString(R.string.error_field_required));
//            focusView = mUsernameView;
//            cancel = true;
//        } else if (!isUsernameValid(username)) {
//            mUsernameView.setError(getString(R.string.error_invalid_email));
//            focusView = mUsernameView;
//            cancel = true;
//        }
//
//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {
//            // Show a progress spinner, and kick off a background task to
//            // perform the user login attempt.
//            showProgress(true);
//            mAuthTask = new UserLoginTask(username, password);
//            mAuthTask.execute((Void) null);
//        }
//    }
//
//    private boolean isUsernameValid(String username) {
//        //TODO: Replace this with your own logic
//        return username.contains("@");
//    }
//
//    private boolean isPasswordValid(String password) {
//        //TODO: Replace this with your own logic
//        return password.length() > 4;
//    }
//
//    /**
//     * Shows the progress UI and hides the login form.
//     */
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//    private void showProgress(final boolean show) {
//        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
//        // for very easy animations. If available, use these APIs to fade-in
//        // the progress spinner.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
//
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });
//
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mProgressView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//                }
//            });
//        } else {
//            // The ViewPropertyAnimator APIs are not available, so simply show
//            // and hide the relevant UI components.
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//        }
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//        return new CursorLoader(this,
//                // Retrieve data rows for the device user's 'profile' contact.
//                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
//                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,
//
//                // Select only email addresses.
//                ContactsContract.Contacts.Data.MIMETYPE +
//                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
//                .CONTENT_ITEM_TYPE},
//
//                // Show primary email addresses first. Note that there won't be
//                // a primary email address if the user hasn't specified one.
//                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
//        List<String> emails = new ArrayList<>();
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            emails.add(cursor.getString(ProfileQuery.ADDRESS));
//            cursor.moveToNext();
//        }
//
//        addEmailsToAutoComplete(emails);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> cursorLoader) {
//
//    }
//
//    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
//        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<>(OwnerLoginActivity.this,
//                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
//
//        mUsernameView.setAdapter(adapter);
//    }
//
//
//    private interface ProfileQuery {
//        String[] PROJECTION = {
//                ContactsContract.CommonDataKinds.Email.ADDRESS,
//                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
//        };
//
//        int ADDRESS = 0;
//        int IS_PRIMARY = 1;
//    }
//
//    /**
//     * Represents an asynchronous login/registration task used to authenticate
//     * the user.
//     */
//    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }
//
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }
//
//            // TODO: register the new account here.
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//            showProgress(false);
//
//            if (success) {
//                finish();
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
//        }
//    }
}

