package my.edu.utar.loanapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, birthdateEditText;
    private SharedPreferences sharedPreferences;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        setContentView(R.layout.activity_main);

        // Initialize UI components
        usernameEditText = findViewById(R.id.usernameEditText);
        birthdateEditText = findViewById(R.id.birthdateEditText);
        MaterialButton loginButton = findViewById(R.id.loginButton);

        // Initialize Calendar
        calendar = Calendar.getInstance();

        // Set up DatePicker for birthdate
        birthdateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Set the click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
                // Set login status to false indicating the user has logged in
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isFirstLogin", false);
                editor.apply();
                // Navigate to the menu page
                Intent intent = new Intent(MainActivity.this, menu.class);
                startActivity(intent);
                finish();
            }
        });

        // Load user information if it exists
        loadUserInformation();
    }

    private void saveUserInformation() {
        String username = usernameEditText.getText().toString().trim();
        String birthdate = birthdateEditText.getText().toString().trim();

        if (username.isEmpty() || birthdate.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("birthdate", birthdate);
        editor.apply();

        Toast.makeText(this, "User information saved", Toast.LENGTH_SHORT).show();
    }

    private void loadUserInformation() {
        String username = sharedPreferences.getString("username", "");
        String birthdate = sharedPreferences.getString("birthdate", "");

        if (!username.isEmpty() && !birthdate.isEmpty()) {
            usernameEditText.setText(username);
            birthdateEditText.setText(birthdate);
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; // In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        birthdateEditText.setText(sdf.format(calendar.getTime()));
    }
}
