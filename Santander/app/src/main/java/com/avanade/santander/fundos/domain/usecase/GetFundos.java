package com.avanade.santander.fundos.domain.usecase;

import android.support.annotation.NonNull;

import com.avanade.santander.UseCase;
import com.avanade.santander.fundos.data.FundosRepository;
import com.avanade.santander.fundos.data.FundosDataSource;
import com.avanade.santander.fundos.domain.model.Fundos;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Camada de Domínio - Caso de Uso
 *
 * Solicita à interface Repositorio
 */
public class GetFundos extends UseCase<GetFundos.RequestValues, GetFundos.ResponseValue> {

    private final FundosRepository mFundosRepository;

    public GetFundos(@NonNull FundosRepository fundosRepository) {
        mFundosRepository = checkNotNull(fundosRepository, "mFundosRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {

        mFundosRepository
                .getFundos(
                        new FundosDataSource.LoadFundosCallback() {
                            @Override
                            public void onFundosLoaded(Fundos fundos) {
                                ResponseValue responseValue = new ResponseValue(fundos);
                                getUseCaseCallback().onSuccess(responseValue);
                            }

                            @Override
                            public void onDataNotAvailable() {
                                getUseCaseCallback().onError();
                            }
                        });

    }

    public static final class RequestValues implements UseCase.RequestValues {
        // Passar parametros para request da API
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final Fundos mFundos;

        public ResponseValue(@NonNull Fundos fundos) {
            mFundos = checkNotNull(fundos, "fundos cannot be null!");
        }

        public Fundos getFundos() {
            return mFundos;
        }
    }
}
