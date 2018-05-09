package rrzaniolo.testandroidsantander.utils;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.base.BaseInnerView;

/**
 * This class hold several utility methods.
 * */
public class Utils {

    /**
     * Method to check if a String is null or empty.
     * */
    public static Boolean isNotNullNorEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    /**
     * Method to load a InnerView.
     * */
    public static void loadFragment(BaseInnerView innerView, boolean addToBackStack, Bundle args, FragmentManager fragmentManager, int ViewGorupId) {
        innerView.setArguments(args);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        transaction.replace(ViewGorupId, innerView);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    /**
     * Method to apply phone mask.
     * */
    public static TextWatcher phoneMask(final EditText editText){
        return new TextWatcher() {
            String oldText = "";
            Boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String clear = removeMasks(s.toString());
                String mask = clear.length() == 10 ? "(##) ####-####" : "(##) #####-####";
                String masked = "";

                if(isUpdating){
                    oldText = clear;
                    isUpdating = false;
                    return;
                }

                int i=0;

                for (char c: mask.toCharArray()) {
                    if(c != '#' && clear.length() > oldText.length()) {
                        masked += c;
                        continue;
                    }

                    try{
                        masked += clear.charAt(i);
                    }catch (Exception e){
                        break;
                    }
                    i++;
                }

                isUpdating = true;
                editText.setText(masked);
                editText.setSelection(masked.length());

            }

            @Override
            public void afterTextChanged(Editable s) { }
        };
    }

    private static String removeMasks(String s){
        return s.replace(".", "")
                .replace("-", "")
                .replace("/", "")
                .replace("(", "")
                .replace(" ", "")
                .replace(":", "")
                .replace(")", "");
    }
}
