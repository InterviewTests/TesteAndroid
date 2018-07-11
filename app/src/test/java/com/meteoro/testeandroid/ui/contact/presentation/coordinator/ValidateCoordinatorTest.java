package com.meteoro.testeandroid.ui.contact.presentation.coordinator;

import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowResultValidate;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ValidateFields;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ValidateCoordinatorTest {

    @Mock
    ValidateFields validateFields;

    @Mock
    ShowResultValidate showResultValidate;

    ValidateCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ValidateCoordinator(
                        validateFields,
                        showResultValidate)
        );
    }

    @Test
    public void callValidateCoordinator_shouldExecuteInOrder() {
        when(validateFields.call(any()))
                .thenReturn(Observable.just(mock(CellsViewModel.class)));
        when(showResultValidate.call(any()))
                .thenReturn(Observable.just(mock(CellsViewModel.class)));

        InOrder callOrder = inOrder(
                validateFields,
                showResultValidate
        );

        Observable.just(mock(CellsViewModel.class))
                .compose(impl)
                .subscribe();

        callOrder.verify(validateFields).call(any());
        callOrder.verify(showResultValidate).call(any());
    }
}
