package com.seletiva.santander.investment.UI.View;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class FormBuilder {
    private LayoutInflater inflater;
    private LinearLayout formContainer;

    public FormBuilder(Form form) throws FormBuilderException {
        if (form == null) {
            throw new FormBuilderException(FormBuilderException.FormBuilderExceptionCode.INVALID_FORM_LISTENER);
        }
        prepareInflateAndContainer(form);
    }

    private void prepareInflateAndContainer(Form form) throws FormBuilderException {
        inflater = form.getInflater();
        formContainer = form.getFormContainer();

        if (inflater == null) {
            throw new FormBuilderException(FormBuilderException.FormBuilderExceptionCode.INVALID_LAYOUT_INFLATOR);
        } else if (formContainer == null) {
            throw new FormBuilderException(FormBuilderException.FormBuilderExceptionCode.INVALID_LAYOUT_CONTAINER);
        }
    }

    public FormBuilder addViewById(int componentId) {
        try {
         View inflatedView = inflater.inflate(componentId, null);

            if (inflatedView != null) {
                inflatedView.setId(componentId);
                formContainer.addView(inflatedView);
            }
        } catch (Exception e) {
            Log.d(getClass().getCanonicalName(), "Impossible to inflate the view");
        }

        return this;
    }

    public FormBuilder addView(View view) {
        formContainer.addView(view);
        return this;
    }

    public void removeAllChilds() {
        formContainer.removeAllViews();
    }

    public int getChildCount() {
        return formContainer.getChildCount();
    }
}
