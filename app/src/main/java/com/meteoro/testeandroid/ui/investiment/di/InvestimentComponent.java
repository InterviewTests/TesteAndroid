package com.meteoro.testeandroid.ui.investiment.di;

import com.meteoro.testeandroid.core.di.PerFragment;
import com.meteoro.testeandroid.core.di.component.LibraryComponent;
import com.meteoro.testeandroid.ui.investiment.presentation.InvestimentFragment;

import dagger.Component;

@PerFragment
@Component(
        dependencies = {LibraryComponent.class},
        modules = {InvestimentModule.class}
)
public interface InvestimentComponent {
    void inject(InvestimentFragment fragment);
}
