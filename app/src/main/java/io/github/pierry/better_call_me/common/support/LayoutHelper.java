package io.github.pierry.better_call_me.common.support;

import android.support.constraint.ConstraintLayout;
import android.view.ViewGroup;

public class LayoutHelper {

  public ConstraintLayout.LayoutParams inflate(int top) {
    return new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
  }
}
