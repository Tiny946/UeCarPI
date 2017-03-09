package com.example.alberto.uecarpi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);


        //TextView myTitle = (TextView)findViewById(R.id.textView3);
        TextView mySubtitle = (TextView)findViewById(R.id.textView4);
        //ImageView myImage = (ImageView)findViewById(R.id.imageView);
        final ImageView rueda = (ImageView)findViewById(R.id.imageView2);

        /* sets a Pretty Custom Font
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/SensaBrush-FillDemo.otf");
        myTitle.setTypeface(myFont);
        mySubtitle.setTypeface(myFont);
    */

        //Animation myanim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        //final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.ab);


        rueda.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rueda.startAnimation(an);
                finish();
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        openApp(true);
    }


    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen
                        .this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

}
