package my.edu.utar.loanapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000; // Duration for the splash screen in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if the user is logging in for the first time
                SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                boolean isFirstLogin = preferences.getBoolean("isFirstLogin", true);

                Intent intent;
                if (isFirstLogin) {
                    // Redirect to MainActivity for first-time login
                    intent = new Intent(SplashScreen.this, MainActivity.class);
                } else {
                    // Redirect to menu for existing user
                    intent = new Intent(SplashScreen.this, menu.class);
                }
                startActivity(intent);
                finish(); // Close the splash activity
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
