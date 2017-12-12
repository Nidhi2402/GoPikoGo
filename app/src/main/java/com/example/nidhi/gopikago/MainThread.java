package com.example.nidhi.gopikago;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import java.util.Locale;

public class MainThread extends AppCompatActivity {

    Context context;
    private ImageView imageView;
    float savetimer1=0;
    float savetimer2=0;
    float savetimer3=0;
    float savetimer4=0;

    int touchx, touchy;	// x,y of touch event
    boolean touched;
    private static final Object lock = new Object();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Assets.gameTimer =System.nanoTime()/1000000000f;
        super.onCreate(savedInstanceState);
        imageView = (ImageView) findViewById(R.id.imageView);
        setContentView(new MyView(this));
        Assets.mp = MediaPlayer.create(this, R.raw.backgroundmusic);
        Assets.mp.start();
        Assets.mp.setLooping(true);
        Assets.runningIntent=true;

        Assets.tts_initialized = false;
        Assets.tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // Set the language to use
                    Assets.tts.setLanguage(Locale.UK);
                    Assets.tts_initialized = true;
                }
            }
        });


    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();

        //android.os.Process.killProcess(android.os.Process.myPid());
        Assets.on_screen = false;
        Assets.notification=true;

    }

    @Override
    protected void onPause(){
        super.onPause();

        Assets.on_screen = false;
        Assets.notification=true;

    }

    @Override
    protected void onResume(){
        super.onResume();
        Assets.on_screen = true;
        Assets.notification=false;
    }

    public void setXY (int x, int y) {
        synchronized (lock) {
            touchx = x;
            touchy = y;
            this.touched = true;
        }
    }

    public class MyView extends View {
        float x, y;
        boolean initialized;

        public MyView(Context context) {


            super(context);
            x = 0;
            y = 0;
            initialized = false;

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                Assets.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            }
            else {
                AudioAttributes attributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();
                Assets.soundPool = new SoundPool.Builder()
                        .setAudioAttributes(attributes)
                        .build();

            }
            Assets.sound_happy = Assets.soundPool.load(context, R.raw.happy, 1);
            Assets.sound_hungry = Assets.soundPool.load(context, R.raw.hungry, 1);
            Assets.sound_eggcrack = Assets.soundPool.load(context, R.raw.eggcrack, 1);



        }





        public void LoadImg(Canvas canvas)
        {
            Bitmap bmp;
            int newWidth, newHeight;
            float scaleFactor;

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.food);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.2f);
            Assets.foodwidth = (int) (canvas.getWidth() * 0.2f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.1f);
            Assets.foodhight = (int) (canvas.getHeight() * 0.1f);
            // Scale it to a new size
            Assets.food = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.settings);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.2f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.1f);
            // Scale it to a new size
            Assets.settings = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.hungrymsg);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.4f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.2f);
            // Scale it to a new size
            Assets.hungrymsg = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.happymsg);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.4f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.2f);
            // Scale it to a new size
            Assets.happymsg = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.full);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.4f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.2f);
            // Scale it to a new size
            Assets.fullmsg = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.egg1);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.3f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.2f);
            // Scale it to a new size
            Assets.egg1 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.egg2);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.3f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.15f);
            // Scale it to a new size
            Assets.egg2 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.eggleft);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.3f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.15f);
            // Scale it to a new size
            Assets.eggleft = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.eggright);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.3f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.15f);
            // Scale it to a new size
            Assets.eggright = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.eggbreak);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.3f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.15f);
            // Scale it to a new size
            Assets.eggbreak = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.eggbroken);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.4f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.15f);
            // Scale it to a new size
            Assets.eggbroken = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.walk1);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.5f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.3f);
            // Scale it to a new size
            Assets.walk1 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.walk2);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.5f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.3f);
            // Scale it to a new size
            Assets.walk2 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.eatting1);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.5f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.3f);
            // Scale it to a new size
            Assets.eating1 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);

            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.eatting2);
            // Compute size of bitmap needed (suppose want width = 20% of screen width)
            newWidth = (int) (canvas.getWidth() * 0.5f);
            // What was the scaling factor to get to this?
            // Compute the new height
            newHeight = (int) (canvas.getHeight() * 0.3f);
            // Scale it to a new size
            Assets.eating2 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight, false);
        }

        private void LoadGraphics(Canvas canvas, int resId) {
            if (initialized == false) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
                Assets.background = Bitmap.createScaledBitmap(bmp, canvas.getWidth(), canvas.getHeight(), false);
                initialized = true;
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // Create the TTS object


            if(Assets.restart == 1){

                Assets.gameTimer = System.nanoTime() / 1000000000f;
                Assets.mp.stop();
                Assets.restart = 0;
                Intent intent =new Intent(getApplicationContext(),MainThread.class);
                startActivity(intent);
            }


            LoadImg(canvas);
            LoadGraphics(canvas, R.drawable.background1);
            canvas.drawBitmap(Assets.background, 0, 0, null);
            //canvas.drawBitmap(Assets.food, 0, 0, null);
            canvas.drawBitmap(Assets.settings, canvas.getWidth() / 1.25f, 0, null);
            Assets.settingwidth = canvas.getWidth()/1.25f;

            switch (Assets.state) {
                case eggborn:

                    int theTime = (int) (System.nanoTime() / 1000000000) % 10;
                    if (theTime % 2 == 0)
                        canvas.drawBitmap(Assets.eggleft, canvas.getWidth() / 2.77f, canvas.getHeight() / 1.42f, null);
                    else
                        canvas.drawBitmap(Assets.eggright, canvas.getWidth() / 2.77f, canvas.getHeight() / 1.42f, null);
                    int EggbornTime =(int) (System.nanoTime() / 1000000000f);
                    if (EggbornTime - Assets.gameTimer >= 4) {
                        Assets.state = Assets.EggState.eggbreak;
                    }
                    break;

                case eggbreak:

                    float currentTime = System.nanoTime() / 1000000000f;
                    if (currentTime - savetimer1 > 3) {
                        savetimer1 = currentTime;
                        Assets.soundPool.play(Assets.sound_eggcrack, 1, 1, 1, 0, 1);
                    }
                    if (currentTime - Assets.gameTimer >= 4 && currentTime - Assets.gameTimer <= 5) {
                        canvas.drawBitmap(Assets.eggbreak, canvas.getWidth() / 2.77f, canvas.getHeight() / 1.42f, null);
                    }
                    if (currentTime - Assets.gameTimer > 5 && currentTime - Assets.gameTimer <= 7) {
                        canvas.drawBitmap(Assets.eggbroken, canvas.getWidth() / 2.77f, canvas.getHeight() / 1.42f, null);
                    }

                    if (currentTime - Assets.gameTimer >= 7) {
                        Assets.state = Assets.EggState.duckborn;

                    }
                    break;

                case duckborn:
                    float happyTime1 = System.nanoTime() / 1000000000f;
                    if (happyTime1 - savetimer4 > 3) {
                        savetimer4 = happyTime1;
                        //Assets.soundPool.play(Assets.sound_happy, 1, 1, 1, 0, 1);
                        if (Assets.tts_initialized) {

                            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP)
                                Assets.tts.speak("I am very happy", TextToSpeech.QUEUE_FLUSH, null);
                            else
                                Assets.tts.speak("I am very happy", TextToSpeech.QUEUE_FLUSH, null, "1");
                            //Assets.mp.stop();

                        }
                    }
                    if(Assets.hungryTime==true){
                        Assets.gameTimer = System.nanoTime() / 1000000000f;
                        Assets.hungryTime=false;
                    }
                    x = ((x - 1) % canvas.getWidth())+10;
                    canvas.drawBitmap(Assets.food, 0, 0, null);
                    Assets.foodonscreen=true;
                    Assets.msg = System.nanoTime() / 1000000000f;
                    Assets.walk = (int) (System.nanoTime()/100000000) % 10;
                    if (Assets.walk <=4) {
                        canvas.drawBitmap(Assets.walk1, x, canvas.getHeight() / 1.66f, null);

                    }
                    else {
                        canvas.drawBitmap(Assets.walk2, x, canvas.getHeight() / 1.66f, null);

                    }
                    int hungertime =(int) (System.nanoTime() / 1000000000f);
                    if (hungertime - Assets.gameTimer >= 15) {
                        Assets.state = Assets.EggState.hungry;
                        Assets.hungryTime=true;

                    }
                    if(Assets.msg - Assets.gameTimer >16){
                        Assets.state = Assets.EggState.hungry;
                    }
                    break;

                case hungry:

                    if(Assets.notification==true){
                        startService(new Intent(getBaseContext(), NotificationMsg.class));
                        Assets.notification=false;
                    }
                    float hungerTime = System.nanoTime() / 1000000000f;
                    if (hungerTime - savetimer2 > 3) {
                        savetimer2 = hungerTime;
                        //Assets.soundPool.play(Assets.sound_hungry, 1, 1, 1, 0, 1);
                        if (Assets.tts_initialized) {

                            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP)
                                Assets.tts.speak("I am hungry", TextToSpeech.QUEUE_FLUSH, null);
                            else
                                Assets.tts.speak("I am hungry", TextToSpeech.QUEUE_FLUSH, null, "1");
                            //Assets.mp.stop();

                        }

                    }
                    if(Assets.reached==true) {
                        if (x < canvas.getWidth() / 02f) {
                            x = ((x - 1) % canvas.getWidth()) + 10;
                        }
                        else{
                            Assets.reached=false;
                        }
                    }
                    else{
                        if(x>=0) {
                            x = ((x + 1) % canvas.getWidth()) - 10;
                        }else {
                            Assets.reached=true;
                        }
                    }
                    canvas.drawBitmap(Assets.food, 0, 0, null);
                    Assets.foodonscreen=true;
                    canvas.drawBitmap(Assets.hungrymsg, canvas.getWidth() / 8.5f, canvas.getHeight() / 2.5f, null);
                    Assets.walk = (int) (System.nanoTime() / 1000000000) % 10;
                    if (Assets.walk % 2 == 0) {
                        canvas.drawBitmap(Assets.walk1, x, canvas.getHeight() / 1.66f, null);


                    }
                    else {
                        canvas.drawBitmap(Assets.walk2, x, canvas.getHeight() / 1.66f, null);

                    }
                    int hungryspeak = (int)(System.nanoTime()/1000000000)%10;
                    if(hungryspeak == 5 || hungryspeak==0){

                    }

                    break;
                case eating:


                    if(Assets.eatingTime==true){
                        Assets.gameTimer = System.nanoTime() / 1000000000f;
                        Assets.eatingTime=false;
                    }
                    Assets.walk = (int) (System.nanoTime() / 1000000000) % 10;
                    if (Assets.walk % 2 == 0) {
                        canvas.drawBitmap(Assets.eating1, canvas.getWidth() / 4, canvas.getHeight() / 1.66f, null);

                    }
                    else {
                        canvas.drawBitmap(Assets.eating2, canvas.getWidth() / 4, canvas.getHeight() / 1.66f, null);

                    }
                    int eatingtime =(int) (System.nanoTime() / 1000000000f);
                    if (eatingtime - Assets.gameTimer >= 5) {
                        Assets.state = Assets.EggState.happy;
                        Assets.happyTime=true;
                        Assets.reached=true;

                    }
                    break;
                case happy:
                    float happyTime = System.nanoTime() / 1000000000f;
                    if (happyTime - savetimer3 > 3) {
                        savetimer3 = happyTime;
                        //Assets.soundPool.play(Assets.sound_happy, 1, 1, 1, 0, 1);
                        if (Assets.tts_initialized) {

                            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP)
                                Assets.tts.speak("I am very happy", TextToSpeech.QUEUE_FLUSH, null);
                            else
                                Assets.tts.speak("I am very happy", TextToSpeech.QUEUE_FLUSH, null, "1");
                            //Assets.mp.stop();

                        }
                    }
                    if(Assets.reached==true) {
                        if (x < canvas.getWidth() / 02f) {
                            x = ((x - 1) % canvas.getWidth()) + 20;
                        }
                        else{
                            Assets.reached=false;
                        }
                    }
                    else{
                        if(x>=0) {
                            x = ((x + 1) % canvas.getWidth()) - 20;
                        }else {
                            Assets.reached=true;
                        }
                    }
                    if(Assets.happyTime==true) {
                        Assets.gameTimer = System.nanoTime() / 1000000000f;
                        Assets.happyTime=false;
                    }
                    canvas.drawBitmap(Assets.food, 0, 0, null);
                    Assets.foodonscreen=true;
                    canvas.drawBitmap(Assets.happymsg, canvas.getWidth() / 8.5f, canvas.getHeight() / 2.5f, null);
                    Assets.walk = (int) (System.nanoTime() / 1000000000) % 10;
                    if (Assets.walk % 2 == 0) {
                        canvas.drawBitmap(Assets.walk1, x, canvas.getHeight() / 1.66f, null);

                    }
                    else {
                        canvas.drawBitmap(Assets.walk2, x, canvas.getHeight() / 1.66f, null);

                    }
                    int Happyt =(int) (System.nanoTime() / 1000000000f);
                    if (Happyt - Assets.gameTimer >= Assets.feeding) {
                        Assets.state = Assets.EggState.hungry;
                        Assets.feeding = 0;
                        Assets.happyTime=true;
                        Assets.reached=true;

                    }
                    if(Assets.feeding>75){
                        if(Assets.fullTime==true) {
                            Assets.newTime = (int) (System.nanoTime() / 1000000000f);
                            Assets.fullTime=false;
                        }
                        int fullt =(int) (System.nanoTime() / 1000000000f);
                        if(fullt- Assets.newTime<=5) {
                            canvas.drawBitmap(Assets.fullmsg, canvas.getWidth() / 8.5f, canvas.getHeight() / 2.5f, null);
                        }
                    }


                    break;


            }



            // Use Color.parseColor to define HTML colors

            invalidate();


        }



        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            float x = event.getX();  // or getRawX();
            float y = event.getY();

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (x >= 0 && x < (0 + Assets.food.getWidth())
                            && y >= 0 && y < (0 + Assets.food.getHeight())) {
                        Assets.eatingTime= true;
                        if(Assets.feeding<90) {
                            Assets.feeding = Assets.feeding + 15;
                            Assets.fullTime = true;

                            if (Assets.foodonscreen == true) {
                                Assets.state = Assets.EggState.eating;
                            }
                        }


                        //Notify();
                    }
                    if (x >= Assets.settingwidth && x < (Assets.settingwidth + Assets.settings.getWidth())
                            && y >= 0 && y < (0 + Assets.settings.getHeight())) {
                        Assets.prefs= true;
                        Intent intent =new Intent(getApplicationContext(),PrefsActivity.class);
                        startActivity(intent);
                    }
                    break;
            }
            return(true);
        }

    }


}
