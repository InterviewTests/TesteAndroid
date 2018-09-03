package br.com.testeandroid.feature;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.testeandroid.R;
import br.com.testeandroid.adapter.InvestimentoAdapter;
import br.com.testeandroid.model.DownInfo;
import br.com.testeandroid.model.Info;
import br.com.testeandroid.model.MoreInfo;
import br.com.testeandroid.model.Screen;

public class InvestimentoFragment extends Fragment implements InvestimentoView {

    TextView tvSubTitulo, tvInvestiment, tvNomePessoa, tvpergunta, tvResposta, tvGrauInvest, tvInfoInvestimento, tvMes, tvMesCdi, tvMesFundo, tvAno, tvAnoFundo, tvAnoCdi, tvDozeMes, tvDozeMesFundo, tvDozeMesCdi;
    RecyclerView recycleViewDadosArray;

    private InvestimentoPresenter presenter;
    private View view;

    public InvestimentoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_investimento, container, false);

        presenter = new InvestimentoPresenterImpl(this);
        presenter.getInvestimentoArrayList();

        inicializador();
        iniciarRecycleView();

        return view;
    }

    public void inicializador(){
        tvSubTitulo = (TextView) view.findViewById(R.id.txtSubTitulo);
        tvInvestiment = (TextView) view.findViewById(R.id.txtInvestiment);
        tvNomePessoa = (TextView) view.findViewById(R.id.txtNomePessoa);
        tvpergunta = (TextView) view.findViewById(R.id.txtpergunta);
        tvResposta = (TextView) view.findViewById(R.id.txtResposta);
        tvGrauInvest = (TextView) view.findViewById(R.id.txtGrauInvest);
        tvInfoInvestimento = (TextView) view.findViewById(R.id.txtInfoInvestimento);

        tvMes = (TextView) view.findViewById(R.id.txtMes);
        tvMesCdi = (TextView) view.findViewById(R.id.txtMesCdi);
        tvMesFundo = (TextView) view.findViewById(R.id.txtMesFundo);
        tvAno = (TextView) view.findViewById(R.id.txtAno);
        tvAnoFundo = (TextView) view.findViewById(R.id.txtAnoFundo);
        tvAnoCdi = (TextView) view.findViewById(R.id.txtAnoCdi);
        tvDozeMes = (TextView) view.findViewById(R.id.txtDozeMes);
        tvDozeMesFundo = (TextView) view.findViewById(R.id.txtDozeMesFundo);
        tvDozeMesCdi = (TextView) view.findViewById(R.id.txtDozeMesCdi);
        tvDozeMes = (TextView) view.findViewById(R.id.txtDozeMes);
        tvDozeMes = (TextView) view.findViewById(R.id.txtDozeMes);
    }

    public void iniciarRecycleView(){
        recycleViewDadosArray = (RecyclerView)view.findViewById(R.id.recycleViewDadosArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleViewDadosArray.setLayoutManager(layoutManager);
    }

    @Override
    public void setScreen(Screen screen) {
        Log.e("CHEGO", screen.getTitle());
        tvSubTitulo.setText(screen.getTitle());
//        Typeface font = Typeface.createFromAsset(getContext().getAssets(),"fonts/dipro_light.otf");
//        tvSubTitulo.setTypeface(font);
        tvNomePessoa.setText(screen.getFundName());
        tvpergunta.setText(screen.getWhatIs());
        tvResposta.setText(screen.getDefinition());
        tvGrauInvest.setText(screen.getRiskTitle());
        tvInfoInvestimento.setText(screen.getInfoTitle());
        setRiskInfo(screen.getRisk());
    }

    @Override
    public void setInfoInvestimento(MoreInfo moreInfo) {
        tvMes.setText("No mês");
        tvMesCdi.setText(String.valueOf(moreInfo.getMonth().getCdi())+"%");
        tvMesFundo.setText(String.valueOf(moreInfo.getMonth().getFund())+"%");
        tvAno.setText("No Ano");
        tvAnoCdi.setText(String.valueOf(moreInfo.getYear().getCdi())+"%");
        tvAnoFundo.setText(String.valueOf(moreInfo.getYear().getFund())+"%");
        tvDozeMes.setText("12 meses");
        tvDozeMesCdi.setText(String.valueOf(moreInfo.getMonths().getCdi())+"%");
        tvDozeMesFundo.setText(String.valueOf(moreInfo.getMonths().getFund())+"%");

    }

    @Override
    public void setRiskInfo(Integer risk) {
        View viewIndicator = null;
        ImageView imgIndicator = null;
        switch (risk) {
            case 1:
                viewIndicator = view.findViewById(R.id.viewUm);
                imgIndicator = view.findViewById(R.id.imgUm);
                break;
            case 2:
                viewIndicator = view.findViewById(R.id.viewDois);
                imgIndicator = view.findViewById(R.id.imgDois);
                break;
            case 3:
                viewIndicator = view.findViewById(R.id.viewTres);
                imgIndicator = view.findViewById(R.id.imgTres);
                break;
            case 4:
                viewIndicator = view.findViewById(R.id.viewQuatro);
                imgIndicator = view.findViewById(R.id.imgQuatro);
                break;
            case 5:
                viewIndicator = view.findViewById(R.id.viewCinco);
                imgIndicator = view.findViewById(R.id.imgCinco);
                break;
        }

        if (viewIndicator != null && imgIndicator != null) {
            viewIndicator.setLayoutParams(getLayoutParams(viewIndicator));
            imgIndicator.setImageResource(R.drawable.ic_down);
        }
    }

    private LinearLayout.LayoutParams getLayoutParams(View colorIndicator) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) colorIndicator.getLayoutParams();
        params.height = 40;
        return params;
    }
    @Override
    public void setRecycleViewAdapter(ArrayList<Info> infos, ArrayList<DownInfo> downInfos) {
        InvestimentoAdapter adapter = new InvestimentoAdapter(infos , downInfos);
        recycleViewDadosArray.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
            view.findViewById(R.id.viewLoading).setVisibility(View.VISIBLE);
    }

    @Override
    public void finishProgress() {
        view.findViewById(R.id.viewLoading).setVisibility(View.GONE);
    }

    @Override
    public void ErroLoading() {
        view.findViewById(R.id.viewErroLoading).setVisibility(View.VISIBLE);
    }
}
