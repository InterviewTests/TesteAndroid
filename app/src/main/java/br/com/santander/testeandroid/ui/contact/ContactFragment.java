package br.com.santander.testeandroid.ui.contact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.ui.contact.domain.models.Cell;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactFragment extends Fragment implements ContactView {
    private View view;
    private ContactPresenter presenter;
    private AppCompatActivity activity;

    @BindView(R.id.ll_container_form)
    LinearLayout containerFields;
    @BindView(R.id.sv_form)
    ScrollView containerForm;
    @BindView(R.id.rl_msg_successfully_sent)
    RelativeLayout containerMessageSuccess;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact, null);
        ButterKnife.bind(this, view);
        activity = ((AppCompatActivity) getActivity());
        presenter = new ContactPresenter(this);
        presenter.loadScreenInfo();

        return view;
    }

    @Override
    public void prepareToolbar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = view.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.contact);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void loadInformationSuccess(List<Cell> cellsList) {
        View view;
        containerFields.removeAllViews();
        final HashMap<Integer, View> hiddenViews = new HashMap<>();
        final HashMap<Cell, EditText> viewsToValidate = new HashMap<>();

        for (final Cell cell : cellsList) {
            switch (cell.getType()) {
                case TEXT:
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_textview, null);

                    TextView textView = view.findViewById(R.id.textview);

                    textView.setText(cell.getMessage());

                    if(cell.isHidden()) {
                        view.setVisibility(View.GONE);
                        hiddenViews.put(cell.getId(), view);
                    }

                    containerFields.addView(view);
                    break;
                case FIELD:
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_edittext, null);

                    TextInputLayout inputLayout = view.findViewById(R.id.text_input_layout);
                    final EditText editText = inputLayout.getEditText();
                    ImageButton clearButton = view.findViewById(R.id.ib_clear_button);

                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(inputLayout.getLayoutParams());
                    params.setMargins(0, cell.getTopSpacing(), 0, 0);

                    if (cell.getTypeField() != null) {
                        switch (cell.getTypeField()) {
                            case EMAIL:
                                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                                break;
                            case TEL_NUMBER:
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                break;
                            default:
                                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                                break;
                        }
                    }

                    inputLayout.setLayoutParams(params);
                    inputLayout.setHint(cell.getMessage());

                    if(cell.isHidden()) {
                        view.setVisibility(View.GONE);
                        hiddenViews.put(cell.getId(), view);
                    }

                    if(cell.isRequired()) {
                        viewsToValidate.put(cell, editText);
                    }

                    clearButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editText.setText("");
                        }
                    });

                    containerFields.addView(view);
                    break;

                case CHECKBOX:
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_checkbox, null);

                    AppCompatCheckBox checkBox = view.findViewById(R.id.compat_checkbox);

                    checkBox.setText(cell.getMessage());

                    LinearLayout.LayoutParams checkboxLayoutParams =
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    checkboxLayoutParams.setMargins(0, cell.getTopSpacing(), 0, 0);
                    checkBox.setLayoutParams(checkboxLayoutParams);

                    if(cell.isHidden()) {
                        view.setVisibility(View.GONE);
                        hiddenViews.put(cell.getId(), view);
                    }

                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked) {
                                hiddenViews.get(cell.getShow()).setVisibility(View.VISIBLE);
                                hiddenViews.get(cell.getShow()).requestFocus();
                            } else {
                                hiddenViews.get(cell.getShow()).setVisibility(View.GONE);
                                containerFields.requestFocus();
                            }
                        }
                    });

                    containerFields.addView(view);
                    break;

                case SEND:
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_button, null);

                    Button button = view.findViewById(R.id.button);

                    LinearLayout.LayoutParams buttonLayoutParams =
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    buttonLayoutParams.setMargins(0, cell.getTopSpacing(), 0, 0);

                    button.setText(cell.getMessage());
                    button.setLayoutParams(buttonLayoutParams);

                    if(cell.isHidden()) {
                        view.setVisibility(View.GONE);
                        hiddenViews.put(cell.getId(), view);
                    }

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            presenter.validateFields(viewsToValidate);
                        }
                    });

                    containerFields.addView(view);
                    break;
            }
        }
    }

    @Override
    public void loadInformationFailed() {

    }

    @Override
    public void showSuccessMessage() {
        containerForm.setVisibility(View.GONE);
        containerMessageSuccess.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.bt_send_new_message)
    public void sendNewMessage() {
        containerMessageSuccess.setVisibility(View.GONE);
        containerForm.setVisibility(View.VISIBLE);
    }
}
