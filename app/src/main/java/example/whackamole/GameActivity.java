package example.whackamole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import example.whackamole.entities.Mole;
import java.util.Locale;

/**
 * Runs the game activity.
 *
 * @author Vladislav
 * @version 1.0
 * @since 1.0
 */
public final class GameActivity extends Activity {
    /**
     * A counting down timer.
     */
    private CountDownTimer timer;
    /**
     * The game field grid layout.
     */
    private GridLayout field;
    /**
     * A mole entity instance.
     */
    private Mole mole;
    /**
     * A shared preferences' handler instance.
     */
    private PreferencesHandler preferencesHandler;
    /**
     * A current player's score text view.
     */
    private TextView score;
    /**
     * The game activity's a resumed flag.
     */
    private boolean isResumed = false;

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

        preferencesHandler = new PreferencesHandler(this);
        score = findViewById(R.id.score);
        field = findViewById(R.id.field);
        mole = new Mole(this, field, score);

        setField();
        mole.setMole();
        startTimer();
    }

    /**
     * Resets a current player's score and restarts {@code mole} if a replay status is true,
     * destroys the game activity otherwise.
     *
     * @author Vladislav
     * @since 1.0
     */
    @Override
    public final void onResume() {
        if (isResumed) {
            Mole.resetScore();
            score.setText(getString(R.string.initial_score));

            if (preferencesHandler.isReplay()) {
                mole.moveMole();
                timer.start();
            } else {
                preferencesHandler.setReplay(true);
                onBackPressed();
            }
        } else
            isResumed = true;

        super.onResume();
    }

    /**
     * Finishes the game activity with no pending transitions.
     *
     * @author Vladislav
     * @since 1.0
     */
    @Override
    public final void onBackPressed() {
        timer.cancel();
        finish();
        overridePendingTransition(0, 0);
    }

    /**
     * Sets the game field with holes.
     *
     * @author Vladislav
     * @since 1.0
     */
    private void setField() {
        ImageView imageView;
        final FrameLayout.LayoutParams layoutParams = new FrameLayout
                .LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(18, 18, 18, 18);

        for (int index = 0; index < 9; index++) {
            imageView = new ImageView(this);

            imageView.setImageDrawable(getDrawable(R.drawable.hole));
            imageView.setLayoutParams(layoutParams);
            field.addView(imageView);
        }
    }

    /**
     * Runs a result activity.
     *
     * @author Vladislav
     * @since 1.0
     */
    private void showResult() {
        final Intent resultActivity = new Intent(this, ResultActivity.class);

        startActivity(resultActivity.putExtra(KeysStorage.MOLES, Mole.getHits()));
        mole.stopMole();
    }

    /**
     * Starts a counting down timer for 30 seconds.
     *
     * @author Vladislav
     * @since 1.0
     */
    private void startTimer() {
        timer = new CountDownTimer(30000, 1000) {
            /**
             * A remaining time text view.
             */
            private final TextView time = findViewById(R.id.time);

            /**
             * Updates a current remaining time every second.
             *
             * @param millisUntilFinished the current remaining time.
             *
             * @author Vladislav
             * @since 1.0
             */
            public void onTick(long millisUntilFinished) {
                final int second = 1000;

                time.setText(String.format(Locale.getDefault(),
                        "%ds", millisUntilFinished / second));
            }

            /**
             * Starts a result activity's running.
             *
             * @author Vladislav
             * @since 1.0
             */
            public void onFinish() {
                time.setText(getResources().getString(R.string.timer_over));
                showResult();
            }
        }.start();
    }
}
