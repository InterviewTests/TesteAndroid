package io.github.pierry.better_call_me.ui.presenters.contracts;

import io.github.pierry.better_call_me.domain.Cell;
import io.github.pierry.better_call_me.ui.contracts.IContactView;
import io.github.pierry.better_call_me.ui.elements.EditBox;
import java.util.List;

public interface IContactPresenter {

  void inject(IContactView contactView);

  void sync();

  void onStart();

  void send(List<EditBox> list);

}
