package in.bigbrains.anime.transection;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

import in.bigbrains.anime.R;

/**
 * @author Harry Singh.
 */
public class Fade {
    public static class FadeActivity {
        private Activity activity;

        public FadeActivity(Activity activity) {
            this.activity = activity;
        }

        public void fadeIn(Intent intent) {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        public void fadeOut() {
            activity.finish();
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    public class FadeFragment {
        private Fragment fragment;

        public FadeFragment(Fragment fragment) {
            this.fragment = fragment;
        }
    }

    public class FadeFragmentV4{
        private android.support.v4.app.Fragment fragment;

        public FadeFragmentV4(android.support.v4.app.Fragment fragment) {
            this.fragment = fragment;
        }
    }
}
