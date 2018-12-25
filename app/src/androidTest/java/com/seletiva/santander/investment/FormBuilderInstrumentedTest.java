package com.seletiva.santander.investment;

import android.app.Activity;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.seletiva.santander.investment.ui.form.domain.model.Cell;
import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;
import com.seletiva.santander.investment.ui.form.domain.model.CellType;
import com.seletiva.santander.investment.ui.form.MainFormActivity_;
import com.seletiva.santander.investment.ui.view.FormComponentView;
import com.seletiva.santander.investment.utils.FileUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class FormBuilderInstrumentedTest {
    private Activity activity;
    private CellHolder mainHolder;

    @Rule
    public ActivityTestRule<MainFormActivity_> rule  = new  ActivityTestRule<>(MainFormActivity_.class);

    @org.junit.Before
    public void setUp() throws Exception {
        activity = rule.getActivity();

        Gson gson = new Gson();
        String fileContent = FileUtils.loadData("cells.json", activity);
        mainHolder = gson.fromJson(fileContent, CellHolder.class);
    }

    @Test
    public void testActivityIsValid() {
        assertNotNull(activity);
    }

    @Test
    public void testCellHolder() {
        int totalHardCodedCells = 6;
        assertNotNull(mainHolder);
        assertEquals(mainHolder.getNumberOfCells(), totalHardCodedCells);
    }


    @Test
    @UiThreadTest
    public void testFormContainerIsEmpty() {
        formBuilder.removeAllChilds();
        assertEquals(formBuilder.getChildCount(), 0);
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
    @UiThreadTest
    public void testFormValidation() {
        boolean isFormValid = true;
        formBuilder.removeAllChilds();

        // Populate the form
        for(Cell cell:mainHolder.getCells()) {
            FormComponentView componentView = new FormComponentView(activity);
            componentView.inflateWithCellModel(cell);
            formBuilder.addView(componentView);
        }

        // Valiate component by component
        for(FormComponentView component:formBuilder.getInnerComponents()) {
            if (!component.isValid()) {
                isFormValid = false;
                break;
            }
        }

        assertFalse(isFormValid);
    }
}
