package resource.com.br.santanderapp.ui.investment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import resource.com.br.santanderapp.R;
import resource.com.br.santanderapp.model.investmentModel.Investment;
import resource.com.br.santanderapp.service.APIService;
import resource.com.br.santanderapp.service.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestmentFragment extends Fragment {
    private ViewPager viewPager;

    public InvestmentFragment() {
    }

    @SuppressLint("ValidFragment")
    public InvestmentFragment(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.investment_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView title = view.findViewById(R.id.investment_txt_title);
        final TextView fundName = view.findViewById(R.id.investment_txt_fundName);
        final TextView whatsIs = view.findViewById(R.id.investment_txt_whatIs);
        final TextView definition = view.findViewById(R.id.investment_txt_definition);
        final TextView riskTitle = view.findViewById(R.id.investment_txt_risk_title);
        final TextView infoRiskTitle = view.findViewById(R.id.investment_txt_risk_infoTitle);
        final TextView fundTrTwo = view.findViewById(R.id.investment_txt_fund_tb_two);
        final TextView fundTrThree = view.findViewById(R.id.investment_txt_fund_tb_three);
        final TextView fundTrFour = view.findViewById(R.id.investment_txt_fund_tb_four);
        final TextView cdiTrTwo = view.findViewById(R.id.investment_txt_cdi_tr_two);
        final TextView cdiTrThree = view.findViewById(R.id.investment_txt_cdi_tr_three);
        final TextView cdiTrFour = view.findViewById(R.id.investment_txt_cdi_tr_four);
        final RecyclerView rcvInfo = view.findViewById(R.id.investment_rcv_info);
        final RecyclerView rcvInfoDown = view.findViewById(R.id.investment_rcv_down_info);
        final Button btnInvestment = view.findViewById(R.id.investment_btn);
        final ImageView imgOne = view.findViewById(R.id.img_one);
        final ImageView imgTwo = view.findViewById(R.id.img_two);
        final ImageView imgThree = view.findViewById(R.id.img_three);
        final ImageView imgFour = view.findViewById(R.id.img_four);
        final ImageView imgFive = view.findViewById(R.id.img_five);
        final ProgressDialog mProgressDialog;

        final InvestmentInfoAdapter[] infoAdapter = new InvestmentInfoAdapter[1];
        final InvestmentDownInfoAdapter[] downInfoAdapter = new InvestmentDownInfoAdapter[1];


        APIService apiService = APIUtils.getAPIService();
        mProgressDialog = getProgressDialog();


        btnInvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(1);
            }
        });


        apiService.getData().enqueue(new Callback<Investment>() {
            @Override
            public void onResponse(Call<Investment> call, Response<Investment> response) {
                setData(response, title, fundName, whatsIs, definition, riskTitle, infoRiskTitle, fundTrTwo, cdiTrTwo, fundTrThree, cdiTrThree, fundTrFour, cdiTrFour);

                setListInfo(response, infoAdapter, rcvInfo);
                setListDownloadInfo(response, downInfoAdapter, rcvInfoDown);

                int riskNumber = response.body().getScreen().getRisk();
                setRiskInvestiment(riskNumber, imgOne, imgTwo, imgThree, imgFour, imgFive);

                mProgressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Investment> call, Throwable t) {
                Toast.makeText(getContext(), "Por Favor, tente mais tarde", Toast.LENGTH_LONG).show();
                Log.e("Erro resposta api", t.getMessage());
            }
        });

    }

    @NonNull
    private ProgressDialog getProgressDialog() {
        ProgressDialog mProgressDialog;
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        return mProgressDialog;
    }

    private void setListDownloadInfo(Response<Investment> response, InvestmentDownInfoAdapter[] downInfoAdapter, RecyclerView rcvInfoDown) {
        downInfoAdapter[0] = new InvestmentDownInfoAdapter(getContext(), response.body().getScreen().getDownInfo());
        rcvInfoDown.setAdapter(downInfoAdapter[0]);
    }

    private void setListInfo(Response<Investment> response, InvestmentInfoAdapter[] infoAdapter, RecyclerView rcvInfo) {
        infoAdapter[0] = new InvestmentInfoAdapter(getContext(), response.body().getScreen().getInfo());
        rcvInfo.setAdapter(infoAdapter[0]);
    }

    private void setData(Response<Investment> response, TextView title, TextView fundName, TextView whatsIs, TextView definition, TextView riskTitle, TextView infoRiskTitle, TextView fundTrTwo, TextView cdiTrTwo, TextView fundTrThree, TextView cdiTrThree, TextView fundTrFour, TextView cdiTrFour) {
        title.setText(response.body().getScreen().getTitle());
        fundName.setText(response.body().getScreen().getFundName());
        whatsIs.setText(response.body().getScreen().getWhatsIs());
        definition.setText(response.body().getScreen().getDefinition());
        riskTitle.setText(response.body().getScreen().getRiskTitle());
        infoRiskTitle.setText(response.body().getScreen().getInfoTitle());
        fundTrTwo.setText(String.valueOf(response.body().getScreen().getMoreInfo().getMonth().getFund()) + "%");
        cdiTrTwo.setText(String.valueOf(response.body().getScreen().getMoreInfo().getMonth().getCdi()) + "%");
        fundTrThree.setText(String.valueOf(response.body().getScreen().getMoreInfo().getYear().getFund()) + "%");
        cdiTrThree.setText(String.valueOf(response.body().getScreen().getMoreInfo().getYear().getCdi()) + "%");
        fundTrFour.setText(String.valueOf(response.body().getScreen().getMoreInfo().getTwelveMonth().getFund()) + "%");
        cdiTrFour.setText(String.valueOf(response.body().getScreen().getMoreInfo().getTwelveMonth().getCdi()) + "%");
    }

    private void setRiskInvestiment(int riskNumber, ImageView imgOne, ImageView imgTwo, ImageView imgThree, ImageView imgFour, ImageView imgFive) {
        switch (riskNumber) {
            case 1:
                imgOne.setVisibility(View.VISIBLE);
                break;
            case 2:
                imgTwo.setVisibility(View.VISIBLE);
                break;
            case 3:
                imgThree.setVisibility(View.VISIBLE);
                break;
            case 4:
                imgFour.setVisibility(View.VISIBLE);
                break;
            case 5:
                imgFive.setVisibility(View.VISIBLE);
                break;
        }
    }
}
