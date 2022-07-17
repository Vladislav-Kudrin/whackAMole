package example.whackamole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Runs the main menu application activity.
 *
 * @author Vladislav
 * @version 1.0
 * @since 1.0
 */
public final class MainActivity extends Activity {
    /**
     * Creates the main menu application activity.
     *
     * @param savedInstanceState saved user's data.
     *
     * @author Vladislav
     * @since 1.0
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Runs the game activity with no pending transitions.
     *
     * @param view a user interface component.
     *
     * @author Vladislav
     * @since 1.0
     */
    public final void onClickPlayButton(View view) {
        startActivity(new Intent(this, GameActivity.class));
        overridePendingTransition(0, 0);
    }
}
