package br.com.rafael.santanderteste.presentation.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormContactFragment extends Fragment implements FormContract.View {

    private FormPresenter presenter;

    private LinearLayout formLayoutMain;

    public FormContactFragment() {
        // Required empty public constructor
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
            setupView(viewItem, cell, viewListId);

        }


    }

    /**
     * Realiza todas as configuracoes da View segundo retorno da API para cada item de acordo seu tipo
     * @param view View do item obtido no XML por meio do tipo retornado pela API
     * @param cell Configuracao de celula retornado pela API
     * @param listaView Lista de views para cada item contendo ID / View
     * @return
     */
    private View setupView(View view, Cell cell, Map<Integer, View> listaView) {
        Integer view_type = cell.getType();
        if (view_type == 1) {
            TextInputLayout inputLayout = view.findViewById(R.id.inputLayout);
            inputLayout.setHint(cell.getMessage());
            TextInputEditText textInputEditText = view.findViewById(R.id.inputEdit);
            textInputEditText.setInputType(ViewHelper.Companion.get_input_type(cell.getTypefield()));

        } else if (view_type == 2) {
            TextView textView = view.findViewById(R.id.textViewForm);
            textView.setText(cell.getMessage());
        } else if (view_type == 3) {
            ImageView imageView = view.findViewById(R.id.imageViewLayout);
            imageView.setBackgroundResource(R.drawable.ic_launcher_background);
        } else if (view_type == 4) {
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            checkBox.setText(cell.getMessage());

            final View view1 = listaView.get(cell.getShow());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if(checked) {
                        view1.setVisibility(View.VISIBLE);
                    } else {
                        view1.setVisibility(View.GONE);
                    }
                }
            });

        } else if (view_type == 5) {
            Button button = view.findViewById(R.id.button);
            button.setText(cell.getMessage());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewHelper.Companion.replace_framgment(getFragmentManager(), R.id.frameLayout, new FundFragment(), "invest");
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
