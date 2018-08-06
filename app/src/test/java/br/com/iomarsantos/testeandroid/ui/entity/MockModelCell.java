package br.com.iomarsantos.testeandroid.ui.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.iomarsantos.testeandroid.data.model.CellResponse;
import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.entity.Type;
import br.com.iomarsantos.testeandroid.entity.TypeField;

public class MockModelCell {

    public static CellResponse newCellResponse(int size) {
        CellResponse cellResponse = new CellResponse();
        cellResponse.setCells(newCellList(size));
        return cellResponse;
    }

    public static Cell newCellText() {
        Cell cell = new Cell();
        cell.setId(1);
        cell.setType(Type.TEXT);
        cell.setMessage("Olá, primeiro se apresente com o seu nome:");
        cell.setTypefield(null);
        cell.setHidden(false);
        cell.setTopSpacing(60.0F);
        cell.setShow(null);
        cell.setRequired(false);
        return cell;
    }


    public static Cell newCellTypeFieldText() {
        Cell cell = new Cell();
        cell.setId(2);
        cell.setType(Type.FIELD);
        cell.setMessage("Nome completo");
        cell.setTypefield(TypeField.TEXT);
        cell.setHidden(false);
        cell.setTopSpacing(35.0F);
        cell.setShow(null);
        cell.setRequired(true);
        return cell;
    }

    public static Cell newCellTypeFieldEmail() {
        Cell cell = new Cell();
        cell.setId(4);
        cell.setType(Type.FIELD);
        cell.setMessage("Email");
        cell.setTypefield(TypeField.EMAIL);
        cell.setHidden(true);
        cell.setTopSpacing(35.0F);
        cell.setShow(null);
        cell.setRequired(true);
        return cell;
    }

    public static Cell newCellTypeFieldPhone() {
        Cell cell = new Cell();
        cell.setId(6);
        cell.setType(Type.FIELD);
        cell.setMessage("Telefone");
        cell.setTypefield(TypeField.PHONE_NUMBER);
        cell.setHidden(false);
        cell.setTopSpacing(10.0F);
        cell.setShow(null);
        cell.setRequired(true);
        return cell;
    }

    public static Cell newCellCheckbox() {
        Cell cell = new Cell();
        cell.setId(3);
        cell.setType(Type.CHECKBOX);
        cell.setMessage("Gostaria de cadastrar meu email");
        cell.setTypefield(null);
        cell.setHidden(false);
        cell.setTopSpacing(35.0F);
        cell.setShow(4);
        cell.setRequired(false);
        return cell;
    }

    public static Cell newCellButton() {
        Cell cell = new Cell();
        cell.setId(7);
        cell.setType(Type.BUTTON);
        cell.setMessage("Enviar");
        cell.setTypefield(null);
        cell.setHidden(false);
        cell.setTopSpacing(10.0F);
        cell.setShow(null);
        cell.setRequired(true);
        return cell;
    }

    public static Cell newCellType(@Type Integer type) {
        Cell cell = new Cell();
        cell.setId(1);
        cell.setType(type);
        cell.setMessage("Olá, primeiro se apresente com o seu nome:");
        cell.setTypefield(null);
        cell.setHidden(false);
        cell.setTopSpacing(60.0F);
        cell.setShow(null);
        cell.setRequired(false);
        return cell;
    }

    public static Cell getCellTypeField(@TypeField String typeField) {
        Cell cell = null;
        if (typeField != null) {
            switch (typeField) {
                case TypeField.TEXT:
                    cell = newCellTypeFieldText();
                    break;
                case TypeField.PHONE_NUMBER:
                    cell = newCellTypeFieldPhone();
                    break;
                case TypeField.EMAIL:
                    cell = newCellTypeFieldEmail();
                    break;
            }
        }
        return cell;
    }

    public static List<Cell> newCellList(int size) {
        ArrayList<Cell> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(newCellText());
        }
        return list;
    }

}
