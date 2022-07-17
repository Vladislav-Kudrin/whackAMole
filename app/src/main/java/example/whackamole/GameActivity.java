package example.whackamole;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

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

        setField();
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
}
