package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.core.data.model.Cell;
import com.meteoro.testeandroid.core.data.model.Cells;
import com.meteoro.testeandroid.core.di.qualifers.IoScheduler;
import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsModelType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.CheckboxViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.FieldViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.ImageViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.SendViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.TextViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.TypeField;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class ConvertToCellsViewModelImpl implements ConvertToCellsViewModel {

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    @Inject
    public ConvertToCellsViewModelImpl(@IoScheduler Scheduler ioScheduler,
                                       @UiScheduler Scheduler uiScheduler) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public Observable<CellsViewModel> call(Observable<Cells> cellsObservable) {
        return cellsObservable
                .flatMap(this::convertToViewModel)
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler);
    }

    private Observable<CellsViewModel> convertToViewModel(Cells cells) {
        return Observable.just(cells)
                .map(this::convertToCellsViewModel);
    }

    private CellsViewModel convertToCellsViewModel(Cells cells) {
        List<CellsType> cellsTypes = new ArrayList<>();

        for (Cell cell : cells.cells()) {
            cellsTypes.add(convertToCellType(cell));
        }

        return new CellsViewModel()
                .cellsTypeList(cellsTypes);
    }

    private CellsType convertToCellType(Cell cell) {
        switch (cell.type()) {
            case 1:
                return convertToField(cell);
            case 2:
                return convertToText(cell);
            case 3:
                return convertToImage(cell);
            case 4:
                return convertToCheckbox(cell);
            case 5:
                return convertToSend(cell);
        }

        throw new IllegalArgumentException("Type convert not accept");
    }

    private FieldViewModel convertToField(Cell cell) {
        return new FieldViewModel()
                .id(cell.id())
                .message(cell.message())
                .topSpacing(cell.topSpacing())
                .typeField(convertTypeField(cell.typeField()))
                .required(cell.required());
    }

    private TypeField convertTypeField(String type) {
        if ("1".equalsIgnoreCase(type)) {
            return TypeField.TEXT;
        } else if ("3".equalsIgnoreCase(type)) {
            return TypeField.EMAIL;
        } else if ("telnumber".equalsIgnoreCase(type)) {
            return TypeField.TEL_NUMBER;
        }

        return null;
    }

    private TextViewModel convertToText(Cell cell) {
        return new TextViewModel()
                .id(cell.id())
                .message(cell.message())
                .topSpacing(cell.topSpacing())
                .required(cell.required());
    }

    private ImageViewModel convertToImage(Cell cell) {
        return new ImageViewModel()
                .id(cell.id())
                .message(cell.message())
                .topSpacing(cell.topSpacing())
                .required(cell.required());
    }

    private CheckboxViewModel convertToCheckbox(Cell cell) {
        return new CheckboxViewModel()
                .id(cell.id())
                .message(cell.message())
                .topSpacing(cell.topSpacing())
                .required(cell.required());
    }

    private SendViewModel convertToSend(Cell cell) {
        return new SendViewModel()
                .id(cell.id())
                .message(cell.message())
                .topSpacing(cell.topSpacing())
                .required(cell.required());
    }
}
