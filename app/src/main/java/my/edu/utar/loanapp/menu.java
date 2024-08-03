package my.edu.utar.loanapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class menu extends AppCompatActivity {

    private static final String TAG = "MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up click listeners for loan options
        findViewById(R.id.personalloan).setOnClickListener(v -> {
            Log.d(TAG, "Personal Loan button clicked");
            openPersonalLoan();
        });
        findViewById(R.id.housing_loan).setOnClickListener(v -> {
            Log.d(TAG, "Housing Loan button clicked");
            openHousingLoan();
        });

        // Set up the username text
        TextView usernameText = findViewById(R.id.name_text);
        String username = getUsernameFromSharedPreferences();
        usernameText.setText(username);
    }

    private String getUsernameFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("username", "Guest");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit_info) {
            Intent intent = new Intent(menu.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEditUserInfoClick(View view) {
        Log.d(TAG, "Edit User Info button clicked");
        Intent intent = new Intent(menu.this, MainActivity.class);
        startActivity(intent);
    }

    private void openPersonalLoan() {
        Log.d(TAG, "openPersonalLoan method called");
        try {
            Intent intent = new Intent(menu.this, personalloan.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Failed to open Personal Loan activity", e);
        }
    }

    private void openHousingLoan() {
        Log.d(TAG, "openHousingLoan method called");
        try {
            Intent intent = new Intent(menu.this, housingloan.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Failed to open Housing Loan activity", e);
        }
    }
}
