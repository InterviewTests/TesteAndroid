package com.adenilson.testeandroid.contact.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.base.BaseFragment;
import com.adenilson.testeandroid.contact.ContactView;
import com.adenilson.testeandroid.contact.helper.CustomViewHelper;
import com.adenilson.testeandroid.contact.model.Cell;
import com.adenilson.testeandroid.contact.model.TypeFieldEnum;
import com.adenilson.testeandroid.contact.ui.controller.ContactPresenterImpl;
import com.adenilson.testeandroid.contact.ui.custom.CustomButton;
import com.adenilson.testeandroid.contact.ui.custom.CustomCheckbox;
import com.adenilson.testeandroid.contact.ui.custom.CustomEditText;
import com.adenilson.testeandroid.contact.ui.custom.CustomTextView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public class ContactFragment extends BaseFragment implements ContactView, CustomButton.OnSendClickListener {

    private static final String KEY_MESSAGE_SENT = "key_message_sent";
    private static final String KEY_VALUES = "key_name";
    private static final String KEY_CHECKED = "key_checked";

    @BindView(R.id.container_contact)
    LinearLayout mContainerContact;
    @BindView(R.id.progress_bar_contact)
    ProgressBar mProgressBarContact;
    @BindView(R.id.screen_success)
    ConstraintLayout mScreenSuccess;
    @BindView(R.id.text_view_send_another)
    TextView mTextViewSendAnother;
    @BindView(R.id.frame_layout)
    FrameLayout mFragmentLayout;

    private ContactPresenterImpl mPresenter;
    private CustomEditText mEditTextEmail;
    private CustomViewHelper mCustomViewHelper;
    private List<String> mValues;
    private Boolean mShowingEmail;

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new ContactPresenterImpl(this);
        mCustomViewHelper = new CustomViewHelper();
        configureElements();
        if (savedInstanceState != null) {
            boolean visible = savedInstanceState.getBoolean(KEY_MESSAGE_SENT);
            mValues = Parcels.unwrap(savedInstanceState.getParcelable(KEY_VALUES));
            mShowingEmail = savedInstanceState.getBoolean(KEY_CHECKED);
            if (visible) {
                showSuccessScreen();
            }
        }
        initData();
    }

    private void configureElements() {
        mTextViewSendAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScreenSuccess.setVisibility(View.GONE);
                mEditTextEmail.setRequired(false);
                mCustomViewHelper.clearFields();
            }
        });
    }

    private void initData() {
        mPresenter.getContact();
    }


    @Override
    public void showLoading() {
        mProgressBarContact.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBarContact.setVisibility(View.GONE);
    }

    @Override
    public void showError(int messageResourceId) {
        Toast.makeText(getContext(), messageResourceId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public void addTextView(Cell cell) {
        CustomTextView view = new CustomTextView(getContext(), cell);
        mContainerContact.addView(view);
        mCustomViewHelper.addCustomView(view);
    }

    @Override
    public void addEditText(Cell cell) {
        CustomEditText editText = new CustomEditText(getContext(), cell);
        boolean isEmail = cell.getTypeField() == TypeFieldEnum.EMAIL;
        if (isEmail) {
            mEditTextEmail = editText;
            if (mShowingEmail != null) {
                cell.setHidden(!mShowingEmail);
                editText.setVisibility();
            }
        }
        mContainerContact.addView(editText);
        mCustomViewHelper.addCustomView(editText);

    }

    @Override
    public void addCheckbox(Cell cell) {
        CustomCheckbox view = new CustomCheckbox(getContext(), cell, new CustomCheckbox.ChangeEmailVisibilityListener() {
            @Override
            public void onCheckChanged(boolean isChecked) {
                TransitionManager.beginDelayedTransition(mFragmentLayout);
                mEditTextEmail.setRequired(isChecked);
                mEditTextEmail.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                mShowingEmail = isChecked;
            }
        });
        mContainerContact.addView(view);

        if (mShowingEmail != null) {
            view.setChecked(mShowingEmail);
        }
        mCustomViewHelper.addCustomView(view);
    }

    @Override
    public void addButton(Cell cell) {
        CustomButton view = new CustomButton(getContext(), cell, this);
        mContainerContact.addView(view);
        mCustomViewHelper.addCustomView(view);
    }

    @Override
    public void addImage(Cell cell) {
        //Not necessary
    }

    @Override
    public void showSuccessScreen() {
        TransitionManager.beginDelayedTransition(mContainerContact);
        mScreenSuccess.setVisibility(View.VISIBLE);
    }

    @Override
    public void setSavedValues() {
        if (mValues != null && mValues.size() != 0) {
            mCustomViewHelper.setStrings(mValues);
        }
    }

    @Override
    public void onSendClick() {
        if (!mCustomViewHelper.checkError()) {
            mPresenter.sendMessage();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_MESSAGE_SENT, mScreenSuccess.getVisibility() == View.VISIBLE);
        outState.putParcelable(KEY_VALUES, Parcels.wrap(mCustomViewHelper.getStrings()));
        if (mShowingEmail != null) {
            outState.putBoolean(KEY_CHECKED, mShowingEmail);
        }
    }
}
