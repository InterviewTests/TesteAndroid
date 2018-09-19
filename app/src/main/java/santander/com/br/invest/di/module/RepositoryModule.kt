package santander.com.br.invest.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import santander.com.br.invest.api.Api
import santander.com.br.invest.api.ContactApi
import santander.com.br.invest.api.InvestmentApi
import santander.com.br.invest.repository.ContactRepository
import santander.com.br.invest.repository.ContactRepositoryImpl
import santander.com.br.invest.repository.InvestmentRepository
import santander.com.br.invest.repository.InvestmentRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

  @Provides
  @Singleton
  fun providesInvestmentRepository(
      investmentApi: InvestmentApi): InvestmentRepository {
    return InvestmentRepositoryImpl(investmentApi)
  }

  @Provides
  @Singleton
  fun providesInvestmentApi(retrofit: Retrofit): InvestmentApi {
    return retrofit.create(InvestmentApi::class.java)
  }

  @Provides
  @Singleton
  fun providesContactRepository(
      contactApi: ContactApi): ContactRepository {
    return ContactRepositoryImpl(contactApi)
  }

  @Provides
  @Singleton
  fun providesContactApi(retrofit: Retrofit): ContactApi {
    return retrofit.create(ContactApi::class.java)
  }

  @Provides
  @Singleton
  fun providesRetroFit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }
}
