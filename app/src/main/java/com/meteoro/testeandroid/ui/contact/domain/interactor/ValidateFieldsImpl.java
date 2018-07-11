package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.core.di.qualifers.IoScheduler;
import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.FieldViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class ValidateFieldsImpl implements ValidateFields {

    private static final String PHONE_PATTERN = "^(\\(11\\) [9][0-9]{4}-[0-9]{4})|(\\(1[2-9]\\) [5-9][0-9]{3}-[0-9]{4})|(\\([2-9][1-9]\\) [5-9][0-9]{3}-[0-9]{4})$";
    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    @Inject
    public ValidateFieldsImpl(@IoScheduler Scheduler ioScheduler,
                              @UiScheduler Scheduler uiScheduler) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public Observable<CellsViewModel> call(Observable<CellsViewModel> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler)
                .flatMap(this::validate);
    }

    private Observable<CellsViewModel> validate(CellsViewModel viewModel) {
        viewModel.isAllValid(false);

        for (CellsType cellsType : viewModel.cellsTypeList()) {
            boolean isValid = validateCell(cellsType);
            if (!isValid) {
                return Observable.just(viewModel);
            }
        }

        viewModel.isAllValid(true);
        return Observable.just(viewModel);
    }

    private boolean validateCell(CellsType cellsType) {
        switch (cellsType.getType()) {
            case FIELD:
                return validateField((FieldViewModel) cellsType);
            default:
                return true;
        }
    }

    private boolean validateField(FieldViewModel viewModel) {
        if (!viewModel.required()) return true;

        switch (viewModel.typeField()) {
            case TEXT:
                return validateTypeText(viewModel);
            case EMAIL:
                return validateTypeEmail(viewModel);
            case TEL_NUMBER:
                return validateTypeTelNumber(viewModel);
            default:
                return true;
        }
    }

    private boolean validateTypeText(FieldViewModel viewModel) {
        String value = viewModel.valueField();

        viewModel.validated(true);
        if (value == null) {
            viewModel.isNotValid(true);
            return false;
        }

        value = value.trim();

        boolean isValid = !value.isEmpty();
        viewModel.isNotValid(!isValid);

        return isValid;
    }

    private boolean validateTypeEmail(FieldViewModel viewModel) {
        String value = viewModel.valueField();

        viewModel.validated(true);
        if (value == null) {
            viewModel.isNotValid(true);
            return false;
        }

        value = value.trim();

        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);

        boolean isValid = matcher.find();
        viewModel.isNotValid(!isValid);

        return isValid;
    }

    private boolean validateTypeTelNumber(FieldViewModel viewModel) {
        String value = viewModel.valueField();

        viewModel.validated(true);
        if (value == null) {
            viewModel.isNotValid(true);
            return false;
        }

        value = value.trim();

        Pattern pattern = Pattern.compile(PHONE_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);

        boolean isValid = matcher.find();
        viewModel.isNotValid(!isValid);

        return isValid;
    }
}
