package io.github.pierry.better_call_me.ui.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.pierry.better_call_me.R;
import io.github.pierry.better_call_me.domain.viewmodels.Data;

public class InfoHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.name) TextView name;
  @BindView(R.id.data) TextView data;

  private Context context;

  public InfoHolder(View itemView) {
    super(itemView);
    this.context = itemView.getContext();
    ButterKnife.bind(this, itemView);
  }

  public void bind(Data data) {
    name.setText(data.getName());
    if (data.getData() != null) {
      this.data.setText(data.getData());
      return;
    }
    this.data.setTextColor(context.getResources().getColor(R.color.error));
    this.data.setText(context.getString(R.string.download));
  }
}
