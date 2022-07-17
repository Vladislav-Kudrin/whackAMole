package example.whackamole;

import android.app.Activity;
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
     * Player's high score.
     */
    private static int highScore;
    /**
     * The game field size.
     */
    private static final int FIELD_SIZE = 9;
    /**
     * The game field holes.
     */
    private static final ImageView[] HOLES = new ImageView[FIELD_SIZE];

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

        for (int index = 0; index < FIELD_SIZE; index++) {
            imageView = new ImageView(this);

            imageView.setImageDrawable(getDrawable(R.drawable.hole));
            imageView.setLayoutParams(layoutParams);
            field.addView(imageView);

            HOLES[index] = imageView;
        }
    }

    /**
     * Starts a counting down timer for 30 seconds.
     *
     * @author Vladislav
     * @since 1.0
     */
    private void startTimer() {
        new CountDownTimer(30000, 1000) {
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
             * Starts a game activity's finishing.
             *
             * @author Vladislav
             * @since 1.0
             */
            public void onFinish() {
                onBackPressed();
            }
        }.start();
    }
}
