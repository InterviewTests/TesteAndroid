package android.teste.com.br.testeandroidapp;

import android.teste.com.br.testeandroidapp.entity.Cell;
import android.teste.com.br.testeandroidapp.entity.Screen;
import android.teste.com.br.testeandroidapp.entity.TypeField;
import android.teste.com.br.testeandroidapp.utils.JsonParser;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Teste do parse de json
 * @author Gabriela Santos
 */
public class JsonParserTest {
    @Test
    public void parseListCell() throws Exception {
        String json = "{\n" +
                "  \"cells\": [\n" +
                "    \t{\n" +
                "\t\t\t\"id\": 1,\n" +
                "\t\t\t\"type\": 2,\n" +
                "\t\t\t\"message\": \"Olá, primeiro se apresente com o seu nome:\",\n" +
                "\t\t\t\"typefield\": null,\n" +
                "\t\t\t\"hidden\": false,\n" +
                "\t\t\t\"topSpacing\": 60.0,\n" +
                "\t\t\t\"show\": null,\n" +
                "\t\t\t\"required\": false\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 2,\n" +
                "\t\t\t\"type\": 1,\n" +
                "\t\t\t\"message\": \"Nome completo\",\n" +
                "\t\t\t\"typefield\": 1,\n" +
                "\t\t\t\"hidden\": false,\n" +
                "\t\t\t\"topSpacing\": 35.0,\n" +
                "\t\t\t\"show\": null,\n" +
                "\t\t\t\"required\": true\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 4,\n" +
                "\t\t\t\"type\": 1,\n" +
                "\t\t\t\"message\": \"Email\",\n" +
                "\t\t\t\"typefield\": 3,\n" +
                "\t\t\t\"hidden\": true,\n" +
                "\t\t\t\"topSpacing\": 35.0,\n" +
                "\t\t\t\"show\": null,\n" +
                "\t\t\t\"required\": true\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 6,\n" +
                "\t\t\t\"type\": 1,\n" +
                "\t\t\t\"message\": \"Telefone\",\n" +
                "\t\t\t\"typefield\": \"telnumber\",\n" +
                "\t\t\t\"hidden\": false,\n" +
                "\t\t\t\"topSpacing\": 10.0,\n" +
                "\t\t\t\"show\": null,\n" +
                "\t\t\t\"required\": true\n" +
                "\t\t},\n" +
                "    {\n" +
                "\t\t\t\"id\": 3,\n" +
                "\t\t\t\"type\": 4,\n" +
                "\t\t\t\"message\": \"Gostaria de cadastrar meu email\",\n" +
                "\t\t\t\"typefield\": null,\n" +
                "\t\t\t\"hidden\": false,\n" +
                "\t\t\t\"topSpacing\": 35.0,\n" +
                "\t\t\t\"show\": 4,\n" +
                "\t\t\t\"required\": false\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 7,\n" +
                "\t\t\t\"type\": 5,\n" +
                "\t\t\t\"message\": \"Enviar\",\n" +
                "\t\t\t\"typefield\": null,\n" +
                "\t\t\t\"hidden\": false,\n" +
                "\t\t\t\"topSpacing\": 10.0,\n" +
                "\t\t\t\"show\": null,\n" +
                "\t\t\t\"required\": true\n" +
                "\t\t}\n" +
                "    ]\n" +
                "}";

        List<Cell> cells = JsonParser.toListCell(json);

        Assert.assertNotNull(cells);
        Assert.assertTrue(cells.size() == 6);

        Cell cell = cells.get(1);
        Assert.assertTrue(cell.getId() == 2);
        Assert.assertEquals(cell.getMessage(),"Nome completo");
        Assert.assertTrue(cell.getTypefield() == TypeField.TEXT);
        Assert.assertFalse(cell.getHidden());
        Assert.assertTrue(cell.getTopSpacing() == 35);
        Assert.assertTrue(cell.getShow() == null);
        Assert.assertTrue(cell.getRequired());
    }

