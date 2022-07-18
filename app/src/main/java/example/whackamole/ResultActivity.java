package example.whackamole;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

/**
 * Runs a result activity.
 *
 * @author Vladislav
 * @version 1.0
 * @since 1.0
 */
public final class ResultActivity extends Activity {
    /**
     * A shared preferences' handler instance.
     */
    private PreferencesHandler preferencesHandler;

    /**
     * Creates a result activity.
     *
     * @param savedInstanceState saved user's data.
     *
     * @author Vladislav
     * @since 1.0
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int highScore;
        final int moles = getIntent().getExtras().getInt(KeysStorage.MOLES);

        preferencesHandler = new PreferencesHandler(this);
        highScore = preferencesHandler.getHighScore();

        if (highScore > moles) {
            ((TextView) findViewById(R.id.score)).setText(getString(R.string.moles)
                    .concat(String.valueOf(moles)));
            ((TextView) findViewById(R.id.highScore)).setText(getString(R.string.high_score)
                    .concat(String.valueOf(highScore)));
        }
        else {
            highScore = moles;

            preferencesHandler.setHighScore(highScore);
            ((TextView) findViewById(R.id.score)).setText(String.format(Locale.getDefault(),
                    "New High Score: %d", highScore));
        }
    }

    /**
     * Finishes the game activity with no pending transitions.
     *
     * @author Vladislav
     * @since 1.0
     */
    @Override
    public final void onBackPressed() {
        onClickMenuButton(this.getCurrentFocus());
    }

    /**
     * Destroys current activity and returns to the game activity.
     *
     * @param view a user's interface component.
     */
    public final void onClickPlayAgainButton(View view) {
        finish();
    }

    /**
     * Destroys current activity and returns to the main activity.
     *
     * @param view a user's interface component.
     */
    public final void onClickMenuButton(View view) {
        preferencesHandler.setReplay(false);
        finish();
    }
}
