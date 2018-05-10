package android.teste.com.br.testeandroidapp.adapter;

import android.content.Context;
import android.teste.com.br.testeandroidapp.entity.Cell;
import android.widget.LinearLayout;

import java.util.List;

public class CellAdapter {
    private AdicionarCamposCellHelper.SubmitCallback submitCallback;
    private Context context;
    private List<Cell> cells;
    private LinearLayout linearLayout;

    public CellAdapter(AdicionarCamposCellHelper.SubmitCallback submitCallback, Context context, List<Cell> cells, LinearLayout linearLayout) {
        this.submitCallback = submitCallback;
        this.context = context;
        this.cells = cells;
        this.linearLayout = linearLayout;
    }

    /**
     * Preenche o layout com os campos contidos na List de {@link Cell}
     */
    public void preencherLayout() {

        AdicionarCamposCellHelper adicionarCamposCellHelper = new AdicionarCamposCellHelper(submitCallback, context, linearLayout);

        for (Cell cell : cells) {

            switch (cell.getType()) {
                case FIELD:
                    adicionarCamposCellHelper.adicionarEditText(cell);
                break;
                case TEXT:
                    adicionarCamposCellHelper.adicionarLabel(cell);
                break;
                case IMAGE:
                    adicionarCamposCellHelper.adicionarImage(cell);
                break;
                case CHECKBOX:
                    adicionarCamposCellHelper.adicionarCheckbox(cell);
                break;
                case SEND:
                    adicionarCamposCellHelper.adicionarBotao(cell);
                break;
            }
        }
    }

}
