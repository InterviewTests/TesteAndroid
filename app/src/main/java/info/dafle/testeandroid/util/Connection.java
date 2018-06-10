package info.dafle.testeandroid.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connection {

    public boolean checkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo inforRede = null;
        if (connectivityManager != null) {
            inforRede = connectivityManager.getActiveNetworkInfo();
        }
        return inforRede != null && inforRede.isConnected();
    }
}
