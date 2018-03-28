package com.billspillstore.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class LaunchImage extends Activity {

    private static final long DELAY = 3000;
    private boolean scheduled = false;
    private Timer splashTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_image);



        splashTimer = new Timer();
        splashTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                LaunchImage.this.finish();
                startActivity(new Intent(LaunchImage.this, TransparentGuide.class));
            }
        }, DELAY);
        scheduled = true;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (scheduled)
            splashTimer.cancel();
        splashTimer.purge();
    }
}
