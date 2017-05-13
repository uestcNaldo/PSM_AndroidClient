package com.uestc.naldo.psm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.activity.LoginActivity.AdminLoginActivity;
import com.uestc.naldo.psm.activity.LoginActivity.OwnerLoginActivity;
import com.uestc.naldo.psm.activity.LoginActivity.TrainerLoginActivity;
import com.uestc.naldo.psm.R;

public class SelectCharacterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);

        Button selectOwnerButton = (Button) findViewById(R.id.select_owner_button);
        selectOwnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectCharacterActivity.this, OwnerLoginActivity.class);
                startActivity(intent);
            }
        });

        Button selectTrainerButton = (Button) findViewById(R.id.select_trainer_button);
        selectTrainerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectCharacterActivity.this, TrainerLoginActivity.class);
                startActivity(intent);
            }
        });

        Button selectAdminButton = (Button) findViewById(R.id.select_admin_button);
        selectAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectCharacterActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
