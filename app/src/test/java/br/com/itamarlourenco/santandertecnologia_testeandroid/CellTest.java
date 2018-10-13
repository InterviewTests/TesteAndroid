package br.com.itamarlourenco.santandertecnologia_testeandroid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.CellServices;
import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CellTest {

    @Test
    public void callCellServicesJson() throws Exception{
        CellServices cellServices = App.getRetrofitInstance().create(CellServices.class);
        Call<Cell.Cells> call = cellServices.getCellData();

        Response<Cell.Cells> execute = call.execute();

        assertTrue(execute.isSuccessful());
    }
}
