package lzacheu.com.br.santanderinvestimento.fund;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import lzacheu.com.br.santanderinvestimento.Injection;
import lzacheu.com.br.santanderinvestimento.R;
import lzacheu.com.br.santanderinvestimento.model.fund.Screen;
import lzacheu.com.br.santanderinvestimento.util.TypeFaceBuilder;
import lzacheu.com.br.santanderinvestimento.widget.RiskLayout;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class FundInfoFragment extends Fragment implements FundInfoContract.View, View.OnClickListener {

    private static final String LOG_TAG = FundInfoFragment.class.getSimpleName();
    private FundInfoContract.Presenter presenter;

    @BindView(R.id.screen_title)
    TextView screenTitleText;

    @BindView(R.id.screen_fund_name)
    TextView screenFundNameText;

    @BindView(R.id.screen_whatis)
    TextView screenWhatIsText;

    @BindView(R.id.screen_definition)
    TextView screenDefinitionText;

    @BindView(R.id.screen_risk_title)
    TextView screenRiskTitleText;

    @BindView(R.id.more_info_title)
    TextView moreInfoTitle;

    @BindView(R.id.more_info_month)
    TextView moreInfoMonth;

    @BindView(R.id.more_info_month_fund)
    TextView moreInfoMonthFund;

    @BindView(R.id.more_info_month_cdi)
    TextView moreInfoMonthCdi;

    @BindView(R.id.more_info_year)
    TextView moreInfoYear;

    @BindView(R.id.more_info_year_fund)
    TextView moreInfoYearFund;

    @BindView(R.id.more_info_year_cdi)
    TextView moreInfoYearCdi;

    @BindView(R.id.more_info_twelve)
    TextView moreInfoTwelve;

    @BindView(R.id.more_info_twelve_fund)
    TextView moreInfoTwelveFund;

    @BindView(R.id.more_info_twelve_cdi)
    TextView moreInfoTwelveCdi;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.risk_layout)
    RiskLayout riskLayout;

    private int lastReferenceIdName;
    private int lastReferenceIdData;

    public static FundInfoFragment newInstance() {
        return new FundInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FundInfoPresenter(Injection.provideFundRepository(),this);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
        presenter.fetchData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fundinfo_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void setPresenter(FundInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void bindValues(Screen screen) {
        riskLayout.setRisk(screen.getRisk());

        screenTitleText.setText(screen.getTitle());
        screenTitleText.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        screenFundNameText.setText(screen.getFundName());
        screenFundNameText.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        screenWhatIsText.setText(screen.getWhatIs());
        screenWhatIsText.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        screenDefinitionText.setText(screen.getDefinition());
        screenDefinitionText.setTypeface(TypeFaceBuilder.getDinpLight(getContext()));

        screenRiskTitleText.setText(screen.getRiskTitle());
        screenRiskTitleText.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        moreInfoTitle.setText(screen.getInfoTitle());
        moreInfoTitle.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        moreInfoMonth.setTypeface(TypeFaceBuilder.getDinpRegular(getContext()));
        moreInfoMonthFund.setText(String.format(Locale.getDefault(),"%.1f%%", screen.getMoreInfo().getMonth().getFund()));
        moreInfoMonthFund.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        moreInfoMonthCdi.setText(String.format(Locale.getDefault(),"%.1f%%", screen.getMoreInfo().getMonth().getCdi()));
        moreInfoMonthCdi.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        moreInfoYear.setTypeface(TypeFaceBuilder.getDinpRegular(getContext()));
        moreInfoYearFund.setText(String.format(Locale.getDefault(),"%.2f%%", screen.getMoreInfo().getYear().getFund()));
        moreInfoYearFund.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        moreInfoYearCdi.setText(String.format(Locale.getDefault(),"%.2f%%", screen.getMoreInfo().getYear().getCdi()));
        moreInfoYearCdi.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        moreInfoTwelve.setTypeface(TypeFaceBuilder.getDinpRegular(getContext()));
        moreInfoTwelveFund.setText(String.format(Locale.getDefault(),"%.1f%%", screen.getMoreInfo().getTwelveMonths().getFund()));
        moreInfoTwelveFund.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        moreInfoTwelveCdi.setText(String.format(Locale.getDefault(),"%.1f%%", screen.getMoreInfo().getTwelveMonths().getCdi()));
        moreInfoTwelveCdi.setTypeface(TypeFaceBuilder.getDinpMedium(getContext()));

        ScrollView rootView = (ScrollView) getView();
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView.getChildAt(0);
        ConstraintSet constraintSet = new ConstraintSet();

        createDynamicInfoFields(screen, constraintLayout, constraintSet);

        createDynamicDownInfoFields(screen, constraintLayout, constraintSet);

        createButtonInvest(constraintLayout, constraintSet);


    }

    private void createButtonInvest(ConstraintLayout constraintLayout, ConstraintSet constraintSet) {
        Button btnInvest = new Button(getContext());
        btnInvest.setBackground(getResources().getDrawable(R.drawable.btn_call_to_action));
        btnInvest.setId(R.id.btn_invest);
        btnInvest.setText(R.string.btn_invest);
        btnInvest.setTextColor(getResources().getColor(R.color.btn_call_to_action_text));

        constraintLayout.addView(btnInvest);
        constraintSet.clone(constraintLayout);
        constraintSet.connect(btnInvest.getId(), ConstraintSet.TOP, lastReferenceIdName, ConstraintSet.BOTTOM, 100);
        constraintSet.connect(btnInvest.getId(), ConstraintSet.START, R.id.guideline_left, ConstraintSet.START, 0);
        constraintSet.connect(btnInvest.getId(), ConstraintSet.END, R.id.guideline_right, ConstraintSet.START, 0);
        constraintSet.connect(btnInvest.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 16);
        constraintSet.constrainWidth(btnInvest.getId(), ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.applyTo(constraintLayout);
    }

    private void createDynamicInfoFields(Screen screen, ConstraintLayout constraintLayout, ConstraintSet constraintSet) {
        for (int i = 0; i < screen.getInfo().size(); i++) {
            TextView textViewName = new TextView(getContext());
            textViewName.setId(i+1);
            textViewName.setText(screen.getInfo().get(i).getName());
            textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            textViewName.setTypeface(TypeFaceBuilder.getDinpRegular(getContext()));
            constraintLayout.addView(textViewName);

            TextView textViewData = new TextView(getContext());
            textViewData.setId(i+100);
            textViewData.setText(screen.getInfo().get(i).getData());
            textViewData.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            textViewData.setTypeface(TypeFaceBuilder.getDinpRegular(getContext()));
            textViewData.setGravity(Gravity.END);
            constraintLayout.addView(textViewData);

            constraintSet.clone(constraintLayout);

            if (i == 0){
                //Info Name
                constraintSet.connect(textViewName.getId(), ConstraintSet.TOP, R.id.more_info_twelve, ConstraintSet.BOTTOM, 18);
                constraintSet.connect(textViewName.getId(), ConstraintSet.START, R.id.guideline_left, ConstraintSet.START, 0);
                constraintSet.connect(textViewName.getId(), ConstraintSet.END, R.id.guideline_more_info_left, ConstraintSet.START, 0);
                constraintSet.constrainWidth(textViewName.getId(), ConstraintSet.MATCH_CONSTRAINT);

                //Info Data
                constraintSet.connect(textViewData.getId(), ConstraintSet.TOP, R.id.more_info_twelve_cdi, ConstraintSet.BOTTOM, 18);
                constraintSet.connect(textViewData.getId(), ConstraintSet.START, R.id.guideline_more_info_left, ConstraintSet.START, 0);
                constraintSet.connect(textViewData.getId(), ConstraintSet.END, R.id.guideline_right, ConstraintSet.START, 0);
                constraintSet.constrainWidth(textViewData.getId(), ConstraintSet.MATCH_CONSTRAINT);
            }
            else{
                //Info Name
                constraintSet.connect(textViewName.getId(), ConstraintSet.TOP, (textViewName.getId())-1, ConstraintSet.BOTTOM, 18 );
                constraintSet.connect(textViewName.getId(), ConstraintSet.START, R.id.guideline_left, ConstraintSet.START, 0);
                constraintSet.connect(textViewName.getId(), ConstraintSet.END, R.id.guideline_more_info_left, ConstraintSet.START, 0);
                constraintSet.constrainWidth(textViewName.getId(), ConstraintSet.MATCH_CONSTRAINT);

                //Info Data
                constraintSet.connect(textViewData.getId(), ConstraintSet.TOP, (textViewData.getId())-1, ConstraintSet.BOTTOM, 18 );
                constraintSet.connect(textViewData.getId(), ConstraintSet.START, R.id.guideline_more_info_left, ConstraintSet.START, 0);
                constraintSet.connect(textViewData.getId(), ConstraintSet.END, R.id.guideline_right, ConstraintSet.START, 0);
                constraintSet.constrainWidth(textViewData.getId(), ConstraintSet.MATCH_CONSTRAINT);
            }

            lastReferenceIdName = textViewName.getId();
            lastReferenceIdData = textViewData.getId();

            constraintSet.applyTo(constraintLayout);


        }
    }

    private void createDynamicDownInfoFields(Screen screen, ConstraintLayout constraintLayout, ConstraintSet constraintSet) {
        for (int i = 0; i < screen.getDownInfo().size(); i++) {
            TextView textViewName = new TextView(getContext());
            textViewName.setId(i+11);
            textViewName.setText(screen.getDownInfo().get(i).getName());
            textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            textViewName.setTypeface(TypeFaceBuilder.getDinpRegular(getContext()));
            constraintLayout.addView(textViewName);

            TextView textViewData = new TextView(getContext());
            textViewData.setId(i+111);
            textViewData.setText("Baixar");
            textViewData.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            textViewData.setTypeface(TypeFaceBuilder.getDinpRegular(getContext()));

            Drawable image = getResources().getDrawable(R.drawable.ic_arrow_downward);
            image.setBounds( 0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight() );

            textViewData.setCompoundDrawables(image, null, null, null);
            textViewData.setTextColor(getResources().getColor(R.color.colorAccent));
            textViewData.setGravity(Gravity.END);
            constraintLayout.addView(textViewData);

            constraintSet.clone(constraintLayout);

            if (i == 0){
                //Info Name
                constraintSet.connect(textViewName.getId(), ConstraintSet.TOP, lastReferenceIdName, ConstraintSet.BOTTOM, 18);
                constraintSet.connect(textViewName.getId(), ConstraintSet.START, R.id.guideline_left, ConstraintSet.START, 0);
                constraintSet.connect(textViewName.getId(), ConstraintSet.END, R.id.guideline_more_info_left, ConstraintSet.START, 0);
                constraintSet.constrainWidth(textViewName.getId(), ConstraintSet.MATCH_CONSTRAINT);

                //Info Data
                constraintSet.connect(textViewData.getId(), ConstraintSet.TOP, lastReferenceIdData, ConstraintSet.BOTTOM, 18);
                constraintSet.connect(textViewData.getId(), ConstraintSet.START, R.id.guideline_down, ConstraintSet.START, 0);
                constraintSet.connect(textViewData.getId(), ConstraintSet.END, R.id.guideline_right, ConstraintSet.START, 0);
                constraintSet.constrainWidth(textViewData.getId(), ConstraintSet.MATCH_CONSTRAINT);
            }
            else{
                //Info Name
                constraintSet.connect(textViewName.getId(), ConstraintSet.TOP, (textViewName.getId())-1, ConstraintSet.BOTTOM, 18 );
                constraintSet.connect(textViewName.getId(), ConstraintSet.START, R.id.guideline_left, ConstraintSet.START, 0);
                constraintSet.connect(textViewName.getId(), ConstraintSet.END, R.id.guideline_more_info_left, ConstraintSet.START, 0);
                constraintSet.constrainWidth(textViewName.getId(), ConstraintSet.MATCH_CONSTRAINT);

                //Info Data
                constraintSet.connect(textViewData.getId(), ConstraintSet.TOP, (textViewData.getId())-1, ConstraintSet.BOTTOM, 18 );
                constraintSet.connect(textViewData.getId(), ConstraintSet.START, R.id.guideline_down, ConstraintSet.START, 0);
                constraintSet.connect(textViewData.getId(), ConstraintSet.END, R.id.guideline_right, ConstraintSet.START, 0);
                constraintSet.constrainWidth(textViewData.getId(), ConstraintSet.MATCH_CONSTRAINT);
            }
            lastReferenceIdName = textViewName.getId();
            constraintSet.applyTo(constraintLayout);


        }
    }

    @Override
    public void onClick(View view) {

    }
}
