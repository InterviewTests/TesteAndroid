package com.alex.testeandroid.presentation.contact;

import com.alex.testeandroid.data.entities.contact.Cell;
import com.alex.testeandroid.domain.ContactInteractor;
import com.alex.testeandroid.presentation.BasePresenter;
import com.alex.testeandroid.presentation.helpers.ValidatorHelper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by alex on 27/08/18.
 */
public class ContactPresenter implements BasePresenter {

    //region FIELDS
    private ContactView view;
    private ContactInteractor contactInteractor;
    private Disposable disposable;
    //endregion

    //region CONSTRUCT
    public ContactPresenter(ContactView view) {
        this.view = view;
        this.contactInteractor = new ContactInteractor();
    }
    //endregion

    //region METHODS
    //region PUBLIC METHODS
    public void getContactForm() {
        view.showProgress(true);

        disposable = contactInteractor.getContactForm()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Cell>>() {
                    @Override
                    public void accept(List<Cell> cells) throws Exception {
                        if (view == null) return;
                        view.showProgress(false);
                        view.buildForm(cells);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view == null) return;
                        view.showProgress(false);
                        view.showMessageErrorRequest();
                    }
                });
    }

    public void send(String name, String email, String phone, boolean registerEmail) {
        ValidatorHelper validatorHelper = new ValidatorHelper();

        boolean error = false;
        if (validatorHelper.isEmpty(name)) {
            view.showErrorName();
            error = true;
        }

        if (registerEmail && !validatorHelper.isValidEmail(email)) {
            view.showErrorEmail();
            error = true;
        }

        if (!validatorHelper.isValidPhone(phone)) {
            view.showErrorPhone();
            error = true;
        }

        if (error) return;

        view.messageSendSuccess();

    }
    //endregion

    //region OVERRIDES METHODS
    @Override
    public void detachView() {
        if (disposable != null) disposable.dispose();
        if (view != null) view = null;
    }
    //endregion
    //endregion
}
