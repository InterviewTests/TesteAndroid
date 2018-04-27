package io.github.pierry.better_call_me;

import io.github.pierry.better_call_me.common.ContactHelper;
import io.github.pierry.better_call_me.domain.Cell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class) public class ContactTest {

  private ContactHelper contactHelper;

  @Before public void setUp() throws Exception {
    contactHelper = new ContactHelper();
  }

  @Test public void testCreateTextField() {
    String name = "John Doe";
    Cell cell = new Cell();
    cell.setId(1);
    cell.setType(2);
    cell.setMessage("Nome completo");
    cell.setTypefield(null);
    cell.setHidden(false);
    cell.setShow(null);
    cell.setTopSpacing(60);
    cell.setRequired(true);
    boolean isValid = contactHelper.validField(cell, name);
    Assert.assertTrue(isValid);
  }

  @Test public void testCreateEmailField() {
    String name = "email@email.com";
    Cell cell = new Cell();
    cell.setId(2);
    cell.setType(1);
    cell.setMessage("E-mail");
    cell.setTypefield(3);
    cell.setHidden(false);
    cell.setShow(null);
    cell.setTopSpacing(35.0);
    cell.setRequired(true);
    boolean isValid = contactHelper.validField(cell, name);
    Assert.assertTrue(isValid);
  }

  @Test public void testCreatePhoneField() {
    String name = "11 9 99999999";
    Cell cell = new Cell();
    cell.setId(6);
    cell.setType(1);
    cell.setMessage("Telefone");
    cell.setTypefield("telnumber");
    cell.setHidden(false);
    cell.setShow(null);
    cell.setTopSpacing(10.0);
    cell.setRequired(true);
    boolean isValid = contactHelper.validField(cell, name);
    Assert.assertTrue(isValid);
  }

  @Test public void testSendButton() {

  }

  @Test public void testRequired() {
    String name = "John Doe";
    Cell cell = new Cell();
    cell.setId(1);
    cell.setType(2);
    cell.setMessage("Nome completo");
    cell.setTypefield(null);
    cell.setHidden(false);
    cell.setTopSpacing(60);
    cell.setRequired(false);
    boolean required = contactHelper.required(cell, name);
    Assert.assertFalse(required);
  }
}
