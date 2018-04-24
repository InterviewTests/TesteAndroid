package br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.investment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.ibm.santander.wallacebaldenebre.R;
import br.com.ibm.santander.wallacebaldenebre.model.Info;
import br.com.ibm.santander.wallacebaldenebre.model.MoreInfo;
import br.com.ibm.santander.wallacebaldenebre.model.Screen;

public class InvestmentFragment extends Fragment implements InvestmentContract.View, View.OnTouchListener {
    protected TextView tvInvestFund;
    protected TextView tvTitle;
    protected TextView tvWhatIs;
    protected TextView tvBodyWhatIs;
    protected TextView tvInvestRisk;
    protected TextView tvTitleMoreInfo;
    protected TextView tvFundMonth;
    protected TextView tvCDIMonth;
    protected TextView tvFundYear;
    protected TextView tvCDIYear;
    protected TextView tvFundTwelveMonth;
    protected TextView tvCDITwelveMonth;

    protected TextView tvAdmTax;
    protected TextView tvLabelAdmTax;
    protected TextView tvAppInitial;
    protected TextView tvLabelAppInitial;
    protected TextView tvMinMovement;
    protected TextView tvLabelMinMovement;
    protected TextView tvMinBalance;
    protected TextView tvLabelMinBalance;
    protected TextView tvSaving;
    protected TextView tvLabelSaving;
    protected TextView tvQuota;
    protected TextView tvLabelQuota;
    protected TextView tvPayment;
    protected TextView tvLabelPayment;

    protected TextView tvEssentials;
    protected TextView tvPerformance;
    protected TextView tvComplementary;
    protected TextView tvRegulamentary;
    protected TextView tvAccession;
    protected ImageView ivPointer1;
    protected ImageView ivPointer2;
    protected ImageView ivPointer3;
    protected ImageView ivPointer4;
    protected ImageView ivPointer5;
    protected Button btInvest;
    protected ConstraintLayout llAlert;
    protected ImageView ivCloseAlert;

    ArrayList<HashMap<String, String>> investmentDataList;
    ProgressDialog pd;

    protected InvestmentPresenter presenter;

