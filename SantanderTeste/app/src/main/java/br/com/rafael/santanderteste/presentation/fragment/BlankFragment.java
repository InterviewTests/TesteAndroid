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
import br.com.rafael.santanderteste.R;


public class BlankFragment extends Fragment {

    private Context mContext;
    private LinearLayout linearLayout;

    public BlankFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fund_layout, container, false);
        return view;
    }

}
