package com.example.budgetonthego;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements BalanceUpdateDialog.OnInputListener {
    float currentBalance;
    View.OnClickListener listener;
    SharedPreferences prefs;
    private Button updateBalanceButton;
    public TextView balanceView;
    public String mInput;

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

        balanceView.setText(String.valueOf(currentBalance));

        updateBalanceButton = findViewById(R.id.updateBalanceButton);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BalanceUpdateDialog dialog = new BalanceUpdateDialog();
                dialog.show(getSupportFragmentManager(), "BalanceUpdateDialog");
            }
        };
        updateBalanceButton.setOnClickListener(listener);
    }

    @Override
    public void sendInput(String input) {
        mInput = input;
        currentBalance = Float.parseFloat(input);
        setInputToTextView();
    }

    private void setInputToTextView() {
        balanceView.setText(mInput);

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
}
