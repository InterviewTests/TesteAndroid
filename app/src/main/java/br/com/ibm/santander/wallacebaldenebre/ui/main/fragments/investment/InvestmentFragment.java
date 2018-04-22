package br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.investment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
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

public class InvestmentFragment extends Fragment implements InvestmentContract.View, View.OnTouchListener, View.OnClickListener {
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
    protected TextView tvAppInitial;
    protected TextView tvMinMovement;
    protected TextView tvMinBalance;
    protected TextView tvSaving;
    protected TextView tvQuota;
    protected TextView tvPayment;
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
                pd.setMessage("Enquanto colhemos as informações deste fundo de investimento...");
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
        presenter.showDataFromServer(this, new InvestmentCallback<HashMap<String, String>>() {
            @Override
            public void onSuccess(HashMap<String, String> data) {
                hideProgress();
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();

                    tvInvestFund.setText(value == null ? "Fundo de Investimento" : value);
                    tvTitle.setText(value == null ? "Vinci Valorem FI\nMultimercado" : value);
                    tvWhatIs.setText(value == null ? "O que é?" : value);
                    tvBodyWhatIs.setText(value == null ? "O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo através de investimentos" : value);
                    tvInvestRisk.setText(value == null ? "Fundo de Investimento" : value);

                    //  dados do risco
                    setPointerVisible(4);
                    switch (key) {
                        case "risk":
                            //  pega os valores
                            switch (Integer.parseInt(value)) {
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
                            }
                            break;
                        default:
                            setPointerVisible(4);
                            break;
                    }

                    tvTitleMoreInfo.setText(value == null ? "Mais informações sobre o investimento" : value);
                    tvFundMonth.setText(String.valueOf(value) == null ? getString(R.string.str_unavailable) : String.valueOf(value));
                    tvCDIMonth.setText(String.valueOf(value) == null ? getString(R.string.str_unavailable) : String.valueOf(value));
                    tvFundYear.setText(String.valueOf(value) == null ? getString(R.string.str_unavailable) : String.valueOf(value));
                    tvCDIYear.setText(String.valueOf(value) == null ? getString(R.string.str_unavailable) : String.valueOf(value));
                    tvFundTwelveMonth.setText(String.valueOf(value) == null ? getString(R.string.str_unavailable) : String.valueOf(value));
                    tvCDITwelveMonth.setText(String.valueOf(value) == null ? getString(R.string.str_unavailable) : String.valueOf(value));
                }
            }

            @Override
            public void onFailure(int errorCode, String reason) {
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
        tvAppInitial = view.findViewById(R.id.tvw_investment_initialapplication);
        tvMinMovement = view.findViewById(R.id.tvw_investment_minimal_movement);
        tvMinBalance = view.findViewById(R.id.tvw_investment_minimal_balance);
        tvSaving = view.findViewById(R.id.tvw_investment_saving);
        tvQuota = view.findViewById(R.id.tvw_investment_quota);
        tvPayment = view.findViewById(R.id.tvw_investment_payment);
        ivPointer1 = view.findViewById(R.id.imv_pointer_lvl1);
        ivPointer2 = view.findViewById(R.id.imv_pointer_lvl2);
        ivPointer3 = view.findViewById(R.id.imv_pointer_lvl3);
        ivPointer4 = view.findViewById(R.id.imv_pointer_lvl4);
        ivPointer5 = view.findViewById(R.id.imv_pointer_lvl5);
        llAlert = view.findViewById(R.id.llt_investment_alert);
        ivCloseAlert = view.findViewById(R.id.imv_investment_close_alert);
        ivCloseAlert.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imv_investment_close_alert) llAlert.setVisibility(View.GONE);
    }
}
