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
final class PreferencesHandler {
    /**
     * An application shared preferences' editor.
     */
    private static SharedPreferences.Editor editor;

    /**
     * An application shared preferences.
     */
    private static SharedPreferences preferences;

    /**
     * Creates a new shared preference handler with an instantiator activity's context.
     *
     * @param context the instantiator activity's context.
     *
     * @author Vladislav
     * @since 1.0
     */
    public PreferencesHandler(Context context) {
        preferences = context.getSharedPreferences(KeysStorage.APP_PREFERENCES,
                Context.MODE_PRIVATE);
        editor = preferences.edit();

        editor.apply();
    }

    /**
     * Sets or updates a value of a high score's preference key.
     *
     * @param highScore the value of the high score's preference key.
     *
     * @author Vladislav
     * @since 1.0
     */
    public final void setHighScore(int highScore) {
        editor.putInt(KeysStorage.HIGH_SCORE, highScore);
        editor.apply();
    }

    /**
     * Gets a value of a high score's preference key if the key exists. If the key doesn't exist
     * returns 0.
     *
     * @return the value of the high score's preference key.
     */
    public final int getHighScore() {
        return preferences.getInt(KeysStorage.HIGH_SCORE, 0);
    }

    /**
     * Sets or updates a value of a replay status preference key.
     *
     * @param isReplay the value of the replay status preference key.
     *
     * @author Vladislav
     * @since 1.0
     */
    public final void setReplay(boolean isReplay) {
        editor.putBoolean(KeysStorage.REPLAY, isReplay);
        editor.apply();
    }

    /**
     * Gets a value of a replay status preference key if the key exists. If the key doesn't exist
     * returns true.
     *
     * @return the value of the replay status preference key.
     */
    public final boolean isReplay() {
        return preferences.getBoolean(KeysStorage.REPLAY, true);
    }
}
