package com.bruno.santander.santanderchallenge.contato.presentation;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bruno.santander.santanderchallenge.IEditTextContato;
import com.bruno.santander.santanderchallenge.R;
import com.bruno.santander.santanderchallenge.UIValidationUtils;
import com.bruno.santander.santanderchallenge.contato.UIGeneratorContato;
import com.bruno.santander.santanderchallenge.contato.model.Cell;
import com.bruno.santander.santanderchallenge.contato.model.ListCell;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ContatoPresenter implements ContatoContract.Presenter {

    private Activity activity;
    private ContatoContract.View view;
    private CompositeDisposable disposable;
    private IEditTextContato editTextContato;

    public ContatoPresenter(Activity activity, ContatoContract.View view){
        this.activity = activity;
        this.view = view;
    }

    public void setEditTextContato(IEditTextContato editTextContato){
        this.editTextContato = editTextContato;
    }

    @Override
    public void getCells() {
        disposable = new CompositeDisposable();

        disposable.add(
                Single.fromCallable(() -> {
                            ObjectMapper mapper = new ObjectMapper();

                            return mapper.readValue(activity.getAssets().open("cells.json"), ListCell.class);
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((myObject) -> view.onSuccessGetCells(myObject)));
    }

    public void setScreenData(CoordinatorLayout coordinatorLayout, ListCell cells){
        LinearLayout linearLayout = coordinatorLayout.findViewById(R.id.root);
        RelativeLayout relativeSucesso = coordinatorLayout.findViewById(R.id.relative_sucesso);

        for(Cell cell : cells.getListCells()){
            if(cell.getType() == Cell.Type.text.val()){
                linearLayout.addView(UIGeneratorContato.getTextView(activity, cell));
            }

            if(cell.getType() == Cell.Type.field.val()){
                TextInputLayout textInputLayout = UIGeneratorContato.getTextInputLayout(activity, cell);
                final EditText editText = (EditText) ((FrameLayout)textInputLayout.getChildAt(0)).getChildAt(0);

                editText.setOnFocusChangeListener((v, hasFocus) -> {
                    if(!hasFocus){
                        UIValidationUtils.validateSingleEditText(activity, editText);
                    }
                });

                editTextContato.addEditText(editText);
                linearLayout.addView(textInputLayout);
            }

            if(cell.getType() == Cell.Type.image.val()){
                linearLayout.addView(UIGeneratorContato.getImageView(activity, cell));
            }

            if(cell.getType() == Cell.Type.checkbox.val()){
                final AppCompatCheckBox checkBox = UIGeneratorContato.getCheckBox(activity, cell);

                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    EditText editText = linearLayout.findViewById((int)checkBox.getTag());
                    FrameLayout frameLayout = (FrameLayout) editText.getParent();
                    TextInputLayout til = (TextInputLayout) frameLayout.getParent();

                    if(isChecked){
                        editText.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.VISIBLE);
                        til.setVisibility(View.VISIBLE);
                    }
                    else{
                        editText.setVisibility(View.GONE);
                        frameLayout.setVisibility(View.GONE);
                        til.setVisibility(View.GONE);
                    }
                });

                linearLayout.addView(checkBox);
            }

            if(cell.getType() == Cell.Type.send.val()){
                Button button = UIGeneratorContato.getButton(activity, cell);

                button.setOnClickListener(v -> {
                    if(UIValidationUtils.validateEditTexts(activity, coordinatorLayout, editTextContato.getEditTextList())){
                        linearLayout.setVisibility(View.GONE);
                        relativeSucesso.setVisibility(View.VISIBLE);
                    }
                });

                linearLayout.addView(button);
            }
        }
    }

    @Override
    public void saveContato() {
//        TODO Fake saving using RxJava
    }

    @Override
    public void dispose() {
        if(disposable.size() > 0 && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
