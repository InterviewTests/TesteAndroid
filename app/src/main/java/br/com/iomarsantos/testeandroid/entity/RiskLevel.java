package br.com.iomarsantos.testeandroid.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
// Enumerate valid values for this interface
@IntDef({RiskLevel.LEVEL_ONE, RiskLevel.LEVEL_TWO, RiskLevel.LEVEL_THREE,
        RiskLevel.LEVEL_FOUR, RiskLevel.LEVEL_FIVE})
// Create an interface for validating int types
public @interface RiskLevel {
    // Declare the constants
    int LEVEL_ONE = 1;

    int LEVEL_TWO = 2;

    int LEVEL_THREE = 3;

    int LEVEL_FOUR = 4;

    int LEVEL_FIVE = 5;

}
