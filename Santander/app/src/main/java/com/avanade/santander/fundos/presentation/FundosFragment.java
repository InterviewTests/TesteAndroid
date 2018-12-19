package com.avanade.santander.fundos.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.avanade.santander.R;
import com.avanade.santander.contato.presentation.ContatoActivity;
import com.avanade.santander.fundos.domain.model.Fundos;
import com.avanade.santander.fundos.domain.model.Info;
import com.avanade.santander.fundos.domain.model.Screen;
import com.avanade.santander.util.DpToPixels;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Camada de apresentação - IView (Content)
 * <p>
 * Exibe os dados em tela e instancia os botoes a serem clicados
 */
public class FundosFragment extends Fragment implements FundosContract.IView {

    private FundosContract.IPresenter mIPresenter;

    private static int LAST_ID;

    private ScrollView scrollViewTopContainer;
    private ConstraintLayout fundosFragmentConstraintLayout;
    private ConstraintSet constraintSet; // usaremos para configurar o layout das List<Views> info

    private TextView txtTitle;
    private TextView txtFundName;
    private TextView txtWhatIs;
    private TextView txtDefinition;
    private TextView txtRiskTitle;
    private ImageView imgRisk;
    private View divider;
    private TextView txtMoreMonthFund;
    private TextView txtMoreMonthCdi;
    private TextView txtMoreYearFund;
    private TextView txtMoreYearCdi;
    private TextView txtMoreLastYearFund;
    private TextView txtMoreLastYearCdi;
    private Button btnInvestir;
    private ImageButton btnShare;

    Typeface typefaceDinProMedium;
    Typeface typefaceDinProRegular;


    public FundosFragment() {
        // Requires empty public constructor
    }

    public static FundosFragment newInstance() {
        return new FundosFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constraintSet = new ConstraintSet();

        typefaceDinProMedium = ResourcesCompat.getFont(getContext(), R.font.din_pro_medium);
        typefaceDinProRegular = ResourcesCompat.getFont(getContext(), R.font.din_pro_regular);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fundos_fragment, container, false);
        txtTitle = root.findViewById(R.id.txt_title);
        txtFundName = root.findViewById(R.id.txt_fundName);
        txtWhatIs = root.findViewById(R.id.txt_whatIs);
        txtDefinition = root.findViewById(R.id.txt_definition);
        txtRiskTitle = root.findViewById(R.id.txt_riskTitle);
        imgRisk = root.findViewById(R.id.img_risk);
        divider = root.findViewById(R.id.divider);
        txtMoreMonthFund = root.findViewById(R.id.more_month_fund);
        txtMoreMonthCdi = root.findViewById(R.id.more_month_cdi);
        txtMoreYearFund = root.findViewById(R.id.more_year_fund);
        txtMoreYearCdi = root.findViewById(R.id.more_year_cdi);
        txtMoreLastYearFund = root.findViewById(R.id.more_lastyear_fund);
        txtMoreLastYearCdi = root.findViewById(R.id.more_lastyear_cdi);

        btnInvestir = root.findViewById(R.id.btn_investir);
        btnInvestir.setOnClickListener((v) -> iniciaActivityContato());

        btnShare = root.findViewById(R.id.btn_share);
        btnShare.setOnClickListener((v)->share());

