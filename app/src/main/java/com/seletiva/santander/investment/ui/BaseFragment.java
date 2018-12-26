package com.seletiva.santander.investment.ui;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;

import com.seletiva.santander.investment.R;

import org.androidannotations.annotations.EFragment;

@EFragment
public class BaseFragment extends Fragment  {
    public void showTryAgainMessage(int messageId, AlertDialog.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.attention))
                .setMessage(messageId)
                .setCancelable(false)
                .setNegativeButton(R.string.ok, clickListener);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
