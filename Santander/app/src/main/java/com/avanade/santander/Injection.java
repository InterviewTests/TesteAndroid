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

import com.avanade.santander.fundos.data.FundosRemoteDataSource;
import com.avanade.santander.fundos.data.FundosRepository;
import com.avanade.santander.fundos.domain.usecase.GetFundos;

/**
 * Enables injection of mock implementations for
 */ //{@link fundosDataSource}
/** at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {
//
//    public static FundosRepository provideFundosRepository(@NonNull Context context) {
//        checkNotNull(context);
//        SantanderDatabase database = SantanderDatabase.getInstance(context);
//        return FundosRepository.getInstance(FakeFundosRemoteDataSource.getInstance(),
//                FundosLocalDataSource.getInstance(new AppExecutors(),
//                        database.fundoDao()));
//    }

    public static FundosRepository provideFundosRepository(
            // Context aqui era para LocalDataSource em SQLiteOpenHelper
            // @NonNull Context context
    ) {
        return FundosRepository.getInstance(FundosRemoteDataSource.getInstance());
    }

    public static GetFundos provideGetFundos() {
        return new GetFundos(provideFundosRepository());
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }
}
