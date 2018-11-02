package com.vietinterview.getbee.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.SlidingImageAdapter;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.SharedPrefUtils;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class IntroActivity extends BaseActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.ic_pic1_intro, R.drawable.ic_pic2_intro, R.drawable.ic_pic3_intro};
    private static final String[] String = {"A B2B Marketplace for Employers & Recruitment Agencies\n", "Vietnam's Largest Recruitment Agencies Marketplace", "1000's of quality recruiters working for you!"};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private ArrayList<String> StringsArray = new ArrayList<String>();

    @Override
    public int setContentViewId() {
        return R.layout.activity_intro;
    }

    @Override
    public void initView() {
        DebugLog.showLogCat(SharedPrefUtils.getBoolean(AppConstant.FIRST, true) + "");
        if (SharedPrefUtils.getBoolean(AppConstant.FIRST, true)) {
            init();
        } else {
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.putExtra("isLogin", true);
            startActivity(mainIntent);
            finish();
        }
    }

    @Override
    public void initData() {

    }

    private void init() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);
        for (int i = 0; i < String.length; i++)
            StringsArray.add(String[i]);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(IntroActivity.this, ImagesArray, StringsArray, this));
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        NUM_PAGES = IMAGES.length;
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}
