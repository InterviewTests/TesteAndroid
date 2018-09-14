package com.nataliafavero.santander.ui.fields;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nataliafavero.santander.R;
import com.nataliafavero.santander.ui.utils.Utils;

/**
 * Created by nataliafavero on 14/09/18.
 */

public class CellButton extends android.support.v7.widget.AppCompatButton {

    private RelativeLayout.LayoutParams params;

    public CellButton(Context context) {
        super(context);
        params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                Utils.convertDpToPixel(getContext(), 50));

        setLayoutParams(params);
        setBackground(getContext().getResources().getDrawable(R.drawable.button_background));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStateListAnimator(null);
        }
        setAllCaps(false);
        setTextColor(getContext().getResources().getColor(R.color.white));
        setTextSize(16);

    }

    public void setMarginTop(int margin) {
        int marginInPx = Utils.convertDpToPixel(getContext(), 20);
        params.setMargins(marginInPx, Utils.convertDpToPixel(getContext(), margin), marginInPx, marginInPx);
    }

    public void setBelow(Integer id) {
        if (id != null) {
            params.addRule(RelativeLayout.BELOW, id);
        }

    }
}
