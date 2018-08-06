package br.com.iomarsantos.testeandroid.ui.fundo.investimentos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.entity.DownInfo;
import br.com.iomarsantos.testeandroid.ui.base.BaseViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DownInfoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DownInfo> mDownInfos;

    public DownInfoAdapter(List<DownInfo> mDownInfos) {
        this.mDownInfos = mDownInfos;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_downinfo_investment, parent, false));
    }

    @Override
    public int getItemCount() {
        return mDownInfos.size();
    }

    public void addItems(List<DownInfo> downInfos) {
        this.mDownInfos.clear();
        this.mDownInfos.addAll(downInfos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.text_view_item_downinfo_name)
        TextView textViewDownInfoName;

        @BindView(R.id.button_item_downinfo_data)
        TextView textDownInfoData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            super.onBind(position);

            final DownInfo downInfo = mDownInfos.get(position);

            String name = downInfo.getName();
            if (name != null) {
                textViewDownInfoName.setText(name);
            }

            String data = downInfo.getData();
            if (data != null) {
                textDownInfoData.setText(data);
            }

        }
    }

}
