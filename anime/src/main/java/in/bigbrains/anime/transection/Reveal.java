package in.bigbrains.anime.transection;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;

/**
 * @author Harry Singh.
 */
public class Reveal {
    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    public static class RevealActivity {
        private Activity activity;

        public RevealActivity(Activity activity) {
            this.activity = activity;
        }

        public void revelIt(View view, Intent intent) {
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(activity, view, "transition");
            int revealX = (int) (view.getX() + view.getWidth() / 2);
            int revealY = (int) (view.getY() + view.getHeight() / 2);

            intent.putExtra(EXTRA_CIRCULAR_REVEAL_X, revealX);
            intent.putExtra(EXTRA_CIRCULAR_REVEAL_Y, revealY);

            ActivityCompat.startActivity(activity, intent, options.toBundle());
        }

        public boolean canReveal() {
            return canReveal(null);
        }

        public boolean canReveal(Bundle bundle) {
            if (bundle == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                    activity.getIntent().hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                    activity.getIntent().hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
                return true;
            }
            return false;
        }

        private View getRootView() {
            View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            if (rootView == null) {
                rootView = activity.findViewById(android.R.id.content);
                Log.e("Reveal Activity", "Root View Null");
            }

            return rootView;
        }

        public View getActionBarView() {
            Window window = activity.getWindow();
            View v = window.getDecorView();
            int resId = activity.getResources().getIdentifier("action_bar_container", "id", "android");
            return v.findViewById(resId);
        }

        public void revealMe(Bundle savedInstanceState, Intent intent) {
            final View rootLayout = getRootView();
            if (canReveal(savedInstanceState)) {
                rootLayout.setVisibility(View.INVISIBLE);

                final int revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
                final int revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);


                ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            revealActivity(rootLayout, revealX, revealY);
                            rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                }
            } else {
                rootLayout.setVisibility(View.VISIBLE);
            }
        }

        public void revealActivity() {
            if (canReveal()) {
                View rootView = getRootView();
                revealActivity(rootView, activity.getIntent().getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0), activity.getIntent().getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0));
            }
        }

        public void unRevealActivity() {
            if (canReveal()) {
                View rootView = getRootView();
                unRevealActivity(rootView, activity.getIntent().getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0), activity.getIntent().getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0));
            }
        }

        public void revealActivity(View rootLayout, int x, int y) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

                // create the animator for this view (the start radius is zero)
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
                circularReveal.setDuration(400);
                circularReveal.setInterpolator(new AccelerateInterpolator());

                // make the view visible and start the animation
                rootLayout.setVisibility(View.VISIBLE);
                circularReveal.start();
            } else {
                activity.finish();
            }
        }

        public void unRevealActivity(final View rootLayout, int revealX, int revealY) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                activity.finish();
            } else {
                float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                        rootLayout, revealX, revealY, finalRadius, 0);

                circularReveal.setDuration(400);
                circularReveal.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        rootLayout.setVisibility(View.INVISIBLE);
                        activity.finish();
                        activity.overridePendingTransition(0, 0);
                    }
                });

                circularReveal.start();
            }
        }
    }

    public static class RevealFragment {
        private Activity activity;
        private Fragment fragment;

        public RevealFragment(Activity activity, Fragment fragment) {
            this.activity = activity;
            this.fragment = fragment;
        }

        public void revelIt(View view, Bundle bundle) {
            int revealX = (int) (view.getX() + view.getWidth() / 2);
            int revealY = (int) (view.getY() + view.getHeight() / 2);

            bundle.putInt(EXTRA_CIRCULAR_REVEAL_X, revealX);
            bundle.putInt(EXTRA_CIRCULAR_REVEAL_Y, revealY);
            fragment.setArguments(bundle);
            activity.getFragmentManager().beginTransaction().add(fragment, "fragment").addToBackStack("revealFragment").commit();
        }

        public boolean canReveal() {
            if (fragment.getArguments() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                    fragment.getArguments().containsKey(EXTRA_CIRCULAR_REVEAL_X) &&
                    fragment.getArguments().containsKey(EXTRA_CIRCULAR_REVEAL_Y)) {
                return true;
            }
            return false;
        }
    }

    public static class RevealFragmentV4 {
        private AppCompatActivity activity;
        private android.support.v4.app.Fragment fragment;

        public RevealFragmentV4(AppCompatActivity activity, android.support.v4.app.Fragment fragment) {
            this.activity = activity;
            this.fragment = fragment;
        }

        public void revelIt(View view, Bundle bundle) {
            int revealX = (int) (view.getX() + view.getWidth() / 2);
            int revealY = (int) (view.getY() + view.getHeight() / 2);

            bundle.putInt(EXTRA_CIRCULAR_REVEAL_X, revealX);
            bundle.putInt(EXTRA_CIRCULAR_REVEAL_Y, revealY);
            fragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().add(fragment, "fragment").addToBackStack("revealFragment").commit();
        }

        public boolean canReveal() {
            if (fragment.getArguments() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                    fragment.getArguments().containsKey(EXTRA_CIRCULAR_REVEAL_X) &&
                    fragment.getArguments().containsKey(EXTRA_CIRCULAR_REVEAL_Y)) {
                return true;
            }
            return false;
        }
    }
}
