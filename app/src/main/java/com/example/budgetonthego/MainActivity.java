package com.example.budgetonthego;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements GotMoneyDialog.OnInputListener {
    float currentBalance;
    View.OnClickListener listener;
    SharedPreferences prefs;
    private Button gotMoneyButton;
    public TextView balanceView;
    public String mInput;
    public Intent intent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        Context context = getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        balanceView = (TextView) findViewById(R.id.balanceValue);
        Log.d("prefsContainsBalanceValue", "prefs.contains = " + prefs.contains("balanceValue"));
        if (prefs.contains("balanceValue")) {
            currentBalance = Utilities.loadData(currentBalance, prefs);
        } else {
            currentBalance = 0;
        }

        //TODO: ensure float has 2 places past decimal point
        //TODO: ensure input is a positive number
        balanceView.setText("$" + String.valueOf(currentBalance));

        gotMoneyButton = findViewById(R.id.gotMoneyButton);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotMoneyDialog dialog = new GotMoneyDialog();
                dialog.show(getSupportFragmentManager(), "GotMoneyDialog");
            }
        };
        gotMoneyButton.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_dropdown, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            intent = new Intent(this, SettingsMenu.class);
            Log.d("MenuItemSelected", "menu item settings selected");
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //TODO: test
        Utilities.saveData(currentBalance, prefs);
    }

    @Override
    public void onPause() {
        super.onPause();
        Utilities.saveData(currentBalance, prefs);
        Log.d("currentBalanceAfterPausing", "current balance = " + currentBalance);
    }

    public void sendInput(String input) {
        mInput = input;
        currentBalance += Float.parseFloat(input);
        updateInput("$" + currentBalance);
        saveInput();
    }

    private void saveInput() {
        Utilities.saveData(currentBalance, prefs);
    }

    private void updateInput(String input) {
        balanceView.setText(input);
    }
}
