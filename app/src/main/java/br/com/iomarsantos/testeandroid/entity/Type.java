package br.com.iomarsantos.testeandroid.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
// Enumerate valid values for this interface
@IntDef({Type.FIELD, Type.TEXT, Type.IMAGE,
        Type.CHECKBOX, Type.BUTTON})
// Create an interface for validating int types
public @interface Type {
    // Declare the constants
    int FIELD = 1;

    int TEXT = 2;

    int IMAGE = 3;

    int CHECKBOX = 4;

    int BUTTON = 5;

}
