package com.dhananjay.cashkaro_poc.ui.fragments.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.dhananjay.cashkaro_poc.App;
import com.dhananjay.cashkaro_poc.R;

/**
 * DialogFragment class for showing dialog with single button extends {@link DialogFragment}
 *
 * @author Dhananjay Kumar
 */
public class OneButtonDialogFragment extends DialogFragment {

    private static final String TAG = OneButtonDialogFragment.class.getSimpleName();
    private static final String ARG_CONTENT = "content";
    private static final String ARG_CANCELABLE = "cancelable";
    private static final String ARG_TITLE = "title";
    private String title;
    private String content;
    private DialogInterface.OnClickListener onClickListener;

    public static void show(FragmentManager fm, String title, String content, boolean cancelable, DialogInterface.OnClickListener onClickListener) {
        OneButtonDialogFragment oneButtonDialogFragment = new OneButtonDialogFragment();

        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CONTENT, content);
        args.putBoolean(ARG_CANCELABLE, cancelable);
        oneButtonDialogFragment.setArguments(args);
        oneButtonDialogFragment.setOnClickListener(onClickListener);

        oneButtonDialogFragment.show(fm, TAG);
    }

    public static void show(FragmentManager fm, int title, int content, boolean cancelable, DialogInterface.OnClickListener onClickListener) {
        show(fm, App.getInstance().getString(title), App.getInstance().getString(content), cancelable, onClickListener);
    }

    public static void show(FragmentManager fm, int title, int content, boolean cancelable) {
        show(fm, App.getInstance().getString(title), App.getInstance().getString(content), cancelable, null);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        title = getArguments().getString(ARG_TITLE);
        content = getArguments().getString(ARG_CONTENT);
        return createDialog();
    }

    private AlertDialog createDialog() {
        if (onClickListener == null) {
            onClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            };
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setPositiveButton(getString(R.string.ok), onClickListener);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(content)) {
            builder.setMessage(content);
        }

        AlertDialog materialDialog = builder.create();

        if (!getArguments().getBoolean(ARG_CANCELABLE)) {
            builder.setCancelable(false);
        }

        return materialDialog;
    }

    public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}