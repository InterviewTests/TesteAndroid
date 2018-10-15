package br.com.itamarlourenco.santandertecnologia_testeandroid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Funds;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.CellServices;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.FundServices;
import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FundTest {

    @Test
    public void callFoundServicesJson() throws Exception{
        FundServices fundServices = App.getRetrofitInstance().create(FundServices.class);
        Call<Funds.Screen> call = fundServices.getFundData();

        Response<Funds.Screen> execute = call.execute();

        assertTrue(execute.isSuccessful());
    }
}
