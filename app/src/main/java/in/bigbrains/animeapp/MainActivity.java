package in.bigbrains.animeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import in.bigbrains.anime.transection.Fade;
import in.bigbrains.anime.transection.Zoom;

public class MainActivity extends AppCompatActivity {
    private Fade.FadeActivity fadeActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fadeActivity = new Fade.FadeActivity(this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                fadeActivity.fadeIn(intent);
            }
        });
    }
}
