package br.com.rafael.santanderteste.presentation.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import br.com.rafael.santanderteste.R;
import br.com.rafael.santanderteste.domain.entity.Cell;
import br.com.rafael.santanderteste.domain.entity.CellCatalog;
import br.com.rafael.santanderteste.domain.ActivityDomain;
import br.com.rafael.santanderteste.helper.ViewHelper;
import br.com.rafael.santanderteste.presentation.FormContract;
import br.com.rafael.santanderteste.presentation.FormPresenter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormContactFragment extends Fragment implements FormContract.View {

    private FormPresenter presenter;

    private LinearLayout formLayoutMain;

    private List<String> errorList = new ArrayList<>();

    public FormContactFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        formLayoutMain = view.findViewById(R.id.form_layout_contact);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.loadCellsList();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new FormPresenter();
        presenter.setView(this);
    }

    @Override
    public void showCellsList(@NotNull CellCatalog cellCatalog) {

        // Lista de configuracao de intens de XML retornados pela API
        List<Cell> listCell = cellCatalog.getCells();
        // HashMap onde a chave é o ID do Item e o valor é a View renderizada
        Map<Integer, View> viewListId = new HashMap<>();


        for (int i = 0; i < listCell.size(); i++) {
            // Item celula da API
            Cell cell = listCell.get(i);

            // Configuracao de LayoutInflater
            LayoutInflater inflater = LayoutInflater.from(getContext());

            // Retornado a view que representa o item de acordo o tipo passado pela API
            View viewItem = ActivityDomain.getFormContactItemView(cell, inflater);

            // Adiciona cada item retornado no LinearLayout do Formularo
            formLayoutMain.addView(viewItem);

            //Adciona no hashmap o ID e a View correspondente
            viewListId.put(cell.getId(), viewItem);

            // Realiza todas as configuracoes da View segundo retorno da API para cada
            // item de acordo seu tipo
            setupView(viewItem, cell, viewListId, listCell);

        }


    }

    /**
     * Realiza todas as configuracoes da View segundo retorno da API para cada item de acordo seu tipo
     * Essa Veirificacao passa por cada View setada no container do LinearLayout
     * @param view      View do item obtido no XML por meio do tipo retornado pela API
     * @param cell      Configuracao de celula retornado pela API
     * @param listaView Lista de views para cada item contendo ID / View
     * @return
     */
    private View setupView(View view, final Cell cell, final Map<Integer, View> listaView, final List<Cell> listCell) {
        Integer view_type = cell.getType();

        if (view_type == 1) {
            // EditText type
            TextInputLayout inputLayout = view.findViewById(R.id.inputLayout);
            inputLayout.setHint(cell.getMessage());
            TextInputEditText textInputEditText = view.findViewById(R.id.inputEdit);
            textInputEditText.setInputType(ViewHelper.Companion.get_input_type(cell.getTypefield()));
            inputLayout.getEditText().addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        } else if (view_type == 2) {
            // TextView type
            TextView textView = view.findViewById(R.id.textViewForm);
            textView.setText(cell.getMessage());
        } else if (view_type == 3) {
            // ImageView type
            ImageView imageView = view.findViewById(R.id.imageViewLayout);
            imageView.setBackgroundResource(R.drawable.ic_launcher_background);
        } else if (view_type == 4) {
            // CheckBox type
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            checkBox.setText(cell.getMessage());

            final View view1 = listaView.get(cell.getShow());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (checked) {
                        view1.setVisibility(View.VISIBLE);
                    } else {
                        TextInputLayout input = view1.findViewById(R.id.inputLayout);
                        input.setErrorEnabled(false);
                        if(!errorList.isEmpty()){
                            errorList.remove(0);
                        }
                        view1.setVisibility(View.GONE);
                    }
                }
            });

        } else if (view_type == 5) {
            // Button type
            Button button = view.findViewById(R.id.button);
            button.setText(cell.getMessage());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (Integer key : listaView.keySet()) {
                        View valueView = listaView.get(key);
                        boolean result = valueView instanceof TextInputLayout;

                        if (result) {
                            // Verifica se o component View é do tipo EditText(Input)
                            TextInputLayout texto = valueView.findViewById(R.id.inputLayout);

                            String input_value = texto.getEditText().getText().toString();

                            // Realiza a verificacao dos campos somente para View de Input que estão
                            // visiveis para o usuario
                            if (texto.getVisibility() == View.VISIBLE) {

                                switch (texto.getEditText().getInputType()) {
                                    case InputType.TYPE_CLASS_TEXT:
                                        if (input_value.length() < 1) {
                                            errorList.add("error");
                                            texto.setError("Favor informar seu nome completo.");
                                        } else {
                                            texto.setErrorEnabled(false);
                                        }
                                        break;
                                    case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
                                        if (!ViewHelper.Companion.isEmailValid(input_value)) {
                                            texto.setError("Favor informar um e-mail valido.");
                                            errorList.add("error");
                                        } else {
                                            texto.setErrorEnabled(false);
                                        }
                                        break;
                                    case InputType.TYPE_CLASS_PHONE:
                                        String phone_value = input_value.replaceAll("[^\\d.]", "");
                                        if (phone_value.length() == 11) {
                                            texto.setErrorEnabled(false);
                                        } else if (phone_value.length() == 10) {
                                            texto.setErrorEnabled(false);
                                        } else {
                                            errorList.add("error");
                                            texto.setError("Favor informar um número de telefone valido ");
                                        }
                                        break;
                                }
                            }

                        }
                    }
                    // Caso a lista de erro esteja vazia o Fragment de sucesso é exobido
                    if (errorList.size() == 0) {
                        ViewHelper.Companion.replace_framgment(getFragmentManager(), R.id.frameMain, new SuccessFragment(), "success");
                    }
                    errorList.clear();

                }
            });
        }
        return view;
    }

    @Override
    public void showLoadingCells() {
    }

    @Override
    public void showErrorToLoadingCells() {

    }
}
