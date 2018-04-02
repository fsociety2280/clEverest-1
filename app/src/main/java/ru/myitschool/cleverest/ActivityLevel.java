package ru.myitschool.cleverest;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class ActivityLevel extends AppCompatActivity {
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        fullHD();
    }

    private void fullHD() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startSound();
    }


    @Override
    public void onPause() {
        super.onPause();
        mPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }
    private void startSound() {
        mPlayer = MediaPlayer.create(this, R.raw.alevel_sound);
        mPlayer.start();
        mPlayer.setLooping(true);
    }
}