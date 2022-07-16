package com.example.budgetonthego;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsMenu extends AppCompatActivity {
    float currentBalance;
    View.OnClickListener listener;
    SharedPreferences prefs;
    private Button updateBalanceButton;
    public TextView balanceView;
    public String input;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        Log.d("InsideSecondActivity", "inside second activity!");
        Context context = getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        balanceView = (TextView) findViewById(R.id.balanceValue);
        Log.d("prefsContainsBalanceValue", "prefs.contains = " + prefs.contains("balanceValue"));
        if (prefs.contains("balanceValue")) {
            currentBalance = Utilities.loadData(currentBalance, prefs);
        } else {
            currentBalance = 0;
        }

        balanceView.setText(String.valueOf(currentBalance));

        updateBalanceButton = findViewById(R.id.updateBalanceButton);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateBalanceDialog dialog = new UpdateBalanceDialog();
                dialog.show(getSupportFragmentManager(), "BalanceUpdateDialog");
            }
        };
        updateBalanceButton.setOnClickListener(listener);
    }
}
