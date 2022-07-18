package example.whackamole.entities;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import example.whackamole.R;

/**
 * Handles a mole entity's methods.
 *
 * @author Vladislav
 * @version 1.0
 * @since 1.0
 */
public final class Mole {
    /**
     * The game activity's context.
     */
    private Context context;
    /**
     * A mole's counting down timer.
     */
    private CountDownTimer moleTimer;
    /**
     * The game field grid layout.
     */
    private GridLayout field;
    /**
     * A mole image view.
     */
    private ImageView mole;
    /**
     * A current player's score text view.
     */
    private TextView score;
    /**
     * Player's current score.
     */
    private static int hits = 0;

    /**
     * Creates a new mole entity.
     *
     * @param context the game activity's context.
     * @param field the game field grid layout.
     * @param score a current player's score text view.
     *
     * @author Vladislav
     * @since 1.0
     */
    public Mole(Context context, GridLayout field, TextView score) {
        this.context = context;
        this.field = field;
        this.score = score;
    }

    /**
     * Sets a mole timer to move the mole across holes.
     *
     * @author Vladislav
     * @since 1.0
     */
    private void setMoleTimer() {
        moleTimer = new CountDownTimer(500, 500) {
            /**
             * Performs some actions on a regular interval.
             *
             * @param millisUntilFinished a current remaining time.
             *
             * @author Vladislav
             * @since 1.0
             */
            @Override
            public void onTick(long millisUntilFinished) {}

            /**
             * Moves a mole randomly across the game field holes.
             *
             * @author Vladislav
             * @since 1.0
             */
            @Override
            public void onFinish() {
                moveMole();
            }
        };
    }

    /**
     * Removes {@code mole} from {@code field} cell, randomly sets {@code mole} onto another
     * {@code field} and restarts {@code moleTimer}.
     *
     * @author Vladislav
     * @since 1.0
     */
    public final void moveMole() {
        field.removeView(mole);
        moleTimer.cancel();
        field.addView(mole, new GridLayout.LayoutParams(GridLayout.spec((int) (Math.random() * 3),
                GridLayout.CENTER), GridLayout.spec((int) (Math.random() * 3), GridLayout.CENTER)));
        moleTimer.start();
    }

    /**
     * Sets an image view and on click listener for {@code mole}.
     *
     * @author Vladislav
     * @since 1.0
     */
    public final void setMole() {
        setMoleTimer();

        mole = new ImageView(context);

        mole.setImageDrawable(context.getDrawable(R.drawable.mole));
        mole.setOnClickListener(view -> {
            moveMole();

            hits++;

            score.setText(String.valueOf(hits));
        });
        moveMole();
    }

    /**
     * Removes {@code mole} from {@code field} and stops {@code moleTimer}.
     *
     * @author Vladislav
     * @since 1.0
     */
    public final void stopMole() {
        field.removeView(mole);
        moleTimer.cancel();
    }

    /**
     * Resets {@code hits} and {@code score} values to 0.
     */
    public final void resetScore() {
        hits = 0;

        score.setText(String.valueOf(hits));
    }

    /**
     * Returns a current player's score.
     *
     * @return the current player's score.
     */
    public final int getHits() {
        return hits;
    }
}
