package lzacheu.com.br.santanderinvestimento.util;

import android.content.res.Resources;

/**
 * Created by luiszacheu on 6/17/18.
 */

public class AndroidUtils {

    public static int dpToPx(int px) {
        return (int) (px * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }


}
