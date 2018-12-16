package com.avanade.santander.fundos.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avanade.santander.R;
import com.avanade.santander.fundos.domain.ScrollChildSwipeRefreshLayout;
import com.avanade.santander.fundos.domain.model.Fundos;
import com.avanade.santander.fundos.domain.model.Info;
import com.avanade.santander.fundos.domain.model.Screen;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class FundosFragment extends Fragment implements FundosContract.View {

    private FundosContract.Presenter mPresenter;

    private static int LAST_ID;

    private TextView txtTitle;
    private TextView txtFundName;
    private TextView txtWhatIs;
    private TextView txtDefinition;
    private TextView txtRiskTitle;
    private ImageView imgRisk;
    private ImageView divider;
    private TextView txtMoreMonthFund;
    private TextView txtMoreMonthCdi;
    private TextView txtMoreYearFund;
    private TextView txtMoreYearCdi;
    private TextView txtMoreLastYearFund;
    private TextView txtMoreLastYearCdi;
    private Button btnInvestir;
    private ConstraintLayout fundosFragmentConstraintLayout;
    private ConstraintSet constraintSet; // usaremos para configurar o layout das List<Views> info

    public FundosFragment() {
        // Requires empty public constructor
    }

    public static FundosFragment newInstance() {
        return new FundosFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mListAdapter = new TasksAdapter(new ArrayList<Task>(0), mItemListener);
        constraintSet = new ConstraintSet();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull FundosContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fundos_fragment, container, false);
        txtTitle = root.findViewById(R.id.txt_title);
        txtFundName = root.findViewById(R.id.txt_fundName);
        txtWhatIs = root.findViewById(R.id.txt_whatIs);
        txtDefinition = root.findViewById(R.id.definition);
        txtRiskTitle = root.findViewById(R.id.riskTitle);
        imgRisk = root.findViewById(R.id.img_risk);

        divider = root.findViewById(R.id.divider);
        LAST_ID = divider.getId();

        txtMoreMonthFund = root.findViewById(R.id.more_month_fund);
        txtMoreMonthCdi = root.findViewById(R.id.more_month_cdi);
        txtMoreYearFund = root.findViewById(R.id.more_year_fund);
        txtMoreYearCdi = root.findViewById(R.id.more_year_cdi);
        txtMoreLastYearFund = root.findViewById(R.id.more_lastyear_fund);
        txtMoreLastYearCdi = root.findViewById(R.id.more_lastyear_cdi);

        btnInvestir = root.findViewById(R.id.btn_investir);

        fundosFragmentConstraintLayout = root.findViewById(R.id.fundos_fragment_constraint_layout);
        constraintSet.clone(fundosFragmentConstraintLayout);


        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(fundosFragmentConstraintLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshFundos();
            }
        });

        setHasOptionsMenu(true);

        return root;

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
        List<Integer> ids = new ArrayList<>();
        Screen screen = fundos.getScreen();
        txtTitle.setText(screen.getTitle());
        txtFundName.setText(screen.getFundName());
        txtWhatIs.setText(screen.getWhatIs());
        txtDefinition.setText(screen.getDefinition());
        txtRiskTitle.setText(screen.getRiskTitle());
        imgRisk.setImageResource(drawableRisk(screen.getRisk()));
        txtMoreMonthFund.setText(screen.getMoreInfo().getMonth().getFund());
        txtMoreMonthCdi.setText(screen.getMoreInfo().getMonth().getCDI());
        txtMoreYearFund.setText(screen.getMoreInfo().getYear().getFund());
        txtMoreYearCdi.setText(screen.getMoreInfo().getYear().getCDI());
        txtMoreLastYearFund.setText(screen.getMoreInfo().getLastyear().getFund());
        txtMoreLastYearCdi.setText(screen.getMoreInfo().getLastyear().getCDI());



        for (Info info : screen.getInfo()) {

            // INFO NAME
            TextView txtName = new TextView(getContext());
            txtName.setId(View.generateViewId());
            txtName.setText(info.getName());

            // INFO DATA
            TextView txtData = new TextView(getContext());
            txtName.setId(View.generateViewId());
            txtName.setText(info.getData());

            // DESENHA INFO NO LAYOUT
            desenhaInfo(txtName, txtData);
        }

        for (Info info : screen.getDownInfo()) {

            // INFO NAME
            TextView txtName = new TextView(getContext());
            txtName.setId(View.generateViewId());
            txtName.setText(info.getName());

            // INFO DATA
            Button btnDown = new Button(getContext());
            btnDown.setId(View.generateViewId());
            btnDown.setText(" Baixar");
            btnDown.setTextColor(getResources().getColor(R.color.colorSantander));
            btnDown.setBackgroundColor(Color.TRANSPARENT);
            btnDown.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baixar,0,0,0);

            // DESENHA INFO NO LAYOUT
            desenhaInfo(txtName, btnDown);
        }



    }

    private void desenhaInfo(View name, View data){

        // ADD VIEWS AO LAYOUT
        fundosFragmentConstraintLayout.addView(name);
        fundosFragmentConstraintLayout.addView(data);

        // SET CONSTRAINTS NAME
        constraintSet.connect(
                name.getId(), ConstraintSet.START,
                divider.getId(), ConstraintSet.START
        );
        constraintSet.connect(
                name.getId(), ConstraintSet.TOP,
                LAST_ID, ConstraintSet.BOTTOM
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
    }


    public int drawableRisk(int risk) {
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
        showMessage("Erro ao buscar informações dobre fundos de investimentos");
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }


}
