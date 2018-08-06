package br.com.iomarsantos.testeandroid.ui.fundo.investimentos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.entity.Info;
import br.com.iomarsantos.testeandroid.ui.base.BaseViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Info> mInfoList;

    public InfoAdapter(List<Info> mInfoList) {
        this.mInfoList = mInfoList;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_investment, parent, false));
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }

    public void addItems(List<Info> infoList) {
        mInfoList.clear();
        mInfoList.addAll(infoList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.text_view_item_info_name)
        TextView textViewInfoName;

        @BindView(R.id.text_view_item_info_data)
        TextView textViewInfoData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            super.onBind(position);

            final Info info = mInfoList.get(position);

            String name = info.getName();
            if (name != null) {
                textViewInfoName.setText(name);
            }

            String data = info.getData();
            if (data != null) {
                textViewInfoData.setText(data);
            }

        }
    }

}
