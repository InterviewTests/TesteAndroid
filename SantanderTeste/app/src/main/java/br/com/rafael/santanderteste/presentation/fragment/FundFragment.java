package br.com.rafael.santanderteste.presentation.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.com.rafael.santanderteste.R;
import br.com.rafael.santanderteste.domain.Fund;
import br.com.rafael.santanderteste.domain.GeneralInfo;
import br.com.rafael.santanderteste.domain.MoreInfo;
import br.com.rafael.santanderteste.domain.ScreenFund;
import br.com.rafael.santanderteste.presentation.FunPresenter;
import br.com.rafael.santanderteste.presentation.FundContract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class FundFragment extends Fragment implements FundContract.View {

    private Context mContext;
    private LinearLayout linearLayout;
    private FunPresenter presenter;

    private TextView tvTitle, tvFundName, tvWhats, tvDefinition, tvRiskTitle;
    private TextView tvFundMonth, tvFundYear, tvFund12Month;
    private TextView tvCDIMonth, tvCDIYear, tvCDI12Month;

//    public FundFragment() {}

    private FundFragment fundFragment;

    public FundFragment getInstance() {
        if (fundFragment != null) return fundFragment;
        fundFragment =  new FundFragment();
        return fundFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.retrieveInvestimentData();

    }

    private void initXmlWidgets(View view) {
        linearLayout = view.findViewById(R.id.vista);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvFundName = view.findViewById(R.id.tvFundName);
        tvWhats = view.findViewById(R.id.tvWhatIs);
        tvDefinition = view.findViewById(R.id.tvDefinition);
        tvRiskTitle = view.findViewById(R.id.tvRiskTitle);
        tvFundMonth = view.findViewById(R.id.tvFunMonth);
        tvFundYear = view.findViewById(R.id.tvFunYear);
        tvFund12Month = view.findViewById(R.id.tvFun12Month);
        tvCDIMonth = view.findViewById(R.id.tvCdiMonth);
        tvCDIYear = view.findViewById(R.id.tvCdiYear);
        tvCDI12Month = view.findViewById(R.id.tvCdi12Months);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initXmlWidgets(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this.getActivity();
        presenter = new FunPresenter();
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fund_layout, container, false);
        return view;
    }

    @Override
    public void loadingInvestimentData() {

    }

    @Override
    public void showErrorToRetrieveData() {

    }

    @Override
    public void showInvestimentData(@NotNull ScreenFund investimentCatalog) {
        Fund fundoInvestimento = investimentCatalog.getScreen();
        if (fundoInvestimento != null) {
            tvTitle.setText(fundoInvestimento.getTitle());
            tvFundName.setText(fundoInvestimento.getFundName());
            tvWhats.setText(fundoInvestimento.getWhatIs());
            tvDefinition.setText(fundoInvestimento.getDefinition());
            tvRiskTitle.setText(fundoInvestimento.getRiskTitle());
            MoreInfo moreInfo = fundoInvestimento.getMoreInfo();
            if (moreInfo != null) {
                tvFundMonth.setText(moreInfo.getMonth().getFund().toString()+"%");
                tvFundYear.setText(moreInfo.getYear().getFund().toString()+"%");
                tvFund12Month.setText(moreInfo.get_12months().getFund().toString()+"%");

                tvCDIMonth.setText(moreInfo.getMonth().getCDI().toString()+"%");
                tvCDIYear.setText(moreInfo.getYear().getCDI().toString()+"%");
                tvCDI12Month.setText(moreInfo.get_12months().getCDI().toString()+"%");

            }

            this.render_information_text_view(fundoInvestimento.getInfo(), fundoInvestimento.getDownInfo());
        }
    }

    private void render_information_text_view(List<GeneralInfo> information_list, List<GeneralInfo> information_data_list) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (GeneralInfo info : information_list) {
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.information_item, null, false);
            TextView tvName = layout.findViewById(R.id.tvName);
            tvName.setText(info.getName());
            TextView tvData = layout.findViewById(R.id.tvData);
            tvData.setText(info.getData());
            linearLayout.addView(layout);
        }
        for (GeneralInfo info : information_list) {
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.information_data_item, null, false);
            TextView tvName = layout.findViewById(R.id.tvName);
            tvName.setText(info.getName());
            linearLayout.addView(layout);
        }
    }
}
