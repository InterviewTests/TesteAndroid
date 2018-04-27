package io.github.pierry.better_call_me.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.R;
import java.util.Objects;
import javax.inject.Inject;

public class FinishFragment extends Fragment {

  @BindView(R.id.resend) Button resend;

  @Inject ContactFragment contactFragment;

  private Unbinder unbind;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View inflate = inflater.inflate(R.layout.finish_fragment, container, false);
    unbind = ButterKnife.bind(this, inflate);
    App.getControllerComponent(Objects.requireNonNull(getActivity())).inject(this);
    return inflate;
  }

  @OnClick(R.id.resend) public void resend() {
    FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.frame, contactFragment);
    transaction.commit();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbind.unbind();
  }
}
