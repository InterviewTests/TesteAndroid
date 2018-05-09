package rrzaniolo.testandroidsantander.main.contact;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rrzaniolo.testandroidsantander.base.BasePresenter;
import rrzaniolo.testandroidsantander.main.contact.Custom.ContractViewBuilder;
import rrzaniolo.testandroidsantander.network.NetworkManager;
import rrzaniolo.testandroidsantander.network.helper.CellMaper;
import rrzaniolo.testandroidsantander.network.models.response.GetCellsResponse;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */
public class ContactPresenter extends BasePresenter implements ContactContract.Presenter{

    //region --- Variables
    ContactContract.View contractView;
    @Nullable private ContactContract.View getContractView() {
        return contractView;
    }
    private void setContractView(@NonNull ContactContract.View contractView) {
        this.contractView = contractView;
    }
    //endregion

    //region --- Constructor
    ContactPresenter(ContactContract.View contractView) {
        setContractView(contractView);
    }
    //endregion

    //region --- Private Methods ---
    private void onSuccess(GetCellsResponse response){
        if(getContractView() != null) {
            ContractViewBuilder contractViewBuilder = new ContractViewBuilder(getContractView().getForm());

            getContractView().getForm()
                    .setCellViewList(
                            contractViewBuilder.processContractCells(CellMaper.map(response))
                    );

            getContractView().setFormListener();
        }
    }
    //endregion

    //region --- Contract Methods
    @Override
    public void onStart() {
        if(getContractView() != null){
            getContractView().hideSuccess();
            getContractView().hideError();
            getContractView().showLoading();

            NetworkManager.getInstance().getCells().enqueue(new Callback<GetCellsResponse>() {
                @Override
                public void onResponse(@NonNull Call<GetCellsResponse> call, @NonNull Response<GetCellsResponse> response) {
                    getContractView().hideLoading();
                    onSuccess(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<GetCellsResponse> call, @NonNull Throwable t) {
                    getContractView().hideLoading();
                    getContractView().showError();
                }
            });
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public View.OnClickListener onSendEventClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContractView() != null){
                    if(getContractView().isFromWithError())
                        getContractView().showFormErrors();
                    else
                        getContractView().showSuccess();
                }
            }
        };
    }

    @Override
    public View.OnClickListener onSendNewMessageClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContractView() != null){
                    getContractView().clearForm();
                    getContractView().hideSuccess();
                }
            }
        };
    }
    //endregion
}
