package io.github.pierry.better_call_me.ui.elements;

import android.content.Context;
import android.text.InputType;
import com.rengwuxian.materialedittext.MaterialEditText;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.R;
import io.github.pierry.better_call_me.common.support.TypefaceHelper;
import javax.inject.Inject;

public class EditBox extends MaterialEditText {

  @Inject TypefaceHelper typefaceHelper;

  public EditBox(Context context) {
    super(context);
    App.getControllerComponent(context).inject(this);
    setShowClearButton(true);
    setSingleLine();
    setTypeface(typefaceHelper.regular(context));
    setHighlightColor(context.getResources().getColor(R.color.black));
    setPrimaryColor(getResources().getColor(R.color.gray));
    setAccentTypeface(typefaceHelper.regular(context));
    setFloatingLabel(FLOATING_LABEL_NORMAL);
    setFloatingLabelTextColor(getResources().getColor(R.color.black));
  }

  public void invalid(String msg) {
    setUnderlineColor(getResources().getColor(R.color.error));
    setErrorColor(getResources().getColor(R.color.error));
    setError(msg);
  }

  public void valid() {
    setUnderlineColor(getResources().getColor(R.color.success));
  }

  public void setEmail() {
    setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
  }

  public void setPhone() {
    setInputType(InputType.TYPE_CLASS_PHONE);
  }
}
