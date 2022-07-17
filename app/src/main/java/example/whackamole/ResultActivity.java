package example.whackamole;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Runs a result activity.
 *
 * @author Vladislav
 * @version 1.0
 * @since 1.0
 */
public final class ResultActivity extends Activity {
    /**
     * Player's current score.
     */
    private static int moles;
    /**
     * Player's high score.
     */
    private static int highScore;

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

        moles = getIntent().getExtras().getInt("MOLES");
        highScore = getIntent().getExtras().getInt("HIGH_SCORE");
        ((TextView) findViewById(R.id.score)).setText(String.valueOf(moles));
    }

    /**
     * Finishes the game activity with no pending transitions.
     *
     * @author Vladislav
     * @since 1.0
     */
    @Override
    public final void onBackPressed() {
        finish();
    }

    public final void onClickPlayAgainButton(View view) {
        onBackPressed();
    }

    public final void onClickMenuButton(View view) {
        onBackPressed();
    }
}
