package info.dafle.testeandroid.mvp.contato;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import info.dafle.testeandroid.R;
import info.dafle.testeandroid.model.Cell;
import info.dafle.testeandroid.util.MaskWatcher;

public class ContatoFragment extends Fragment implements ContatoContract.View, View.OnClickListener {

    private static final String TAG = ContatoContract.class.getSimpleName();
    private ContatoContract.Presenter mPresenter;
    private Context context;
    private View root;
    private LinearLayout layout;
    private ProgressBar progressBar;
    private List<Cell> cells;
    private Typeface typeFace;
    private ConstraintLayout cc_message_success;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_contato, container, false);
        context = root.getContext();
        typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/DINPro-Light.ttf");
        progressBar = root.findViewById(R.id.progress);
        cc_message_success = root.findViewById(R.id.cc_message_success);
        TextView title = root.findViewById(R.id.title);
        TextView tv_thankyou = root.findViewById(R.id.tv_thankyou);
        TextView tv_message_success = root.findViewById(R.id.tv_message_success);
        TextView tv_send_new_message = root.findViewById(R.id.tv_send_new_message);
        title.setTypeface(typeFace);
        tv_thankyou.setTypeface(typeFace);
        tv_message_success.setTypeface(typeFace);
        tv_send_new_message.setTypeface(typeFace);
        tv_send_new_message.setOnClickListener(this);
        layout = root.findViewById(R.id.main);
        new ContatoPresenter(this);
        return root;
    }

    @Override
    public void setPresenter(@NonNull ContatoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void buildLayout(@NonNull List<Cell> cells) {
        this.cells = cells;

        for (Cell cell:cells) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, cell.getTopSpacing(), 0, 0);

            switch (Type.getFromInt(cell.getType())) {

                case field:

                    EditText editText = new EditText(context);

                    switch (TypeField.getFromObject(cell.getTypefield())) {

                        case text:

                            editText.setInputType(InputType.TYPE_CLASS_TEXT);
                            break;

                        case email:
                            editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                            break;

                        case telNumber:
                            editText.setInputType(InputType.TYPE_CLASS_PHONE);
                            editText.addTextChangedListener(new MaskWatcher(editText, "(##) #####-#####"));
                            break;
                    }

                    editText.setHint(cell.getMessage());
                    editText.setTypeface(typeFace);
                    editText.setTextColor(getResources().getColor(android.R.color.black));
                    LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    editText.setLayoutParams(editTextParams);
                    TextInputLayout textInputLayout = new TextInputLayout(context);
                    textInputLayout.setId(cell.getId());
                    textInputLayout.setLayoutParams(params);
                    textInputLayout.addView(editText, editTextParams);
                    layout.addView(textInputLayout);

                    break;
                case text:

                    TextView tv = new TextView(context);
                    tv.setId(cell.getId());
                    tv.setText(cell.getMessage());
                    tv.setTypeface(typeFace);
                    tv.setTextColor(context.getResources().getColor(R.color.gray));
                    tv.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                    tv.setLayoutParams(params);
                    layout.addView(tv);

                    break;
                case image:

                    ImageView img = new ImageView(context);
                    img.setId(cell.getId());
                    img.setContentDescription(cell.getMessage());
                    img.setLayoutParams(params);
                    layout.addView(img);


                    break;
                case checkbox:

                    CheckBox checkBox = new CheckBox(context);
                    checkBox.setId(cell.getId());
                    checkBox.setText(cell.getMessage());
                    checkBox.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                    checkBox.setTextColor(context.getResources().getColor(R.color.gray));
                    checkBox.setTypeface(typeFace);
                    checkBox.setLayoutParams(params);
                    layout.addView(checkBox);

                    break;
                case send:

                    Button button = new Button(context);
                    button.setId(cell.getId());
                    button.setText(cell.getMessage());
                    button.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                    button.setTextColor(getResources().getColor(R.color.white));
                    button.setLayoutParams(params);
                    button.setTypeface(typeFace);
                    button.setBackground(getResources().getDrawable(R.drawable.background_button));
                    button.setOnClickListener(this);
                    layout.addView(button);

                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_send_new_message:
                showMessageSuccess(false);
                layout.removeAllViews();
                buildLayout(cells);
            break;

            default:
                mPresenter.validateForm(cells);
            break;
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(context, message + context.getResources().getString(R.string.error_form_contact), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void removeErrorFromView(TextInputLayout textInputLayout) {
        textInputLayout.setErrorEnabled(false);
    }

    @Override
    public void setErrorToEditText(TextInputLayout textInputLayout, String error) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(error);
    }

    @Override
    public View findViewById(int id) {
        return root.findViewById(id);
    }

    @Override
    public void showMessageSuccess(boolean show) {
        cc_message_success.setVisibility(show ? View.VISIBLE : View.GONE);
        layout.setVisibility(show ? View.GONE : View.VISIBLE);
    }
}
