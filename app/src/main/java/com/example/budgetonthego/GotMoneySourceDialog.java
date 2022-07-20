package com.example.budgetonthego;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GotMoneySourceDialog extends androidx.fragment.app.DialogFragment {
    public interface OnInputListener {
        void sendInput(String input);
    }

    private Button actionOk, actionCancel;
    public GotMoneyDialog.OnInputListener onInputListener;
    private RadioGroup radioGroup;
    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.got_money_dialog_source, container, false);
        actionCancel = view.findViewById(R.id.got_money_source_balance_input_cancel);
        actionOk = view.findViewById(R.id.got_money_source_balance_input_confirm);
        radioGroup = view.findViewById(R.id.radio_group);
        populateRadioGroup(view);

        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        actionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String input = inputBalanceDialog.getText().toString();
                onInputListener.sendInput("blah");
                getDialog().dismiss();
            }
        });

        return view;
    }

    private ArrayList<RadioButton> populateRadioGroup(View view) {
        // create the arrayList
        ArrayList<RadioButton> radioButtonList = new ArrayList<RadioButton>();
        // context needed for prefs, then initialize prefs and get an editor to load the data
        Context context = getActivity().getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        // this line is what loads the json into our program
        String json = prefs.getString("radioButtons", "");

        // the next few lines convert the json to an arrayList
        Type type = new TypeToken<ArrayList<RadioButton>>(){}.getType();
        radioButtonList = gson.fromJson(json, type);
        Iterator<RadioButton> iterator = radioButtonList.iterator();

        // put the Radio Buttons on the screen
        while (iterator.hasNext()) {

        }

        return radioButtonList;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputListener = (GotMoneyDialog.OnInputListener) getActivity();
            Log.d("UpdateBalanceDialog", "OnInputListener good to go");
        } catch (ClassCastException e) {
            Log.e("onAttach", e.getMessage());
        }
    }
}
