package io.github.pierry.better_call_me.ui.contracts;

public interface IContactView {

  void buildTextView(String label, long id, int top, boolean required);

  void buildEditText(String hint, long id, int top, boolean required, boolean isEmail, boolean isNumber);

  void buildButton(String label, long id, int top, boolean required);

  void buildCheckbox(String label, long id, int top, boolean required);

  void showLoader();

  void hideLoader();

  void required(long id);

  void invalid(long id);

  void valid(long id);

  void navigateToFinish();
}
