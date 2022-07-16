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

public class SettingsMenu extends AppCompatActivity implements UpdateBalanceDialog.OnInputListener {
    float currentBalance;
    View.OnClickListener listener;
    SharedPreferences prefs;
    private TextView updateBalanceTextView;
    public TextView balanceView;
    public String mInput;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        Log.d("InsideSecondActivity", "inside second activity!");
        Context context = getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        updateBalanceTextView = findViewById(R.id.updateBalanceText);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateBalanceDialog dialog = new UpdateBalanceDialog();
                dialog.show(getSupportFragmentManager(), "BalanceUpdateDialog");
            }
        };
        updateBalanceTextView.setOnClickListener(listener);
    }

    @Override
    public void sendInput(String input) {
        mInput = input;
        currentBalance = Float.parseFloat(input);
        saveInput();
    }

    private void saveInput() {
        Utilities.saveData(currentBalance, prefs);
    }
}
