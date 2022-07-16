package example.whackamole;

import android.app.Activity;
import android.os.Bundle;

/**
 * Runs the game activity.
 *
 * @author Vladislav
 * @version 1.0
 * @since 1.0
 */
public final class GameActivity extends Activity {
    /**
     * Creates the game activity.
     *
     * @param savedInstanceState saved user's data.
     *
     * @author Vladislav
     * @since 1.0
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    public final void onBackPressed() {
        finish();
        overridePendingTransition(0, 0);
    }
}
