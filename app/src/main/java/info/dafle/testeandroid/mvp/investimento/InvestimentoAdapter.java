package info.dafle.testeandroid.mvp.investimento;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.dafle.testeandroid.R;
import info.dafle.testeandroid.model.Investment;

public class InvestimentoAdapter extends RecyclerView.Adapter<InvestimentoAdapter.InvestmentVH> {

    private Context context;
    private List<Investment> investments;
    private Typeface typeFace;

    InvestimentoAdapter(Context context, List<Investment> investments, Typeface typeFace) {
        this.context = context;
        this.investments = investments;
        this.typeFace = typeFace;
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