        scrollViewTopContainer = root.findViewById(R.id.scrool_view_top_container);
        fundosFragmentConstraintLayout = root.findViewById(R.id.fundos_fragment_constraint_layout);


        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(scrollViewTopContainer);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIPresenter.refreshFundos();
            }
        });

        setHasOptionsMenu(true);
        fundosFragmentConstraintLayout.setVisibility(View.GONE);

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mIPresenter.start();
    }

    /**
     * Lifecycle - Fragmente Running
     */

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(@NonNull FundosContract.IPresenter IPresenter) {
        mIPresenter = checkNotNull(IPresenter);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {

        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void desenhaTela(Fundos fundos) {

        fundosFragmentConstraintLayout.setVisibility(View.VISIBLE);

        List<Integer> ids = new ArrayList<>();
        Screen screen = fundos.getScreen();
        txtTitle.setText(screen.getTitle());
        txtFundName.setText(screen.getFundName());
        txtWhatIs.setText(screen.getWhatIs());
        txtDefinition.setText(screen.getDefinition());
        txtRiskTitle.setText(screen.getRiskTitle());
        imgRisk.setImageResource(defineDrawableRisk(screen.getRisk()));
        txtMoreMonthFund.setText(screen.getMoreInfo().getMonth().getFund() + "%");
        txtMoreMonthCdi.setText(screen.getMoreInfo().getMonth().getCDI() + "%");
        txtMoreYearFund.setText(screen.getMoreInfo().getYear().getFund() + "%");
        txtMoreYearCdi.setText(screen.getMoreInfo().getYear().getCDI() + "%");
        txtMoreLastYearFund.setText(screen.getMoreInfo().getLastyear().getFund() + "%");
        txtMoreLastYearCdi.setText(screen.getMoreInfo().getLastyear().getCDI() + "%");


        LAST_ID = divider.getId();
        for (Info info : screen.getInfo()) {


            // INFO NAME
            TextView txtName = new TextView(getContext());
            txtName.setId(View.generateViewId());
            txtName.setText(info.getName());
            txtName.setTextColor(getResources().getColor(android.R.color.darker_gray));
            //txtName.setTypeface(typefaceDinProRegular);

            // INFO DATA
            TextView txtData = new TextView(getContext());
            txtData.setId(View.generateViewId());
            txtData.setText(info.getData());
            txtData.setTextColor(Color.BLACK);
            //txtData.setTypeface(typefaceDinProRegular);

            // CONSTRAINT LAYOUT PARA INFO
            constraintInfo(txtName, txtData);
        }

        for (final Info info : screen.getDownInfo()) {

            // INFO NAME
            TextView txtName = new TextView(getContext());
            txtName.setId(View.generateViewId());
            txtName.setText(info.getName());
            txtName.setTextColor(getResources().getColor(android.R.color.darker_gray));
            //txtName.setTypeface(typefaceDinProRegular);

            // INFO BUTTON
            Button btnDown = new Button(getContext());
            btnDown.setId(View.generateViewId());

            btnDown.setText(" Baixar");
            btnDown.setTextColor(getResources().getColor(R.color.colorSantander));
            btnDown.setBackgroundColor(Color.TRANSPARENT);
            btnDown.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baixar, 0, 0, 0);

            btnDown.setOnClickListener((view) -> {
                String url = info.getData() != null ? info.getData() : "http://www.santander.com";
                abrirLinkBaixarInfo(url);
            });

            // CONSTRAINT LAYOUT PARA INFO
            constraintInfo(txtName, btnDown);
        }

        // CONSTRAINT LAYOUT BOTÃO INVESTIR
        constraintSet.clone(fundosFragmentConstraintLayout);

        constraintSet.connect(
                btnInvestir.getId(), ConstraintSet.TOP,
                LAST_ID, ConstraintSet.BOTTOM
        );
        constraintSet.applyTo(fundosFragmentConstraintLayout);
    }

    private void constraintInfo(View name, View data) {

        // ADD VIEWS AO LAYOUT
        fundosFragmentConstraintLayout.addView(name);
        fundosFragmentConstraintLayout.addView(data);

        // Atualiza posição do botão
        constraintSet.clone(fundosFragmentConstraintLayout);

        // SET CONSTRAINTS NAME
        constraintSet.connect(
                name.getId(), ConstraintSet.START,
                divider.getId(), ConstraintSet.START
        );
        constraintSet.connect(
                name.getId(), ConstraintSet.TOP,
                LAST_ID, ConstraintSet.BOTTOM,
                DpToPixels.convertToPixels(24, getContext())
        );

        // SET CONTRAINTS DATA
        constraintSet.connect(
                data.getId(), ConstraintSet.END,
                divider.getId(), ConstraintSet.END
        );
        constraintSet.connect(
                data.getId(), ConstraintSet.TOP,
                name.getId(), ConstraintSet.TOP
        );
        constraintSet.connect(
                data.getId(), ConstraintSet.BOTTOM,
                name.getId(), ConstraintSet.BOTTOM
        );

        LAST_ID = name.getId();
        constraintSet.applyTo(fundosFragmentConstraintLayout);
    }

    public int defineDrawableRisk(int risk) {
        switch (risk) {
            case 1:
                return R.drawable.img_risk_1;
            case 2:
                return R.drawable.img_risk_2;
            case 3:
                return R.drawable.img_risk_3;
            case 4:
                return R.drawable.img_risk_4;
            default:
                return R.drawable.img_risk_5;
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showLoadingFundosError() {
        showMessage("Erro ao buscar informações sobre fundos de investimentos.\nVerifique sua conexão.");
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void abrirLinkBaixarInfo(@NonNull String stringURL) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(stringURL)); // "http://www.example.com";
        startActivity(i);
    }

    @Override
    public void iniciaActivityContato() {
        // TODO - manter 1 Activity e trocar o Fragment, ou manter a call -> Activity p/ segregar Dominio
        startActivity(new Intent(getContext(), ContatoActivity.class));
    }


    @Override
    public void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Fundos Santander";
        String shareSub = "Veja os ganhos em Fundo de Investimentos Santander";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Compartilhar com "));
    }


}
