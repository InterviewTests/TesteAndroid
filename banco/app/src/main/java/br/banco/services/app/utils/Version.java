package br.banco.services.app.utils;

import android.os.Build;

public final class Version {

    private final String osRelease =  Build.VERSION.RELEASE;
    private final int sdkVersion = Build.VERSION.SDK_INT;

    public String getOsRelease() {
        return osRelease;
    }

    public int getSdkVersion() {
        return sdkVersion;
    }
}
