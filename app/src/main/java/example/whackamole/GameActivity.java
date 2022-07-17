package example.whackamole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
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
     * The game field view.
     */
    private GridLayout field;
    /**
     * A mole image view.
     */
    private ImageView mole;
    /**
     * Player's high score.
     */
    private static int highScore = 0;
    /**
     * Player's current score.
     */
    private static int moles = 0;
    /**
     * A counting down timer.
     */
    private static CountDownTimer timer;
    /**
     * A mole's counting down timer.
     */
    private static CountDownTimer moleTimer;

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

        field = findViewById(R.id.field);
        highScore = getIntent().getExtras().getInt("HIGH_SCORE");

        setField();
        setMole();
        startTimer();
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
     * Sets an image view and on click listener for {@code mole}.
     *
     * @author Vladislav
     * @since 1.0
     */
    private void setMole() {
        setMoleTimer();

        mole = new ImageView(this);

        mole.setImageDrawable(getDrawable(R.drawable.mole));
        mole.setOnClickListener(view -> {
            moveMole();

            moles++;

            ((TextView) findViewById(R.id.score)).setText(String.valueOf(moles));
        });
        moveMole();
    }

    /**
     * Removes {@code mole} from {@code field} cell, randomly sets {@code mole} onto another
     * {@code field} and restarts {@code moleTimer}.
     *
     * @author Vladislav
     * @since 1.0
     */
    private void moveMole() {
        field.removeView(mole);
        moleTimer.cancel();
        field.addView(mole, new GridLayout.LayoutParams(GridLayout.spec((int) (Math.random() * 3),
                GridLayout.CENTER), GridLayout.spec((int) (Math.random() * 3), GridLayout.CENTER)));
        moleTimer.start();
    }

    /**
     * Runs a result activity.
     *
     * @author Vladislav
     * @since 1.0
     */
    private void showResult() {
        final Intent resultActivity = new Intent(this, ResultActivity.class);

        resultActivity.putExtra("HIGH_SCORE", highScore);
        startActivity(resultActivity.putExtra("MOLES", moles));
    }

    /**
     * Sets a mole timer to move the mole across holes.
     *
     * @author Vladislav
     * @since 1.0
     */
    private void setMoleTimer() {
        moleTimer = new CountDownTimer(500, 500) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                moveMole();
            }
        };
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
