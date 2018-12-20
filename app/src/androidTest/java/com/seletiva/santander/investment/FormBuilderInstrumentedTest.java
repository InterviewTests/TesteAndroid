package com.seletiva.santander.investment;

import android.app.Activity;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.seletiva.santander.investment.Models.Cell;
import com.seletiva.santander.investment.Models.CellHolder;
import com.seletiva.santander.investment.Models.CellType;
import com.seletiva.santander.investment.UI.Activities.MainActivity_;
import com.seletiva.santander.investment.UI.View.Form;
import com.seletiva.santander.investment.UI.View.FormBuilder;
import com.seletiva.santander.investment.UI.View.FormComponentView;
import com.seletiva.santander.investment.Utils.FileUtils;

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
    private FormBuilder formBuilder;
    private CellHolder mainHolder;

    @org.junit.Before
    public void setUp() throws Exception {
        activity = rule.getActivity();
        formBuilder = new FormBuilder((Form) activity);

        Gson gson = new Gson();
        String fileContent = FileUtils.loadData("cells.json", activity);
        mainHolder = gson.fromJson(fileContent, CellHolder.class);
    }

    @Test
    public void testActivityIsValid() {
        assertNotNull(activity);
    }

    @Test
    @UiThreadTest
    public void testFormContainerIsEmpty() {
        formBuilder.removeAllChilds();
        assertEquals(formBuilder.getChildCount(), 0);
    }

    @Test
    @UiThreadTest
    public void testBasicFormBuilder() {
        formBuilder.removeAllChilds();
        formBuilder.addViewById(R.layout.form_component_checkbox)
                .addViewById(R.layout.form_component_field)
                .addViewById(R.layout.form_component_text);

        assertEquals(formBuilder.getChildCount(), 3);
    }

    @Test
    @UiThreadTest
    public void testFormBuilderWithNonValidView() {
        final int randomInvalidViewId = -1000;

        formBuilder.removeAllChilds();
        formBuilder.addViewById(R.layout.form_component_checkbox)
                .addViewById(R.layout.form_component_field)
                .addViewById(randomInvalidViewId);

        assertEquals(formBuilder.getChildCount(), 2);
    }

    @Test
    @UiThreadTest
    public void testFormComponentView() {
        Cell cell = new Cell();
        cell.setType(CellType.field);

        FormComponentView recoveredView = new FormComponentView(activity);
        recoveredView.inflateWithCellModel(cell);

        assertNotNull(recoveredView.findViewById(R.id.textualInputData));
        assertNull(recoveredView.findViewById(R.id.textualDisplayData));
    }

    @Test
    @UiThreadTest
    public void testFullFormBuilder() {
        int totalHardCodedCells = 6;
        formBuilder.removeAllChilds();

        for(Cell cell:mainHolder.getCells()) {
            FormComponentView componentView = new FormComponentView(activity);
            componentView.inflateWithCellModel(cell);

            formBuilder.addView(componentView);
        }

        assertEquals(formBuilder.getChildCount(), totalHardCodedCells);
    }

    @Test
    public void testCellHolder() {
        int totalHardCodedCells = 6;
        assertNotNull(mainHolder);
        assertEquals(mainHolder.getNumberOfCells(), totalHardCodedCells);
    }
}
