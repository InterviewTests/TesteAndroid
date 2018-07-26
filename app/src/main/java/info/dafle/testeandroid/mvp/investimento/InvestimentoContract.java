package info.dafle.testeandroid.mvp.investimento;

import info.dafle.testeandroid.BasePresenter;
import info.dafle.testeandroid.BaseView;
import info.dafle.testeandroid.model.Fund;

public interface InvestimentoContract {

    interface View extends BaseView<Presenter>{

        void buildLayout(Fund fund);

        void showMessage(String s);

        void showProgress();

        void hideProgress();
    }

    interface Presenter  extends BasePresenter {

    }
}
