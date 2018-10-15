package br.com.itamarlourenco.santandertecnologia_testeandroid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Type;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.TypeField;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.CellServices;
import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

@RunWith(MockitoJUnitRunner.class)
public class CellTest {

    @Test
    public void validate(){
        Cell cell = mock(Cell.class, withSettings().verboseLogging());

        when(cell.getType()).thenReturn(Type.field);
        when(cell.getTypeField()).thenReturn(TypeField.email);
        when(cell.isRequired()).thenReturn(true);

        //Email no value
        assertFalse(cell.isValidData());

        //Email with email not valid
        when(cell.getValue()).thenReturn("invalidoemail");
        assertFalse(cell.isValidData());

        //Email success
        when(cell.getValue()).thenReturn("itamar@email.com.br");
        assertFalse(cell.isValidData());
    }

    @Test
    public void callCellServicesJson() throws Exception{
        CellServices cellServices = App.getRetrofitInstance().create(CellServices.class);
        Call<Cell.Cells> call = cellServices.getCellData();

        Response<Cell.Cells> execute = call.execute();

        assertTrue(execute.isSuccessful());
    }
}
