package br.com.iomarsantos.testeandroid.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
// Enumerate valid values for this interface
@StringDef({TypeField.TEXT, TypeField.PHONE_NUMBER,
        TypeField.EMAIL})
// Create an interface for validating int types
public @interface TypeField {
    // Declare the constants
    String TEXT = "1";

    String PHONE_NUMBER = "telnumber";

    String EMAIL = "3";

}
