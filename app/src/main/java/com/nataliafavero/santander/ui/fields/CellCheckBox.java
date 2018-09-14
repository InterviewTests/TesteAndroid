package com.nataliafavero.santander.ui.fields;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nataliafavero.santander.R;
import com.nataliafavero.santander.ui.utils.Utils;

/**
 * Created by nataliafavero on 14/09/18.
 */

public class CellCheckBox extends android.support.v7.widget.AppCompatCheckBox {

    private RelativeLayout.LayoutParams params;

    public CellCheckBox(Context context) {
        super(new ContextThemeWrapper(context, R.style.MyCheckbox));

         params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
         setTextColor(context.getResources().getColor(R.color.gray));
         setLayoutParams(params);
    }

    public void setMarginTop(int marginTopPx) {
        int marginInPx = Utils.convertDpToPixel(getContext(), 20);
        params.setMargins(marginInPx, Utils.convertDpToPixel(getContext(), marginTopPx), marginInPx, 0);
    }

    public void setBelow(Integer id) {
        if (id != null) {
            params.addRule(RelativeLayout.BELOW, id);
        }

    }
}
