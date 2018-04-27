package io.github.pierry.better_call_me.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.R;
import io.github.pierry.better_call_me.common.support.LayoutHelper;
import io.github.pierry.better_call_me.common.support.TypefaceHelper;
import io.github.pierry.better_call_me.ui.contracts.IContactView;
import io.github.pierry.better_call_me.ui.elements.EditBox;
import io.github.pierry.better_call_me.ui.presenters.contracts.IContactPresenter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

public class ContactFragment extends Fragment implements IContactView {

  //@BindView(R.id.progress) ProgressBar progress;
  @BindView(R.id.layout) ConstraintLayout layout;
  @BindView(R.id.toolBar) Toolbar toolbar;

  @Inject IContactPresenter presenter;
  @Inject TypefaceHelper typefaceHelper;
  @Inject LayoutHelper layoutHelper;
  @Inject FinishFragment finishFragment;

  private Unbinder unbind;
  private List<EditBox> list;
  private ConstraintSet set = new ConstraintSet();

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View inflate = inflater.inflate(R.layout.contact_fragment, container, false);
    unbind = ButterKnife.bind(this, inflate);
    App.getControllerComponent(Objects.requireNonNull(getActivity())).inject(this);
    list = new ArrayList<>();
    createToolbarLayout();
    presenter.inject(this);
    presenter.sync();
    return inflate;
  }

  @Override public void buildTextView(String label, long id, int top, boolean required) {
    TextView textView = (TextView) layout.getViewById((int) id);
    if (textView != null) {
      return;
    }
    textView = new TextView(Objects.requireNonNull(getActivity()));
    textView.setHint(label);
    textView.setTypeface(typefaceHelper.regular(getActivity()));
    buildView(textView, (int) id, top);
  }

  @Override public void buildEditText(String hint, long id, int top, boolean required, boolean isEmail, boolean isNumber) {
    EditBox editBox = (EditBox) layout.getViewById((int) id);
    if (editBox != null) {
      return;
    }
    editBox = new EditBox(Objects.requireNonNull(getActivity()));
    editBox.setHint(hint);
    editBox.setTypeface(typefaceHelper.regular(getActivity()));
    editBox.setFloatingLabelText(hint);
    if (isEmail) {
      editBox.setEmail();
    } else if (isNumber) {
      editBox.setPhone();
    }
    list.add(editBox);
    buildView(editBox, (int) id, top);
  }

  @Override public void buildButton(String label, long id, int top, boolean required) {
    Button button = (Button) layout.getViewById((int) id);
    if (button != null) {
      return;
    }
    button = new Button(Objects.requireNonNull(getActivity()));
    button.setText(label);
    button.setId((int) id);
    button.setTypeface(typefaceHelper.regular(getActivity()));
    button.setBackgroundResource(R.drawable.btn);
    button.setTextColor(getResources().getColor(R.color.ia_white));
    button.setOnClickListener(view -> presenter.send(list));
    buildView(button, (int) id, top);
  }

  @Override public void buildCheckbox(String label, long id, int top, boolean required) {
    CheckBox checkBox = (CheckBox) layout.getViewById((int) id);
    if (checkBox != null) {
      return;
    }
    checkBox = new CheckBox(Objects.requireNonNull(getActivity()));
    checkBox.setText(label);
    checkBox.setId((int) id);
    checkBox.setTypeface(typefaceHelper.regular(getActivity()));
    buildView(checkBox, (int) id, top);
  }

  void buildView(View view, int id, int top) {
    view.setId((int) id);
    ConstraintLayout.LayoutParams newParams = layoutHelper.inflate(top);
    int index = layout.getChildCount();
    View childAt = layout.getChildAt(index - 1);
    layout.addView(view, newParams);
    set = createItemLayout(view, childAt, top);
    layout.setConstraintSet(set);
    set.applyTo(layout);
  }

  void createToolbarLayout() {
    set.clone(layout);
    set.connect(toolbar.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
    set.connect(toolbar.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
    set.connect(toolbar.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
    set.connect(toolbar.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
  }

  ConstraintSet createItemLayout(View view, View before, int top) {
    set.clone(layout);
    set.constrainHeight(view.getId(), ConstraintSet.WRAP_CONTENT);
    set.constrainWidth(view.getId(), ConstraintSet.MATCH_CONSTRAINT_SPREAD);
    set.connect(view.getId(), ConstraintSet.TOP, before.getId(), ConstraintSet.BOTTOM, top);
    set.connect(view.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 100);
    set.connect(view.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 100);
    return set;
  }

  @Override public void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override public void showLoader() {
    //Objects.requireNonNull(getActivity()).runOnUiThread(() -> progress.setVisibility(View.VISIBLE));
  }

  @Override public void hideLoader() {
    //Objects.requireNonNull(getActivity()).runOnUiThread(() -> progress.setVisibility(View.GONE));
  }

  @Override public void required(long id) {
    EditBox editBox = get(id);
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> editBox.invalid(getString(R.string.field_required)));
  }

  @Override public void invalid(long id) {
    EditBox editBox = get(id);
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> editBox.invalid(getString(R.string.field_invalid)));
  }

  @Override public void valid(long id) {
    EditBox editBox = get(id);
    Objects.requireNonNull(getActivity()).runOnUiThread(editBox::valid);
  }

  @Override public void navigateToFinish() {
    FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.frame, finishFragment);
    transaction.commit();
  }

  EditBox get(long id) {
    for (EditBox e : list) {
      if (e.getId() == id) {
        return e;
      }
    }
    return null;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbind.unbind();
  }
}
