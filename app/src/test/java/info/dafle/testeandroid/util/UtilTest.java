package info.dafle.testeandroid.util;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class UtilTest {

    @Test
    public void verifyIfEmailIsValid() {
        Util util = Mockito.mock(Util.class);
        Mockito.when(util.isValidEmail("dafle@dafle.info")).thenReturn(true);
        Assert.assertTrue(util.isValidEmail("dafle@dafle.info"));
    }

    @Test
    public void verifyIfEmailIsNotValid() {
        Util util = Mockito.mock(Util.class);
        Mockito.when(util.isValidEmail("dafle@dafleinfo")).thenReturn(false);
        Assert.assertFalse(util.isValidEmail("dafle@dafleinfo"));
    }

    @Test
    public void verifyIfPhoneIsValid() {
        Util util = Mockito.mock(Util.class);
        Mockito.when(util.isValidPhoneNumber("11959952631")).thenReturn(true);
        Assert.assertTrue(util.isValidPhoneNumber("11959952631"));
    }

    @Test
    public void verifyIfPhoneIsNotValid() {
        Util util = Mockito.mock(Util.class);
        Mockito.when(util.isValidPhoneNumber("119599526")).thenReturn(false);
        Assert.assertFalse(util.isValidPhoneNumber("119599526"));
    }
}
