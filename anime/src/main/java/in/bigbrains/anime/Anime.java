package in.bigbrains.anime;

import android.content.Context;

/**
 * @author Harry Singh.
 */
public class Anime {
    private static Context context;
    public static void initialize(Context context) {
        Anime.context = context;
    }

    public static Context getContext() {
        return context;
    }
}
