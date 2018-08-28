package br.com.santander.testeandroid.utils;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;

import br.com.santander.testeandroid.contact.model.CellResponse;

public class UIUtils {

    public static void defineConstraints(ConstraintLayout constraintLayout, CellResponse cell,
                                         int startID, int endID, boolean isFirstElement) {

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        final int margin = 0;
        final int endSide = isFirstElement ? ConstraintSet.TOP : ConstraintSet.BOTTOM;

        constraintSet.connect(startID, ConstraintSet.LEFT, constraintLayout.getId(),
                ConstraintSet.LEFT, margin);

        constraintSet.connect(startID, ConstraintSet.RIGHT, constraintLayout.getId(),
                ConstraintSet.RIGHT, margin);

        constraintSet.connect(startID, ConstraintSet.TOP, endID, endSide,
                cell.getTopSpacing().intValue());

        constraintSet.applyTo(constraintLayout);
    }

}
