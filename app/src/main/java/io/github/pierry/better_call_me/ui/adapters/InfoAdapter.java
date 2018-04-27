package io.github.pierry.better_call_me.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.pierry.better_call_me.R;
import io.github.pierry.better_call_me.domain.viewmodels.Data;
import io.github.pierry.better_call_me.ui.holders.InfoHolder;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoHolder> {

  private List<Data> items;

  public void addItems(List<Data> items) {
    this.items = items;
  }

  @NonNull @Override public InfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_adapter, parent, false);
    return new InfoHolder(itemView);
  }

  @Override public void onBindViewHolder(@NonNull InfoHolder holder, int position) {
    Data data = items.get(position);
    holder.bind(data);
  }

  @Override public int getItemCount() {
    return items != null ? items.size() : 0;
  }
}
