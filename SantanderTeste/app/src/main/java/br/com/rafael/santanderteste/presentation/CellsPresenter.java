package br.com.rafael.santanderteste.presentation;

import br.com.rafael.santanderteste.domain.CellCatalog;
import br.com.rafael.santanderteste.repository.BankProvider;
import br.com.rafael.santanderteste.repository.BankRepository;
import org.jetbrains.annotations.NotNull;

public class CellsPresenter implements CellsContract.Presenter{

    private CellsContract.View cellView;

    /**
     * Funcao que por meio do repositorio retorna as chamadas para view
     */
    @Override
    public void loadCellsList() {
        cellView.showLoadingCells();
        BankProvider.Companion.getCells(new BankRepository<CellCatalog>() {
            @Override
            public void onResponse(CellCatalog reponse) {
                cellView.showCellsList(reponse);
            }

            @Override
            public void onFailure(@NotNull Throwable throwable) {
                cellView.showErrorToLoadingCells();
            }
        });
    }

    
    @Override
    public void setView(CellsContract.View view) {
        cellView = view;
    }

}