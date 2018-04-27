package io.github.pierry.better_call_me.ui.presenters;

import android.content.Context;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.common.ContactHelper;
import io.github.pierry.better_call_me.common.support.RxBus;
import io.github.pierry.better_call_me.domain.Cell;
import io.github.pierry.better_call_me.domain.Type;
import io.github.pierry.better_call_me.domain.TypeField;
import io.github.pierry.better_call_me.interactors.contracts.IContactInteractor;
import io.github.pierry.better_call_me.ui.contracts.IContactView;
import io.github.pierry.better_call_me.ui.elements.EditBox;
import io.github.pierry.better_call_me.ui.presenters.contracts.IContactPresenter;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

public class ContactPresenter implements IContactPresenter {

  @Inject IContactInteractor interactor;
  @Inject CompositeDisposable compositeDisposable;
  @Inject RxBus rxBus;
  @Inject ContactHelper contactHelper;

  private IContactView contactView;

  private List<Cell> cells;

  public ContactPresenter(Context context) {
    App.getControllerComponent(context).inject(this);
  }

  @Override public void inject(IContactView contactView) {
    this.contactView = contactView;
  }

  @Override public void sync() {
    contactView.showLoader();
    interactor.fetchCells();
  }

  @Override public void onStart() {
    compositeDisposable.add(rxBus.asFlowable().subscribe(event -> {
      if (event instanceof List) {
        contactView.hideLoader();
        cells = (List<Cell>) event;
        handle(cells);
      }
    }));
  }

  @Override public void send(List<EditBox> list) {
    boolean isValid = false;
    for (EditBox e : list) {
      isValid = isValid(e.getText().toString(), e.getId());
    }
    if (isValid) {
      contactView.navigateToFinish();
    }
  }

  private boolean isValid(String text, long id) {
    for (Cell c : cells) {
      if (c.getId() == id) {
        if (!contactHelper.required(c, text)) {
          contactView.required(c.getId());
          return false;
        }
        if (!contactHelper.validField(c, text)) {
          contactView.invalid(c.getId());
          return false;
        } else {
          contactView.valid(c.getId());
          return true;
        }
      }
    }
    return false;
  }

  private void handle(List<Cell> cells) {
    for (Cell c : cells) {
      if (c.getType() == Type.field) {
        boolean isEmail = false;
        if (c.getTypefield() == TypeField.email) {
          isEmail = true;
        }
        boolean isNumber = false;
        if (c.getTypefield() == TypeField.number) {
          isNumber = true;
        }
        contactView.buildEditText(c.getMessage(), c.getId(), (int) c.getTopSpacing(), c.isRequired(), isEmail, isNumber);
        continue;
      }
      if (c.getType() == Type.send) {
        contactView.buildButton(c.getMessage(), c.getId(), (int) c.getTopSpacing(), c.isRequired());
        continue;
      }
      if (c.getType() == Type.checkbox) {
        contactView.buildCheckbox(c.getMessage(), c.getId(), (int) c.getTopSpacing(), c.isRequired());
      }
      if (c.getType() == Type.text) {
        contactView.buildTextView(c.getMessage(), c.getId(), (int) c.getTopSpacing(), c.isRequired());
      }
    }
  }
}
