package resource.com.br.santanderapp.ui.investment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import resource.com.br.santanderapp.R;
import resource.com.br.santanderapp.model.investmentModel.Info;

public class InvestmentInfoAdapter extends RecyclerView.Adapter<InvestmentInfoAdapter.InvestmentViewHolder> {
    private Context context;
    private List<Info> listInfo;


    public InvestmentInfoAdapter(Context context, List<Info> listInfo) {
        this.context = context;
        this.listInfo = listInfo;
    }

    @NonNull
    @Override
    public InvestmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View createView = LayoutInflater.from(context).inflate(R.layout.item_investment, parent, false);
        return new InvestmentViewHolder(createView);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmentViewHolder holder, int position) {
        Info info = listInfo.get(position);
        holder.settingsData(info);
    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public class InvestmentViewHolder extends RecyclerView.ViewHolder {
        private Info listInfo;
        private TextView name;
        private TextView data;

        public InvestmentViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_investment_description);
            data = itemView.findViewById(R.id.item_investment_value);

        }

        public void settingsData(Info listInfo) {
            this.listInfo = listInfo;
            fillFields(listInfo);
        }

        private void fillFields(Info listInfo) {
            name.setText(listInfo.getName());
            data.setText(listInfo.getData());
        }
    }
}
