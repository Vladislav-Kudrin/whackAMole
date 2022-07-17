package example.whackamole;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Handles shared preference methods.
 *
 * @author Vladislav
 * @version 1.0
 * @since 1.0
 */
final class PreferenceHandler {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    /**
     * Creates a new shared preference handler with an instantiator activity context.
     *
     * @param context the instantiator activity context.
     *
     * @author Vladislav
     * @since 1.0
     */
    public PreferenceHandler(Context context) {
        preferences = context.getSharedPreferences(KeysStorage.APP_PREFERENCES,
                Context.MODE_PRIVATE);
        editor = preferences.edit();

        editor.apply();
    }

    /**
     * Sets or updates a value of a high score preference key.
     *
     * @param highScore the value of the high score preference key.
     *
     * @author Vladislav
     * @since 1.0
     */
    public final void setHighScore(int highScore) {
        editor.putInt(KeysStorage.HIGH_SCORE, highScore);
        editor.apply();
    }

    /**
     * Gets a a value of a high score preference key if the key exists. If the key doesn't exist
     * returns 0.
     *
     * @return the value of the high score preference key.
     */
    public final int getHighScore() {
        return preferences.getInt(KeysStorage.HIGH_SCORE, 0);
    }
}
