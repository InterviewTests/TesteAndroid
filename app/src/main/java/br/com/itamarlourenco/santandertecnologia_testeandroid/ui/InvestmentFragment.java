package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.app.ViewContract;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Funds;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.Intractors.GetFundsIntractorImpl;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomGraphic;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomTextView;

public class InvestmentFragment extends BaseFragment implements ViewContract.ViewActions<Funds> {

    private ViewContract.presenter presenter;
    private ProgressBar progressBar;
    private ConstraintLayout constraintLayout;

    public static InvestmentFragment newInstance(){
        return new InvestmentFragment();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        constraintLayout = view.findViewById(R.id.constraintLayout);
        presenter = new ViewPresenterImpl<Funds>(this, new GetFundsIntractorImpl());
        presenter.requestData();
        showProgress();
    }

    @Override
    protected int idLayoutFragment() {
        return R.layout.investiment_fragment;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        constraintLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        constraintLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDataToRecyclerView(Funds funds) {
        if(funds != null){
            populateView(funds);
        }
    }

    private void populateView(Funds funds) {
        View view = getView();
        if(view != null){
            ((CustomTextView) view.findViewById(R.id.investment_funds)).setText(funds.getTitle());
            ((CustomTextView) view.findViewById(R.id.secondTitle)).setText(funds.getFundName());
            ((CustomTextView) view.findViewById(R.id.whichIs)).setText(funds.getWhatIs());
            ((CustomTextView) view.findViewById(R.id.description)).setText(funds.getDefinition());
            ((CustomTextView) view.findViewById(R.id.degree)).setText(funds.getRiskTitle());
            ((CustomTextView) view.findViewById(R.id.moreInformation)).setText(funds.getInfoTitle());

            ((CustomGraphic) view.findViewById(R.id.graphic)).setStatus(Integer.parseInt(funds.getRisk()));

            Funds.MoreInfo moreInfo = funds.getMoreInfo();
            if(moreInfo != null){
                ((CustomTextView) view.findViewById(R.id.fund_month)).setText(String.format("%s%%", moreInfo.getMonth().getFund()));
                ((CustomTextView) view.findViewById(R.id.cdi_month)).setText(String.format("%s%%", moreInfo.getMonth().getCDI()));

                ((CustomTextView) view.findViewById(R.id.fund_year)).setText(String.format("%s%%", moreInfo.getYear().getFund()));
                ((CustomTextView) view.findViewById(R.id.cdi_year)).setText(String.format("%s%%", moreInfo.getYear().getCDI()));

                ((CustomTextView) view.findViewById(R.id.fund_12months)).setText(String.format("%s%%", moreInfo.getMonths12().getFund()));
                ((CustomTextView) view.findViewById(R.id.cdi_12months)).setText(String.format("%s%%", moreInfo.getMonths12().getCDI()));
            }

            LinearLayout table = view.findViewById(R.id.table);

            ArrayList<Funds.InfoSchema> infos = funds.getInfo();
            for(Funds.InfoSchema info: infos){
                View inflateView = LayoutInflater.from(getContext()).inflate(R.layout.layout_table_info, null);
                ((CustomTextView) inflateView.findViewById(R.id.name)).setText(info.getName());
                ((CustomTextView) inflateView.findViewById(R.id.data)).setText(info.getData());
                table.addView(inflateView);
            }

            ArrayList<Funds.InfoSchema> downInfos = funds.getDownInfo();
            for(Funds.InfoSchema downInfo: downInfos){
                View inflateView = LayoutInflater.from(getContext()).inflate(R.layout.layout_table_down_info, null);
                ((CustomTextView) inflateView.findViewById(R.id.name)).setText(downInfo.getName());
                table.addView(inflateView);
            }
        }
    }


    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), R.string.error_request_cell,Toast.LENGTH_LONG).show();
    }
}
