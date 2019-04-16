package br.com.ricardo.testeandroid.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardo.testeandroid.model.FundApp;
import br.com.ricardo.testeandroid.model.InvestmentInteractor;
import br.com.ricardo.testeandroid.model.Month;
import br.com.ricardo.testeandroid.model.Screen;
import br.com.ricardo.testeandroid.view.InvestmentView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestmentPresenterImpl implements InvestmentPresenter{

    private InvestmentView investmentView;
    private InvestmentInteractor investmentInteractor;

    private List<Screen> screenList;

    public InvestmentPresenterImpl(InvestmentInteractor investmentInteractor) {
        this.investmentInteractor = investmentInteractor;
    }

    @Override
    public void attachView(InvestmentView view) {
        investmentView = view;
    }

    @Override
    public void detachView() {
        investmentView = null;
    }

    @Override
    public void requestInvestmentInfos() {

        investmentInteractor.requestFundInfos().enqueue(new Callback<FundApp>() {
            @Override
            public void onResponse(Call<FundApp> call, Response<FundApp> response) {

                if(!response.isSuccessful()){
                    Log.i("TAG", "Erro: " + response.code());
                } else{

                    FundApp fundApp = response.body();

                    if(fundApp != null){

                        Screen screen = new Screen(fundApp.getScreen().getTitle(),fundApp.getScreen().getFundName(),
                                fundApp.getScreen().getWhatIs(), fundApp.getScreen().getDefinition(),
                                fundApp.getScreen().getRiskTitle(), fundApp.getScreen().getRisk(),
                                fundApp.getScreen().getInfoTitle(), fundApp.getScreen().getMoreInfo(),
                                fundApp.getScreen().getInfo(), fundApp.getScreen().getDownInfo());

                        investmentView.addTextInfos(screen);

                    } else {
                        Log.i("TAG", "Erro: Json vazio.");
                    }
                }
            }

            @Override
            public void onFailure(Call<FundApp> call, Throwable t) {
                Log.i("TAG", "Erro: Conexão falhou.");
            }
        });

    }
}
