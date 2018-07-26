package info.dafle.testeandroid.util;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectionTest {

    @Test
    public void testNetwork() {

        Connection connection = mock(Connection.class);
        Context context = mock(Context.class);
        when(connection.checkConnection(context)).thenReturn(true);
        Assert.assertTrue(connection.checkConnection(context));
    }
}
