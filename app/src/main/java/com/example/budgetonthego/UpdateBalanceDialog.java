package com.example.budgetonthego;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class UpdateBalanceDialog extends androidx.fragment.app.DialogFragment {
    public interface OnInputListener {
        void sendInput(String input);
    }

    private EditText inputBalanceDialog;
    private Button actionOk, actionCancel;
    public  OnInputListener onInputListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_balance_dialog, container, false);
        actionCancel = view.findViewById(R.id.update_balance_input_cancel);
        actionOk = view.findViewById(R.id.update_balance_input_confirm);
        inputBalanceDialog = view.findViewById(R.id.update_balance_input);

        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        actionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputBalanceDialog.getText().toString();
                onInputListener.sendInput(input);
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener) getActivity();
            Log.d("UpdateBalanceDialog", "OnInputListener good to go");
        } catch (ClassCastException e) {
            Log.e("onAttach", e.getMessage());
        }
    }
}
