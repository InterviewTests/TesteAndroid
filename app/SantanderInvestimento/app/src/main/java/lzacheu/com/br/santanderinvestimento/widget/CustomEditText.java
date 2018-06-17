package lzacheu.com.br.santanderinvestimento.widget;

import android.content.Context;

/**
 * Created by luiszacheu on 6/17/18.
 */

public class CustomEditText extends android.support.design.widget.TextInputEditText {

    private boolean required;

    public CustomEditText(Context context) {
        super(context);
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
