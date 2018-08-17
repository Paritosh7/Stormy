package com.example.paritosh.stormy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;

public class SabkaAlertDialogFragment extends DialogFragment {

    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_MESSAGE = "ARG_MESSAGE";

    @StringRes private int titleRes;
    @StringRes private int messageRes;

    public static SabkaAlertDialogFragment newInstance(@StringRes int title, @StringRes int message) {
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE, title);
        args.putInt(ARG_MESSAGE, message);
        SabkaAlertDialogFragment fragment = new SabkaAlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titleRes = getArguments().getInt(ARG_TITLE);
            messageRes = getArguments().getInt(ARG_MESSAGE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setTitle(titleRes)
                .setMessage(messageRes)
                .setPositiveButton(R.string.error_button_ok_text, null).create();

        return builder.create();
    }
}
