package resource.com.br.santanderapp.ui.investment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import resource.com.br.santanderapp.R;
import resource.com.br.santanderapp.model.investmentModel.DownInfo;


public class InvestmentDownInfoAdapter extends RecyclerView.Adapter<InvestmentDownInfoAdapter.InvestmentDownInfoViewHolder> {
    private Context context;
    private List<DownInfo> listDownInfo;


    public InvestmentDownInfoAdapter(Context context, List<DownInfo> listDownInfo) {
        this.context = context;
        this.listDownInfo = listDownInfo;
    }

    @NonNull
    @Override
    public InvestmentDownInfoAdapter.InvestmentDownInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View createView = LayoutInflater.from(context).inflate(R.layout.item_investment, parent, false);
        return new InvestmentDownInfoAdapter.InvestmentDownInfoViewHolder(createView);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmentDownInfoViewHolder holder, int position) {
        DownInfo downInfo = listDownInfo.get(position);
        holder.settingsData(downInfo);
    }


    @Override
    public int getItemCount() {
        return listDownInfo.size();
    }

    public class InvestmentDownInfoViewHolder extends RecyclerView.ViewHolder {
        private DownInfo listDownInfo;
        private TextView name;
        private TextView data;

        public InvestmentDownInfoViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_investment_description);
            data = itemView.findViewById(R.id.item_investment_value);

        }

        public void settingsData(DownInfo listDownInfo) {
            this.listDownInfo = listDownInfo;
            fillFields(listDownInfo);
        }

        private void fillFields(final DownInfo listDownInfo) {
            name.setText(listDownInfo.getName());
            data.setText(" Baixar");
            data.setTextColor(context.getResources().getColor(R.color.colorRed));
            Drawable image = context.getResources().getDrawable(R.drawable.ic_download);
            data.setCompoundDrawablesWithIntrinsicBounds(image, null, null, null);
            data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listDownInfo.getData() == null) {
                        Toast.makeText(context, "Não há documentos para Download", Toast.LENGTH_LONG).show();
                    } else {


                    }

                }
            });
        }
    }
}
