package android.teste.com.br.testeandroidapp.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.CompoundButtonCompat;
import android.teste.com.br.testeandroidapp.R;
import android.teste.com.br.testeandroidapp.entity.Cell;
import android.teste.com.br.testeandroidapp.utils.BrPhoneNumberFormatter;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Classe para auxiliar na adição de campos ao layout
 */
public class AdicionarCamposCellHelper {
    private SubmitCallback submitCallback;
    private Context context;
    private LinearLayout linearLayout;

    /**
     * @param submitCallback callback para um potencial click em botão
     * @param context Contexto da activity
     * @param linearLayout Layout onde os campos serão adicionados
     */
    public AdicionarCamposCellHelper(SubmitCallback submitCallback, Context context, LinearLayout linearLayout) {
        this.submitCallback = submitCallback;
        this.context = context;
        this.linearLayout = linearLayout;
    }

    /**
     * Adiciona um botão ao layout
     * @param cell Dados do campo
     */
    public void adicionarBotao(final Cell cell) {
        Button button = new Button(context);
        Drawable drawable = context.getResources().getDrawable(R.drawable.round_button);
        button.setBackground(drawable);
        button.setTextColor(Color.WHITE);
        button.setText(cell.getMessage());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, cell.getTopSpacing(), 0, 0);
        button.setLayoutParams(layoutParams);

        if (cell.getHidden()) {
            button.setVisibility(View.INVISIBLE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCallback.onSubmitButtonPress();
            }
        });
        linearLayout.addView(button);
    }

    /**
     * Adiciona um checkbox ao layout
     * @param cell Dados do campo
     */
    public void adicionarCheckbox(final Cell cell) {
        CheckBox checkBox = new CheckBox(context);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, cell.getTopSpacing(), 0, 0);
        checkBox.setLayoutParams(layoutParams);

        checkBox.setText(cell.getMessage());
        checkBox.setTag(cell.getId());

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked} ,
                },
                new int[]{
                        context.getResources().getColor(R.color.colorAccent),
                        context.getResources().getColor(R.color.buttonNormal)
                }
        );

        CompoundButtonCompat.setButtonTintList(checkBox,colorStateList);

        if (cell.getShow() != null) {
            final Integer show = cell.getShow();
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    View view = linearLayout.findViewById(show);
                    if (isChecked) {
                        view.setVisibility(View.VISIBLE);
                    } else {
                        view.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        linearLayout.addView(checkBox);
    }

    /**
     * Adicionar um ImageView ao layout
     * @param cell Dados do campo
     */
    public void adicionarImage(Cell cell) {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, cell.getTopSpacing(), 0, 0);
        imageView.setLayoutParams(layoutParams);
        imageView.setTag(cell.getId());

        linearLayout.addView(imageView);
    }

    /**
     * Adiciona um TextView ao layout
     * @param cell Dados do campos
     */
    public void adicionarLabel(Cell cell) {
        TextView textView = new TextView(context);
        textView.setText(cell.getMessage());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, cell.getTopSpacing(), 0, 0);

        textView.setLayoutParams(layoutParams);
        textView.setTag(cell.getId());

        if (cell.getHidden()) {
            textView.setVisibility(View.INVISIBLE);
        }

        linearLayout.addView(textView);
    }

    /**
     * Adiciona um EditText ao layout
     * @param cell Dados do campo
     */
    public void adicionarEditText(Cell cell) {

        TextInputLayout textInputLayout = new TextInputLayout(context);
        textInputLayout.setId(cell.getId());

        EditText editText = new EditText(context);
        editText.setHint(cell.getMessage());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, cell.getTopSpacing(), 0, 0);
        editText.setLayoutParams(layoutParams);
        editText.setTag(cell.getId());

        if (cell.getHidden()) {
            textInputLayout.setVisibility(View.INVISIBLE);
        }

        switch (cell.getTypefield()) {
            case EMAIL:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case TEXT:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case TEL_NUMBER:
                editText.setInputType(InputType.TYPE_CLASS_PHONE);

                BrPhoneNumberFormatter addLineNumberFormatter = new BrPhoneNumberFormatter(new WeakReference<>(editText));
                editText.addTextChangedListener(addLineNumberFormatter);

                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789 -()"));
                setEditTextMaxLength(editText, 15);
                break;
        }

        textInputLayout.addView(editText);
        linearLayout.addView(textInputLayout);
    }

    /**
     * Define o tamanho máximo do texto do EditText
     * @param editText Campo a ser definido o tamanho
     * @param length Tamanho máximo do texto
     */
    private void setEditTextMaxLength(EditText editText, int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(FilterArray);
    }

    /**
     * Callback para lidar com um potencial click em botão
     */
    public interface SubmitCallback {
        void onSubmitButtonPress();
    }
}
