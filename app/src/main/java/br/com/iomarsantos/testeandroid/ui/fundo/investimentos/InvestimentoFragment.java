package br.com.iomarsantos.testeandroid.ui.fundo.investimentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.di.component.ActivityComponent;
import br.com.iomarsantos.testeandroid.entity.InvestmentFund;
import br.com.iomarsantos.testeandroid.ui.base.BaseFragment;
import br.com.iomarsantos.testeandroid.ui.fundo.views.info.MoreInformationInvestmentView;
import br.com.iomarsantos.testeandroid.ui.fundo.views.risk.RiskView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestimentoFragment extends BaseFragment implements
        InvestimentoView {

    private static final String TAG = "InvestimentoFragment";

    @Inject
    InvestimentoBasePresenter<InvestimentoView> mPresenter;

    @Inject
    InfoAdapter infoAdapter;

    @Inject
    DownInfoAdapter downInfoAdapter;

    @Inject
    LinearLayoutManager mLayoutManagerDownInfo;

    @Inject
    LinearLayoutManager mLayoutManagerInfo;

    @BindView(R.id.text_view_investimento_titulo)
    TextView textViewTitulo;

    @BindView(R.id.text_view_investimento_nome_fundo)
    TextView textViewNomeFundo;

    @BindView(R.id.text_view_investimento_o_que)
    TextView textViewOQue;

    @BindView(R.id.text_view_investimento_definicao)
    TextView textViewDefinicao;

    @BindView(R.id.risk_view_investimento_degree)
    RiskView riskViewDegree;

    @BindView(R.id.text_view_investimento_info_titulo)
    TextView textViewInfoTitulo;

    @BindView(R.id.investiment_view_more_info_investment)
    MoreInformationInvestmentView moreInformationInvestmentView;

    @BindView(R.id.recycler_view_investimento_more_informations)
    RecyclerView mRecyclerViewInfo;

    @BindView(R.id.recycler_view_investimento_downinfo_more_informations)
    RecyclerView mRecyclerViewDownInfo;

    public static InvestimentoFragment newInstance() {
        Bundle args = new Bundle();
        InvestimentoFragment fragment = new InvestimentoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investimento, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        configureRecyclerViewInfo();
        configureRecyclerViewDowInfo();
        mPresenter.findAllFundApiCall();
    }

    private void configureRecyclerViewInfo() {
        mLayoutManagerInfo.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewInfo.setLayoutManager(mLayoutManagerInfo);
        mRecyclerViewInfo.setNestedScrollingEnabled(false);
        mRecyclerViewInfo.setHasFixedSize(false);
        mRecyclerViewInfo.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewInfo.setAdapter(infoAdapter);
    }

    private void configureRecyclerViewDowInfo() {
        mLayoutManagerDownInfo.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewDownInfo.setLayoutManager(mLayoutManagerDownInfo);
        mRecyclerViewDownInfo.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewDownInfo.setNestedScrollingEnabled(false);
        mRecyclerViewDownInfo.setHasFixedSize(false);
        mRecyclerViewDownInfo.setAdapter(downInfoAdapter);
    }

    @Override
    public void updateVies(InvestmentFund investmentFund) {
        this.textViewTitulo.setText(investmentFund.getTitle());
        this.textViewNomeFundo.setText(investmentFund.getFundName());
        this.textViewOQue.setText(investmentFund.getWhatIs());
        this.textViewDefinicao.setText(investmentFund.getDefinition());
        this.riskViewDegree.setInvestmentFund(investmentFund);
        this.textViewInfoTitulo.setText(investmentFund.getInfoTitle());
        this.moreInformationInvestmentView.setInvestmentFund(investmentFund);

        this.infoAdapter.addItems(investmentFund.getInfo());
        this.downInfoAdapter.addItems(investmentFund.getDownInfo());
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

}
