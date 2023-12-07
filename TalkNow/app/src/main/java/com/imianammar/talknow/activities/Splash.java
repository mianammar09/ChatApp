package com.imianammar.talknow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.imianammar.talknow.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView gifImageView = findViewById(R.id.imageSplash);
        Glide.with(this)
                .load(R.raw.splash)
                .into(gifImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