    @Test
    public void parseScreen() throws Exception {
        String json = "{\n" +
                "  \"screen\": {\n" +
                "  \t\t\"title\": \"Fundos de investimento\",\n" +
                "  \t\t\"fundName\": \"Vinci Valorem FI Multimercado\",\n" +
                "  \t\t\"whatIs\": \"O que é?\",\n" +
                "  \t\t\"definition\": \"O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo através de investimentos.\",\n" +
                "  \t\t\"riskTitle\": \"Grau de risco do investimento\",\n" +
                "  \t\t\"risk\": 4,\n" +
                "  \t\t\"infoTitle\": \"Mais informações sobre o investimento\",\n" +
                "  \t\t\"moreInfo\": {\n" +
                "  \t\t\t\"month\": {\n" +
                "  \t\t\t\t\"fund\": 0.3,\n" +
                "  \t\t\t\t\"CDI\": 0.3\n" +
                "  \t\t\t},\n" +
                "  \t\t\t\"year\": {\n" +
                "  \t\t\t\t\"fund\": 13.01,\n" +
                "  \t\t\t\t\"CDI\": 12.08\n" +
                "  \t\t\t},\n" +
                "  \t\t\t\"12months\": {\n" +
                "  \t\t\t\t\"fund\": 17.9,\n" +
                "  \t\t\t\t\"CDI\": 17.6\n" +
                "  \t\t\t}\n" +
                "  \t\t},\n" +
                "  \t\t\"info\": [\n" +
                "  \t\t\t{\n" +
                "  \t\t  \t\t\"name\": \"Taxa de administração\",\n" +
                "  \t\t  \t\t\"data\": \"0,50%\"\n" +
                "  \t\t  \t},\n" +
                "  \t\t  \t{\n" +
                "  \t\t  \t\t\"name\": \"Aplicação inicial\",\n" +
                "  \t\t  \t\t\"data\": \"R$ 10.000,00\"\n" +
                "  \t\t  \t},\n" +
                "  \t\t  \t{\n" +
                "  \t\t  \t\t\"name\": \"Movimentação mínima\",\n" +
                "  \t\t  \t\t\"data\": \"R$ 1.000,00\"\n" +
                "  \t\t  \t},\n" +
                "  \t\t  \t{\n" +
                "  \t\t  \t\t\"name\": \"Saldo mínimo\",\n" +
                "  \t\t  \t\t\"data\": \"R$ 5.000,00\"\n" +
                "  \t\t  \t},\n" +
                "  \t\t  \t{\n" +
                "  \t\t  \t\t\"name\": \"Resgate (valor bruto)\",\n" +
                "  \t\t  \t\t\"data\": \"D+0\"\n" +
                "  \t\t  \t},\n" +
                "  \t\t  \t{\n" +
                "  \t\t  \t\t\"name\": \"Cota (valor bruto)\",\n" +
                "  \t\t  \t\t\"data\": \"D+1\"\n" +
                "  \t\t  \t},\n" +
                "  \t\t  \t{\n" +
                "  \t\t  \t\t\"name\": \"Pagamento (valor bruto)\",\n" +
                "  \t\t  \t\t\"data\": \"D+2\"\n" +
                "  \t\t  \t}\n" +
                "  \t\t],\n" +
                "  \t\t\"downInfo\": [\n" +
                "  \t\t\t{\n" +
                "  \t\t\t\t\"name\": \"Essenciais\",\n" +
                "  \t\t\t\t\"data\": null\n" +
                "  \t\t\t},\n" +
                "  \t\t\t{\n" +
                "  \t\t\t\t\"name\": \"Desempenho\",\n" +
                "  \t\t\t\t\"data\": null\n" +
                "  \t\t\t},\n" +
                "  \t\t\t{\n" +
                "  \t\t\t\t\"name\": \"Complementares\",\n" +
                "  \t\t\t\t\"data\": null\n" +
                "  \t\t\t},\n" +
                "  \t\t\t{\n" +
                "  \t\t\t\t\"name\": \"Regulamento\",\n" +
                "  \t\t\t\t\"data\": null\n" +
                "  \t\t\t},\n" +
                "  \t\t\t{\n" +
                "  \t\t\t\t\"name\": \"Adesão\",\n" +
                "  \t\t\t\t\"data\": null\n" +
                "  \t\t\t}\n" +
                "  \t\t]\n" +
                "\n" +
                "    }\n" +
                "}";

        Screen screen = JsonParser.toScreen(json);

        Assert.assertNotNull(screen);

        Assert.assertEquals(screen.getTitle(), "Fundos de investimento");
        Assert.assertEquals(screen.getFundName(), "Vinci Valorem FI Multimercado");
        Assert.assertEquals(screen.getDefinition(), "O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo através de investimentos.");
        Assert.assertEquals(screen.getRiskTitle(), "Grau de risco do investimento");
        Assert.assertTrue(screen.getRisk() == 4);
        Assert.assertEquals(screen.getInfoTitle(), "Mais informações sobre o investimento");
        Assert.assertNotNull(screen.getMoreInfo());
        Assert.assertNotNull(screen.getInfo());
        Assert.assertNotNull(screen.getDownInfo());
    }

}