package com.study.vipoliveira.investapp.ui.contact

import android.text.InputType
import android.view.View
import android.widget.EditText
import com.study.vipoliveira.investapp.domain.ContactDomain
import com.study.vipoliveira.investapp.domain.SchedulersFacade
import com.study.vipoliveira.investapp.util.isValidEmail
import com.study.vipoliveira.investapp.util.isValidPhoneNumber
import com.study.vipoliveira.investapp.util.isValidText
import io.reactivex.disposables.CompositeDisposable
import java.util.ArrayList

class ContactFormPresenter(private val domain: ContactDomain,
                           private val facade: SchedulersFacade,
                           private val disposable: CompositeDisposable): ContactFormContract.Presenter {

    private lateinit var view: ContactFormContract.View


    override fun getContactForm() {
        disposable.add(
                domain.requestContactForm()
                        .subscribeOn(facade.io())
                        .observeOn(facade.ui())
                        .doOnSubscribe {
                            _ -> view.displayLoadingUI()
                        }
                        .subscribe({
                            items -> view.hideLoadingUI()
                            view.createContactForm(items)
                        },
                                {
                                    error ->
                                    error.message?.let { view.displayError(it) }
                                })
        )
    }

    override fun attach(view: ContactFormContract.View) {
        this.view = view
    }

    override fun validateInput(views: ArrayList<View>): Boolean {
        for (view in views) {
            if (view is EditText) {
                if (view.isShown()) {
                    if (view.inputType == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
                        if (!view.text.toString().isValidEmail()) {
                            this.view.showEmailError()
                            return false
                        }
                    } else if (view.inputType == InputType.TYPE_CLASS_PHONE){
                        if (!view.text.toString().isValidPhoneNumber()) {
                            this.view.showPhoneError()
                            return false
                        }
                    } else {
                        if (!view.text.toString().isValidText()) {
                            this.view.showTextError()
                            return false
                        }
                    }
                }
            }
        }
        return true
    }

    override fun sendInfo() {

    }

    override fun clearDiposable() {
        disposable.clear()
    }
}