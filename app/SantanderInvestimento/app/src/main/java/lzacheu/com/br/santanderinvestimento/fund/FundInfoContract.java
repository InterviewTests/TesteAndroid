package lzacheu.com.br.santanderinvestimento.fund;

import lzacheu.com.br.santanderinvestimento.base.BasePresenter;
import lzacheu.com.br.santanderinvestimento.base.BaseView;
import lzacheu.com.br.santanderinvestimento.model.fund.Screen;

/**
 * Created by luiszacheu on 6/16/18.
 */

public interface FundInfoContract {

    interface View extends BaseView<Presenter>{

        void showLoading();

        void hideLoading();

        void bindValues(Screen screen);
    }

    interface Presenter extends BasePresenter{

        void fetchData();
    }
}
