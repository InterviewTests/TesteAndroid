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
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
         setTextColor(context.getResources().getColor(R.color.gray));
        setId(View.generateViewId());
    }

    public void setMarginTop(int marginTopPx) {
        params.setMargins(20,  Utils.convertDpToPixel(getContext(), marginTopPx), 20, 0 );
    }

    public void setBelow(Integer id) {
        if (id != null) {
            params.addRule(RelativeLayout.BELOW, id);
        }

    }
}
