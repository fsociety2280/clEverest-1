package ru.myitschool.cleverest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


import java.util.HashMap;
import java.util.Map;

public class Intro extends AppCompatActivity {
    Button buttonPlayGame, buttonHowToPlay, buttonExit;
    public static final int SOUND_BUTTON = 0;
    private SoundPool soundPool;
    private Map<Integer, Integer> soundMap;
    MediaPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        fullHD();
        sizeScr();
        V.calculateCoefficientScreen();
        createButtons();
        setClickers();
        initilizeSound(this);


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

    private void fullHD() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    private void sizeScr() {
        V.scrWidth = getResources().getDisplayMetrics().widthPixels;
        V.scrHeight = getResources().getDisplayMetrics().heightPixels;
    }

    private void createButtons() {
        float x, y;
        int widthButton, heightButton;
        widthButton = (V.scrWidth > V.scrHeight ? V.scrWidth : V.scrHeight) / 3;
        heightButton = (int) (widthButton / V.KOEFF_BUTTON_INTRO);
        x = V.scrWidth / 2 - widthButton / 2;
        y = V.scrHeight / 2 - heightButton / 2;
        buttonPlayGame = new Button(this, x, y, widthButton, heightButton, "New Game");
        y = V.scrHeight / 2 - heightButton * 2;
        buttonHowToPlay = new Button(this, x, y, widthButton, heightButton, "How To Play");
        y = V.scrHeight / 2 + heightButton;
        buttonExit = new Button(this, x, y, widthButton, heightButton, "Exit");
    }

    private void setClickers() {
        buttonPlayGame.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonPlayGame.buttonDown();
                    soundPool.play(soundMap.get(SOUND_BUTTON), V.volume, V.volume, V.priority, V.loop, V.rate);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonPlayGame.buttonUp();
                    startActivity(new Intent(Intro.this, ActivityLevel.class));
                    try {
                        Thread.sleep(400);
                    } catch (Exception e) {
                    }
                    finish();
                }
                return true;
            }
        });

        buttonHowToPlay.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonHowToPlay.buttonDown();
                    soundPool.play(soundMap.get(SOUND_BUTTON), V.volume, V.volume, V.priority, V.loop, V.rate);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonHowToPlay.buttonUp();
                    startActivity(new Intent(Intro.this, HTP.class));
                    try {
                        Thread.sleep(400);
                    } catch (Exception e) {
                    }
                    finish();

                }
                return true;
            }
        });

        buttonExit.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonExit.buttonDown();
                    soundPool.play(soundMap.get(SOUND_BUTTON), V.volume, V.volume, V.priority, V.loop, V.rate);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonExit.buttonUp();
                    try {
                        Thread.sleep(400);
                    } catch (Exception e) {

                    }
                    System.exit(0);
                }
                return true;
            }
        });
    }

    private void initilizeSound(Context context) {
        int MAX_STREAMS = 4;
        int SOUND_QUALITY = 100;
        int priority = 1;
        soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, SOUND_QUALITY);
        soundMap = new HashMap<>();
        soundMap.put(SOUND_BUTTON, soundPool.load(context, R.raw.sound_button, priority));


    }

    private void startSound() {
        mPlayer = MediaPlayer.create(this, R.raw.intro_sound);
        mPlayer.start();
        mPlayer.setLooping(true);
    }


}
