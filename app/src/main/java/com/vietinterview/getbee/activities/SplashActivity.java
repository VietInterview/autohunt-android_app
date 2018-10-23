package com.vietinterview.getbee.activities;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vietinterview.getbee.R;

/**
 * Created by hienvv7 on 05/07/2017.
 */

public class SplashActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private Handler mHandlerTimer = null;
    private ImageView mLogoImageView;

    @Override
    public int setContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        mLogoImageView = (ImageView) findViewById(R.id.splashscreen);
        Glide.with(this).load(R.drawable.splash_pic).into(mLogoImageView);
        showFadeIn();
    }

    @Override
    public void initData() {

    }

    /**
     * Show fade in logo app
     */
    private void showFadeIn() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHandlerTimer = new Handler();
                mHandlerTimer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showFadeOut();
                    }
                }, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mLogoImageView.startAnimation(fadeInAnimation);
    }

    /**
     * Show fade out logo app
     */
    private void showFadeOut() {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLogoImageView.setVisibility(View.GONE);
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mLogoImageView.startAnimation(fadeOutAnimation);

    }
}
