package com.example.pro4.smartbarbell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.common.SignInButton;

public class PreLoginActivity extends AppCompatActivity {

    private RelativeLayout mRLayout;
    private ImageView mLogoText;
    private SignInButton mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        mRLayout = findViewById(R.id.pre_login_layout);
        mLogoText = findViewById(R.id.logo_text);
        mSignInButton = findViewById(R.id.sign_in_button);

        mRLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(PreLoginActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
