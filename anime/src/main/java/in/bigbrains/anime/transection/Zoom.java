package in.bigbrains.anime.transection;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

import in.bigbrains.anime.R;

/**
 * @author Harry Singh.
 */
public class Zoom {
    public static class ZoomActivity {
        private Activity activity;

        public ZoomActivity(Activity activity) {
            this.activity = activity;
        }

        public void zoomIn(Intent intent) {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.zoom_scale_in, 0);
        }
        public void zoomOut() {
            activity.finish();
            activity.overridePendingTransition(0, R.anim.zoom_in);
        }
    }

    public class ZoomFragment {
        private Fragment fragment;

        public ZoomFragment(Fragment fragment) {
            this.fragment = fragment;
        }
    }

    public class ZoomFragmentV4{
        private android.support.v4.app.Fragment fragment;

        public ZoomFragmentV4(android.support.v4.app.Fragment fragment) {
            this.fragment = fragment;
        }
    }
}
