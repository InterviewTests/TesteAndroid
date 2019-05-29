package com.example.alessandrofsouza.santanderapp.presentation.utils;

import android.util.Log;

import com.example.alessandrofsouza.santanderapp.presentation.pages.contact.ContactContract;
import com.example.alessandrofsouza.santanderapp.presentation.pages.contact.ContactFragment;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final String TAG = "Santander ";
    private static ContactContract contract;
    private static ContactFragment contactFragment;

    public static final Pattern PHONE8_PATTERN = Pattern.compile(
            ("^[+]?[0-9]{10}$")
    );

    public static final Pattern PHONE9_PATTERN = Pattern.compile(
            ("^[+]?[0-9]{11}$")
    );


    public static boolean validatePhone(CharSequence phone) {
        return phone != null && PHONE8_PATTERN.matcher(phone).matches() || phone != null && PHONE9_PATTERN.matcher(phone).matches();
    }

}