    public InvestmentFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investment, container, false);

        setUp(view);

        showData();

        return view;
    }

    @Override
    public void showProgress() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pd = new ProgressDialog(getActivity());
                pd.setTitle("Aguarde...");
                pd.setMessage("Carregando os dados...");
                pd.setCancelable(true);
                pd.show();

                Handler hpd = new Handler();
                hpd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void hideProgress() {
        if (pd.isShowing()) pd.dismiss();
    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void showData() {
        presenter.showDataFromServer(this, new InvestmentCallback<HashMap<String, Screen>>() {
            @Override
            public void onSuccess(final HashMap<String, Screen> data) {
                Log.i("LOG", "onSuccess()");
                hideProgress();
                for (Map.Entry<String, Screen> entry : data.entrySet()) {
                    final Screen value = entry.getValue();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvInvestFund.setText(value.getTitle());
                            tvTitle.setText(value.getFundName());
                            tvWhatIs.setText(value.getWhatIs());
                            tvBodyWhatIs.setText(value.getDefinition());
                            tvInvestRisk.setText(value.getRiskTitle());

                            switch (value.getRisk()) {
                                case 1:
                                    setPointerVisible(1);
                                    break;
                                case 2:
                                    setPointerVisible(2);
                                    break;
                                case 3:
                                    setPointerVisible(3);
                                    break;
                                case 4:
                                    setPointerVisible(4);
                                    break;
                                case 5:
                                    setPointerVisible(5);
                                    break;
                                default:
                                    setPointerVisible(4);
                                    break;
                            }

                            tvTitleMoreInfo.setText(value.getInfoTitle());
                            tvFundMonth.setText(String.valueOf(value.getMoreInfo().get(0).getMonth().getFund()).concat("%"));
                            tvCDIMonth.setText(String.valueOf(value.getMoreInfo().get(0).getMonth().getCDI()).concat("%"));
                            tvFundYear.setText(String.valueOf(value.getMoreInfo().get(0).getYear().getFund()).concat("%"));
                            tvCDIYear.setText(String.valueOf(value.getMoreInfo().get(0).getYear().getCDI()).concat("%"));
                            tvFundTwelveMonth.setText(String.valueOf(value.getMoreInfo().get(0).getTwelveMonths().getFund()).concat("%"));
                            tvCDITwelveMonth.setText(String.valueOf(value.getMoreInfo().get(0).getTwelveMonths().getCDI()).concat("%"));

                            tvLabelAdmTax.setText(value.getInfos()[0].getName());
                            tvAdmTax.setText(value.getInfos()[0].getData());
                            tvLabelAppInitial.setText(value.getInfos()[1].getName());
                            tvAppInitial.setText(value.getInfos()[1].getData());
                            tvLabelMinMovement.setText(value.getInfos()[2].getName());
                            tvMinMovement.setText(value.getInfos()[2].getData());
                            tvLabelMinBalance.setText(value.getInfos()[3].getName());
                            tvMinBalance.setText(value.getInfos()[3].getData());
                            tvLabelSaving.setText(value.getInfos()[4].getName());
                            tvSaving.setText(value.getInfos()[4].getData());
                            tvLabelQuota.setText(value.getInfos()[5].getName());
                            tvQuota.setText(value.getInfos()[5].getData());
                            tvLabelPayment.setText(value.getInfos()[6].getName());
                            tvPayment.setText(value.getInfos()[6].getData());

                            tvEssentials.setText(value.getDownInfos()[0].getName());
                            tvPerformance.setText(value.getDownInfos()[1].getName());
                            tvComplementary.setText(value.getDownInfos()[2].getName());
                            tvRegulamentary.setText(value.getDownInfos()[3].getName());
                            tvAccession.setText(value.getDownInfos()[4].getName());
                        }
                    });
                }
            }

            @Override
            public void onFailure(int errorCode, String reason) {
                Log.i("LOG", "onFailure()");
                hideProgress();
            }
        });
    }

    private void setPointerVisible(int pos) {
        ivPointer1.setVisibility(pos == 1 ? View.VISIBLE : View.INVISIBLE);
        ivPointer1.setVisibility(pos == 2 ? View.VISIBLE : View.INVISIBLE);
        ivPointer1.setVisibility(pos == 3 ? View.VISIBLE : View.INVISIBLE);
        ivPointer1.setVisibility(pos == 4 ? View.VISIBLE : View.INVISIBLE);
        ivPointer1.setVisibility(pos == 5 ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void casts(View view) {
        tvInvestFund = view.findViewById(R.id.tvw_investment_title);
        tvTitle = view.findViewById(R.id.tvw_investment_titlemaster);
        tvWhatIs = view.findViewById(R.id.tvw_investment_whatis);
        tvBodyWhatIs = view.findViewById(R.id.tvw_investment_whatisbody);
        tvInvestRisk = view.findViewById(R.id.tvw_investment_titlerisk);

        //  paleta de cores do risco

        tvTitleMoreInfo = view.findViewById(R.id.tvw_investment_moreinfo);
        tvFundMonth = view.findViewById(R.id.tvw_investment_fund_month);
        tvCDIMonth = view.findViewById(R.id.tvw_investment_cdi_month);
        tvFundYear = view.findViewById(R.id.tvw_investment_fund_year);
        tvCDIYear = view.findViewById(R.id.tvw_investment_cdi_year);
        tvFundTwelveMonth = view.findViewById(R.id.tvw_investment_fund_twelvemonth);
        tvCDITwelveMonth = view.findViewById(R.id.tvw_investment_cdi_twelvemonth);

        tvAdmTax = view.findViewById(R.id.tvw_investment_admtax);
        tvLabelAdmTax = view.findViewById(R.id.tvw_investment_label_admtax);
        tvAppInitial = view.findViewById(R.id.tvw_investment_initialapplication);
        tvLabelAppInitial = view.findViewById(R.id.tvw_investment_label_initialapplication);
        tvMinMovement = view.findViewById(R.id.tvw_investment_minimal_movement);
        tvLabelMinMovement = view.findViewById(R.id.tvw_investment_label_minimal_movement);
        tvMinBalance = view.findViewById(R.id.tvw_investment_minimal_balance);
        tvLabelMinBalance = view.findViewById(R.id.tvw_investment_label_minimal_balance);
        tvSaving = view.findViewById(R.id.tvw_investment_saving);
        tvLabelSaving = view.findViewById(R.id.tvw_label_investment_label_saving);
        tvQuota = view.findViewById(R.id.tvw_investment_quota);
        tvLabelQuota = view.findViewById(R.id.tvw_investment_label_quota);
        tvPayment = view.findViewById(R.id.tvw_investment_payment);
        tvLabelPayment = view.findViewById(R.id.tvw_investment_label_payment);

        tvEssentials = view.findViewById(R.id.tvw_investment_essentials);
        tvPerformance = view.findViewById(R.id.tvw_investment_performance);
        tvComplementary = view.findViewById(R.id.tvw_investment_complementary);
        tvRegulamentary = view.findViewById(R.id.tvw_investment_regulation);
        tvAccession = view.findViewById(R.id.tvw_investment_accession);
        ivPointer1 = view.findViewById(R.id.imv_pointer_lvl1);
        ivPointer2 = view.findViewById(R.id.imv_pointer_lvl2);
        ivPointer3 = view.findViewById(R.id.imv_pointer_lvl3);
        ivPointer4 = view.findViewById(R.id.imv_pointer_lvl4);
        ivPointer5 = view.findViewById(R.id.imv_pointer_lvl5);
        btInvest = view.findViewById(R.id.btn_investment_invest);
        btInvest.setOnTouchListener(this);
    }

    @Override
    public void setUp(View view) {
        presenter = new InvestmentPresenter();
        presenter.onAttach(this);

        investmentDataList = new ArrayList<>();

        casts(view);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.btn_investment_invest) {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                v.setAlpha(0.7f);
            else
                v.setAlpha(1f);
        }
        return false;
    }

}
