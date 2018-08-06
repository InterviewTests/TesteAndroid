package br.com.iomarsantos.testeandroid.ui.fundo.views.input;

import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.entity.TypeField;

public class InputType {

    @TypeField
    private String typeField;

    @TypeField
    private String getTypeField() {
        return typeField;
    }

    private void setTypeField(@TypeField String typeField) {
        this.typeField = typeField;
    }

    public ClassInputType get(Cell cell) {
        setTypeField(cell.getTypefield());
        return getView(getTypeField());
    }

    private ClassInputType getView(@TypeField String typeField) {
        ClassInputType classInputType = null;
        if (typeField != null) {
            switch (typeField) {
                case TypeField.TEXT:
                    classInputType = new TypeClassText();
                    break;
                case TypeField.PHONE_NUMBER:
                    classInputType = new TypeClassPhone();
                    break;
                case TypeField.EMAIL:
                    classInputType = new TypeClassEmail();
                    break;
            }
        }
        return classInputType;
    }

}