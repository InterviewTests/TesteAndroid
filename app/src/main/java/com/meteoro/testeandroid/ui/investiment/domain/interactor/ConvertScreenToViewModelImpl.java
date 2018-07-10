package com.meteoro.testeandroid.ui.investiment.domain.interactor;

import com.meteoro.testeandroid.core.data.model.DownInfo;
import com.meteoro.testeandroid.core.data.model.FundInfo;
import com.meteoro.testeandroid.core.data.model.Info;
import com.meteoro.testeandroid.core.data.model.MoreInfo;
import com.meteoro.testeandroid.core.data.model.Screen;
import com.meteoro.testeandroid.core.data.model.ScreenVo;
import com.meteoro.testeandroid.core.di.qualifers.IoScheduler;
import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.ui.investiment.domain.model.DownInfoViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.FundInfoViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.HeaderViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.InfoViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ModelType;
import com.meteoro.testeandroid.ui.investiment.domain.model.MoreInfoViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;
import com.meteoro.testeandroid.ui.investiment.presentation.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class ConvertScreenToViewModelImpl implements ConvertScreenToViewModel {

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;
    private PercentFormatter percentFormatter;

    @Inject
    public ConvertScreenToViewModelImpl(@IoScheduler Scheduler ioScheduler,
                                        @UiScheduler Scheduler uiScheduler,
                                        PercentFormatter percentFormatter) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.percentFormatter = percentFormatter;
    }

    @Override
    public Observable<ScreenViewModel> call(Observable<ScreenVo> observable) {
        return observable
                .flatMap(this::convertToViewModel)
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler);
    }

    private Observable<ScreenViewModel> convertToViewModel(ScreenVo vo) {
        return Observable.just(vo)
                .map(this::convertScreenViewModel);
    }

    private ScreenViewModel convertScreenViewModel(ScreenVo vo) {
        Screen screen = vo.screen();

        List<ModelType> modelTypes = new ArrayList<>();
        modelTypes.add(convertHeaderViewModel(screen));
        modelTypes.addAll(convertListInfoViewModel(screen.info()));
        modelTypes.addAll(convertListDownInfoViewModel(screen.downInfo()));

        return new ScreenViewModel()
                .modelTypeList(modelTypes);
    }

    private HeaderViewModel convertHeaderViewModel(Screen screen) {
        return new HeaderViewModel()
                .title(screen.title())
                .fundName(screen.fundName())
                .whatIs(screen.whatIs())
                .definition(screen.definition())
                .riskTitle(screen.riskTitle())
                .risk(screen.risk())
                .infoTitle(screen.infoTitle())
                .moreInfoViewModel(convertMoreInfoViewModel(screen.moreInfo()));
    }


    private MoreInfoViewModel convertMoreInfoViewModel(MoreInfo moreInfo) {
        return new MoreInfoViewModel()
                .month(convertFundInfoViewModel(moreInfo.month()))
                .year(convertFundInfoViewModel(moreInfo.year()))
                .months12(convertFundInfoViewModel(moreInfo.months12()));
    }

    private FundInfoViewModel convertFundInfoViewModel(FundInfo fundInfo) {
        return new FundInfoViewModel()
                .fund(percentFormatter.format(fundInfo.fund()))
                .cdi(percentFormatter.format(fundInfo.cdi()));
    }

    private List<InfoViewModel> convertListInfoViewModel(List<Info> infoList) {
        List<InfoViewModel> list = new ArrayList<>();
        for (Info info : infoList) {
            list.add(convetInfoViewModel(info));
        }
        return list;
    }

    private InfoViewModel convetInfoViewModel(Info info) {
        return new InfoViewModel()
                .name(info.name())
                .data(info.data());
    }

    private List<DownInfoViewModel> convertListDownInfoViewModel(List<DownInfo> downInfoList) {
        List<DownInfoViewModel> list = new ArrayList<>();
        for (DownInfo downInfo : downInfoList) {
            list.add(convertDownInfoViewModel(downInfo));
        }
        return list;
    }

    private DownInfoViewModel convertDownInfoViewModel(DownInfo downInfo) {
        return new DownInfoViewModel()
                .name(downInfo.name())
                .data(downInfo.data());
    }
}
