package info.dafle.testeandroid.mvp.investimento;

import info.dafle.testeandroid.model.Fund;

public interface InvestimentoContract {

    interface View {

        void buildLayout(Fund fund);

        void showMessage(String s);

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BaseP<View> {


    }

    interface BaseP<T> {

        void setView(T t);
    }
}
