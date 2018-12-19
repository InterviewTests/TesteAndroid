package com.seletiva.santander.investment.UI.View;

import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class FormBuilder {
    private LayoutInflater inflater;
    private LinearLayout formContainer;

    public FormBuilder(Form form) throws FormBuilderException {
        if (form == null) {
            throw new FormBuilderException(FormBuilderException.FormBuilderExceptionCode.INVALID_FORM_LISTENER);
        }

        inflater = form.getLayoutInflater();
        formContainer = form.getFormContainer();

        if (inflater == null) {
            throw new FormBuilderException(FormBuilderException.FormBuilderExceptionCode.INVALID_LAYOUT_INFLATOR);
        } else if (formContainer == null) {
            throw new FormBuilderException(FormBuilderException.FormBuilderExceptionCode.INVALID_LAYOUT_CONTAINER);
        }
    }
}
