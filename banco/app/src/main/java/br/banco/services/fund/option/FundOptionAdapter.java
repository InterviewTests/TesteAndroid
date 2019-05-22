package br.banco.services.fund.option;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.banco.services.R;

public class FundOptionAdapter extends RecyclerView.Adapter<FundOptionAdapter.MyViewHolder> {

    private List<FundOption> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public FundOptionAdapter(List<FundOption> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fund_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        FundOption fund = moviesList.get(position);

        holder.title.setText(fund.getTitle());
        holder.genre.setText(fund.getGenre());
        holder.year.setText(fund.getYear());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
