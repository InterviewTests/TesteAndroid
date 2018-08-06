package br.com.iomarsantos.testeandroid.ui.entity;

import org.junit.Test;

import br.com.iomarsantos.testeandroid.entity.Type;
import br.com.iomarsantos.testeandroid.entity.TypeField;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CellTest {

    @Test
    public void Should_Return_Type_When_Receiving_Text_Type() {
        Integer typeReturned = MockModelCell.newCellText().getType();
        assertThat(typeReturned, is(equalTo(Type.TEXT)));
    }

    @Test
    public void Should_Return_Type_When_Receiving_Button_Type() {
        Integer typeReturned = MockModelCell.newCellType(Type.BUTTON).getType();
        assertThat(typeReturned, is(equalTo(Type.BUTTON)));
    }

    @Test
    public void Should_Return_Type_When_Receiving_Field_Type() {
        Integer typeReturned = MockModelCell.newCellType(Type.FIELD).getType();
        assertThat(typeReturned, is(equalTo(Type.FIELD)));
    }

    @Test
    public void Should_Return_Type_When_Receiving_CheckBox_Type() {
        Integer typeReturned = MockModelCell.newCellType(Type.CHECKBOX).getType();
        assertThat(typeReturned, is(equalTo(Type.CHECKBOX)));
    }

    @Test
    public void Should_Return_Type_When_Receiving_Image_Type() {
        Integer typeReturned = MockModelCell.newCellType(Type.IMAGE).getType();
        assertThat(typeReturned, is(equalTo(Type.IMAGE)));
    }

    @Test
    public void Should_Return_Message_When_Received_Cell_Type_Text_Message() {
        String returnedMessage = MockModelCell.newCellText().getMessage();
        assertThat(returnedMessage, is(equalTo("Olá, primeiro se apresente com o seu nome:")));
    }

    @Test
    public void Should_Return_Message_When_Received_Cell_Type_Field_Text_Message() {
        String returnedMessage = MockModelCell.getCellTypeField(TypeField.TEXT).getMessage();
        assertThat(returnedMessage, is(equalTo("Nome completo")));
    }

    @Test
    public void Should_Return_Message_When_Received_Cell_Type_Field_Email_Message() {
        String returnedMessage = MockModelCell.getCellTypeField(TypeField.EMAIL).getMessage();
        assertThat(returnedMessage, is(equalTo("Email")));
    }

    @Test
    public void Should_Return_Message_When_Received_Cell_Type_Field_Phone_Message() {
        String returnedMessage = MockModelCell.getCellTypeField(TypeField.PHONE_NUMBER).getMessage();
        assertThat(returnedMessage, is(equalTo("Telefone")));
    }


}
