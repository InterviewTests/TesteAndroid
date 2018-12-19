package com.seletiva.santander.investment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.UiThread;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.LinearLayout;

import com.seletiva.santander.investment.UI.Activities.MainActivity;
import com.seletiva.santander.investment.UI.Activities.MainActivity_;
import com.seletiva.santander.investment.UI.View.Form;
import com.seletiva.santander.investment.UI.View.FormBuilder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class FormBuilderInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity_> rule  = new  ActivityTestRule<>(MainActivity_.class);

    private Activity activity;
    private LinearLayout formContainer;
    private FormBuilder formBuilder;

    @org.junit.Before
    public void setUp() throws Exception {
        activity = rule.getActivity();
        formContainer = activity.findViewById(R.id.form_container);
        formBuilder = new FormBuilder((Form) activity);
    }

    @Test
    public void testActivityIsValid() {
        assertNotNull(activity);
    }

    @Test
    public void testFormContainerNotNull() {
        assertNotNull(formContainer);
    }

    @Test
    public void testFormContainerIsEmpty() {
        assertEquals(formContainer.getChildCount(),0);
    }

    @Test
    @UiThreadTest
    public void testBasicFormBuilder() {
        formBuilder.addViewById(R.layout.form_component_checkbox)
                .addViewById(R.layout.form_component_field)
                .addViewById(R.layout.form_component_text);

        assertEquals(formContainer.getChildCount(), 3);
    }

    @Test
    @UiThreadTest
    public void testFormBuilderWithNonValidView() {
        final int randomInvalidViewId = -1000;

        formBuilder.addViewById(R.layout.form_component_checkbox)
                .addViewById(R.layout.form_component_field)
                .addViewById(randomInvalidViewId);

        assertEquals(formContainer.getChildCount(), 2);
    }

    @Test
    @UiThreadTest
    public void testFormBuilderRecoverViewById() {
        final int viewByToRecovered = R.layout.form_component_field;
        View recoveredView = formBuilder.recoverViewById(viewByToRecovered);
        assertNotNull(recoveredView);
    }
}
