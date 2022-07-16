package com.example.budgetonthego;

import android.content.SharedPreferences;
import android.util.Log;

public class Utilities {
    public static float loadData(float currentBalance, SharedPreferences prefs) {
        SharedPreferences.Editor editor = prefs.edit();
        currentBalance = prefs.getFloat("balanceValue", 0);
        Log.d("currentBalanceAfterLoading", "current balance = " + currentBalance);
        editor.apply();
        return currentBalance;
    }

    public static void saveData(float currentBalance, SharedPreferences prefs) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat("balanceValue", currentBalance);
        editor.apply();
        Log.d("currentBalanceAfterSaving", "current balance = " + currentBalance);
    }
}
