package in.bigbrains.animeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import in.bigbrains.anime.transection.Fade;
import in.bigbrains.anime.transection.Zoom;

public class Main2Activity extends AppCompatActivity {
    private Fade.FadeActivity revealActivity;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = findViewById(R.id.image);
        Glide.with(getApplicationContext()).load("http://www.mscareergirl.com/wp-content/uploads/2017/05/fork-in-road-road-less-traveled-pexels.jpeg").into(imageView);

        revealActivity = new Fade.FadeActivity(this);
    }

    @Override
    public void onBackPressed() {
        revealActivity.fadeOut();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
