/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.avanade.santander;

import android.content.Context;
import android.support.annotation.NonNull;

import com.avanade.santander.contato.domain.usecase.GetFormulario;
import com.avanade.santander.data.local.CellsLocalDataSource;
import com.avanade.santander.data.local.SantanderDatabase;
import com.avanade.santander.data.remote.CellsRemoteDataSource;
import com.avanade.santander.data.CellsRepository;
import com.avanade.santander.data.remote.FundosRemoteDataSource;
import com.avanade.santander.data.FundosRepository;
import com.avanade.santander.fundos.domain.usecase.GetFundos;
import com.avanade.santander.util.AppExecutors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of mock implementations for
 */ //{@link fundosDataSource}

/**
 * at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static FundosRepository provideFundosRepository(/* @NonNull Context context */) {
        // Context aqui era para LocalDataSource em SQLiteOpenHelper
        return FundosRepository.getInstance(FundosRemoteDataSource.getInstance());
    }

    public static GetFundos provideGetFundos() {
        return new GetFundos(provideFundosRepository());
    }


    public static CellsRepository provideCellsRepository(@NonNull Context context) {
        checkNotNull(context);
        SantanderDatabase database = SantanderDatabase.getInstance(context);
        return CellsRepository.getInstance(
                CellsRemoteDataSource.getInstance(),
                CellsLocalDataSource.getInstance(new AppExecutors(), database.contatoDao())
        );
    }

    public static GetFormulario provideGetCells(@NonNull Context context) {
        return new GetFormulario(provideCellsRepository(context));
    }


}
