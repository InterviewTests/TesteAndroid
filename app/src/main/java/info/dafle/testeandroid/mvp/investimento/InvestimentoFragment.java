package info.dafle.testeandroid.mvp.investimento;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.dafle.testeandroid.R;
import info.dafle.testeandroid.model.Fund;
import info.dafle.testeandroid.model.Investment;

public class InvestimentoFragment extends Fragment implements  InvestimentoContract.View {

    private static final String TAG = InvestimentoFragment.class.getSimpleName();
    private Fund fund;
    private Context context;
    private ConstraintLayout constraintLayout;
    private Typeface typeFace;
    private View root;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_investimento, container, false);

        context = root.getContext();
        constraintLayout = root.findViewById(R.id.main);
        progressBar = root.findViewById(R.id.progressBar);
        typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/DINPro-Light.ttf");
        InvestimentoViewModel viewModel = ViewModelProviders.of(this).get(InvestimentoViewModel.class);
        viewModel.setView(this);
        showProgress();
        viewModel.getFund().observe(this, fund1 -> {
            buildLayout(fund1);
            hideProgress();
        });
        return root;
    }

    public String formatToPercentage(double db) {
        try {

            return db+"".replace(".",",")+"%";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-";
    }

    @Override
    public void buildLayout(Fund fund) {
        this.fund = fund;
        Fund.Screen screen = fund.getScreen();
        for (int i=0; i<screen.titles().length; i++) {
            TextView textView = (TextView) constraintLayout.getChildAt(i+1);
            textView.setTypeface(typeFace);
            textView.setText(screen.titles()[i]);
        }

        List<Investment> investments = new ArrayList<>();

        Investment title = new Investment();
        title.setTitle(null);
        title.setFirstValue("Fundo");
        title.setSecondValue("CDI");
        investments.add(title);

        Fund.Screen.MoreInfo moreInfo = screen.getMoreInfo();
        String[] titles = new String[]{"No mês", "No Ano", "12 meses"};
        double[] fundos = new double[]{moreInfo.getMonth().getFund(),moreInfo.getYear().getFund(), moreInfo.getMonths12().getFund()};
        double[] cdis = new double[]{moreInfo.getMonth().getCDI(),moreInfo.getYear().getCDI(), moreInfo.getMonths12().getCDI()};
        for (int i=0; i<titles.length; i++) {
            Investment investment = new Investment();
            investment.setTitle(titles[i]);
            investment.setFirstValue(formatToPercentage(fundos[i]));
            investment.setSecondValue(formatToPercentage(cdis[i]));
            investments.add(investment);
        }

        investments.add(new Investment()); //new line

        for(Fund.Screen.Info info: screen.getInfo()) {

            Investment investment = new Investment();
            investment.setTitle(info.getName());
            investment.setSecondValue(info.getData());
            investments.add(investment);
        }

        for(Fund.Screen.Info info: screen.getDownInfo()) {

            Investment investment = new Investment();
            investment.setTitle(info.getName());
            investment.setSecondValue("Baixar");
            investment.setShowImageDownload(true);
            investment.setTextColor(Color.RED);
            investments.add(investment);
        }


        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new InvestmentAdapter(investments));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        ConstraintLayout.LayoutParams c = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();

        c.height = dpToPx(36 * investments.size());
        recyclerView.setLayoutParams(c);
        showCurrentArrow(screen.getRisk());
    }

    void showCurrentArrow(int position) {

        ConstraintLayout constraintLayout = root.findViewById(R.id.constraintLayout);
        View imageView = constraintLayout.getChildAt(position-1);
        Log.i(TAG, imageView.toString());
        imageView.setVisibility(View.VISIBLE);
        View view = constraintLayout.getChildAt(position+position);
        ConstraintLayout.LayoutParams c = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        c.height = dpToPx(8);
        view.setLayoutParams(c);
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
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

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    class InvestmentAdapter extends RecyclerView.Adapter<InvestmentAdapter.InvestmentVH> {

        List<Investment> investments;

        InvestmentAdapter(List<Investment> investments) {
            this.investments = investments;
        }

        @NonNull
        @Override
        public InvestmentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new InvestmentVH(LayoutInflater.from(context).inflate(R.layout.adapter_investimento, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull InvestmentVH holder, int position) {
            holder.bind(investments.get(position));
        }

        @Override
        public int getItemCount() {
            return investments.size();
        }

        class InvestmentVH extends RecyclerView.ViewHolder {

            TextView tv_title, tv_middle, tv_end;
            ImageView iv_baixar;

            InvestmentVH(View itemView) {
                super(itemView);
                tv_title = itemView.findViewById(R.id.tv_title);
                tv_middle = itemView.findViewById(R.id.tv_middle);
                tv_end = itemView.findViewById(R.id.tv_end);
                iv_baixar = itemView.findViewById(R.id.iv_baixar);
                tv_title.setTypeface(typeFace);
                tv_middle.setTypeface(typeFace);
                tv_end.setTypeface(typeFace);
            }

            void bind(Investment investment) {
                tv_title.setText(investment.getTitle());
                tv_middle.setText(investment.getFirstValue());
                tv_end.setText(investment.getSecondValue());
                tv_end.setTextColor(investment.getTextColor());
                iv_baixar.setVisibility(investment.isShowImageDownload() ? View.VISIBLE : View.INVISIBLE);
            }
        }
    }
}
