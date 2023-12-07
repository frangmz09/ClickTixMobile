package com.davinci.clicktixmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.davinci.clicktixmobile.MainActivity;
import com.davinci.clicktixmobile.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 2000; // Tiempo en milisegundos

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);


        ImageView splashImage = findViewById(R.id.splashImage);



        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }, SPLASH_TIMEOUT);
    }
}
