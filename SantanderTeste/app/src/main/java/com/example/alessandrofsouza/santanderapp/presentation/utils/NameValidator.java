package com.example.alessandrofsouza.santanderapp.presentation.utils;

import java.util.regex.Pattern;

public class NameValidator {

    public static final Pattern NAME_PATTERN = Pattern.compile(
            ("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$")
    );

    public static boolean validateName(CharSequence name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

}
