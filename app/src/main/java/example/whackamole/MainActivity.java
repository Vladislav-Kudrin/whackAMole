package example.whackamole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Runs the main menu application activity.
 *
 * @author Vladislav
 * @version 1.0
 * @since 1.0
 */
public final class MainActivity extends Activity {
    /**
     * A player's high score.
     */
    private static int highScore = 0;
    /**
     * A player's high score text view.
     */
    private TextView score;
    /**
     * A shared preferences' handler instance.
     */
    private static PreferenceHandler preferenceHandler;

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

        score = findViewById(R.id.score);
        preferenceHandler = new PreferenceHandler(this);
        highScore = preferenceHandler.getHighScore();

        score.setText(String.valueOf(highScore));
    }

    @Override
    public final void onResume() {
        highScore = preferenceHandler.getHighScore();

        score.setText(String.valueOf(highScore));
        super.onResume();
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
        final Intent gameActivity = new Intent(this, GameActivity.class);
        startActivity(gameActivity.putExtra(KeysStorage.HIGH_SCORE, highScore));
        overridePendingTransition(0, 0);
    }
}
