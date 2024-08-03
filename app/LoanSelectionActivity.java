package my.edu.utar.loanapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoanSelectionActivity extends AppCompatActivity {

    private String birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_selection);

        // Retrieve the birthdate from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        birthdate = sharedPreferences.getString("birthdate", "");

        findViewById(R.id.btn_housing_loan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanSelectionActivity.this, housingloan.class);
                intent.putExtra("birthdate", birthdate);  // Pass the birthdate to the next activity
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_personal_loan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanSelectionActivity.this, personalloan.class);
                intent.putExtra("birthdate", birthdate);  // Pass the birthdate to the next activity
                startActivity(intent);
            }
        });
    }
}
