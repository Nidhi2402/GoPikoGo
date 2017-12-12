package com.example.nidhi.gopikago;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.speech.tts.TextToSpeech;

/**
 * Created by SAMPC on 7/27/2016.
 */
public class Assets {
    static Bitmap background;
    static Bitmap egg1;
    static Bitmap egg2;
    static Bitmap eggleft;
    static Bitmap eggright;
    static Bitmap eggbreak;
    static Bitmap eggbroken;
    static Bitmap walk1;
    static Bitmap walk2;
    static Bitmap eating1;
    static Bitmap eating2;
    static Bitmap food;
    static Bitmap settings;
    static Bitmap hungrymsg;
    static Bitmap happymsg;
    static Bitmap fullmsg;

    static MediaPlayer mp;

    enum EggState {
        eggborn,
        eggbreak,
        duckborn, 			    // in the game
        hungry,
        eating,
        happy,// draw dead body on screen
    };
    static EggState state = EggState.eggborn;

    static boolean on_screen= true;
    static boolean runningIntent;
    static boolean prefs;
    static boolean eatingTime=false;
    static boolean happyTime=false;
    static boolean hungryTime=false;
    static boolean reached=true;
    static boolean foodonscreen=false;
    static boolean fullTime=false;
    static boolean notification=false;
    static boolean tts_initialized;
    //static boolean fullstomach=false;
    static float gameTimer;
    static float msg=0;
    static float foodhight;
    static SoundPool soundPool;
    static float foodwidth;
    static float settingwidth;
    static int walk;
    static int feeding=0;
    static int newTime;
    static int eggcrack;
    static int happy;
    static int hungry;
    static int sound_happy;
    static int sound_hungry;
    static int sound_eggcrack;

    static TextToSpeech tts;


    static int restart=0;


}

