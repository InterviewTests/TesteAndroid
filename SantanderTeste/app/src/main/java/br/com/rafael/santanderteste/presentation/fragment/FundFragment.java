package br.com.rafael.santanderteste.presentation.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.com.rafael.santanderteste.R;
import br.com.rafael.santanderteste.domain.ScreenFund;
import br.com.rafael.santanderteste.presentation.FunPresenter;
import br.com.rafael.santanderteste.presentation.FundContract;
import org.jetbrains.annotations.NotNull;


public class FundFragment extends Fragment implements FundContract.View {

    private Context mContext;
    private LinearLayout linearLayout;
    private FunPresenter presenter;

    public FundFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.retrieveInvestimentData();

        for (int i = 0; i < 20 ; i++) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.information_item, null, false);

        TextView tvName = (TextView) layout.findViewById(R.id.tvName);
        tvName.setText("testando " + i);

        linearLayout.addView(layout);

    }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = (LinearLayout) view.findViewById(R.id.vista);

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
        Toast.makeText(mContext, "ta buscando a bagaca", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorToRetrieveData() {

    }

    @Override
    public void showInvestimentData(@NotNull ScreenFund investimentCatalog) {
        Toast.makeText(mContext, investimentCatalog.toString(), Toast.LENGTH_SHORT).show();
    }
}
