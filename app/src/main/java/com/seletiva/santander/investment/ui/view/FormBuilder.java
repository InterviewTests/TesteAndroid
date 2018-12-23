package com.seletiva.santander.investment.ui.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class FormBuilder {
    private LayoutInflater inflater;
    private LinearLayout formContainer;
    private List<FormComponentView> innerComponents;

    public FormBuilder(Form form) throws FormBuilderException {
        if (form == null) {
            throw new FormBuilderException(FormBuilderException.FormBuilderExceptionCode.INVALID_FORM_LISTENER);
        }

        prepareInflateAndContainer(form);
        innerComponents = new ArrayList<>();
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

    public FormBuilder addView(FormComponentView view) {
        innerComponents.add(view);
        formContainer.addView(view);
        return this;
    }

    public void removeAllChilds() {
        formContainer.removeAllViews();
    }

    public int getChildCount() {
        return formContainer.getChildCount();
    }

    public List<FormComponentView> getInnerComponents() {
        return innerComponents;
    }
}
