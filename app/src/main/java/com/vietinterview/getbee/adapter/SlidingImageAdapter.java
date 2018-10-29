package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.activities.BaseActivity;
import com.vietinterview.getbee.activities.MainActivity;

import java.util.ArrayList;

/**
 * Created by SONU on 29/08/15.
 */
public class SlidingImageAdapter extends PagerAdapter {
    private ArrayList<Integer> IMAGES;
    ArrayList<String> introString;
    private LayoutInflater inflater;
    private Context context;
    BaseActivity baseActivity;

    public SlidingImageAdapter(Context context, ArrayList<Integer> IMAGES, ArrayList<String> introString, BaseActivity baseActivity) {
        this.context = context;
        this.IMAGES = IMAGES;
        this.introString = introString;
        this.baseActivity = baseActivity;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        final TextView tvIntro = (TextView) imageLayout.findViewById(R.id.tvIntro);
        Button btnLogin = imageLayout.findViewById(R.id.btnLogin);
        Button btnRegist = imageLayout.findViewById(R.id.btnRegist);
        tvIntro.setText(introString.get(position));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(baseActivity, MainActivity.class);
                mainIntent.putExtra("isLogin", true);
                baseActivity.startActivity(mainIntent);
                baseActivity.finish();
            }
        });
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(baseActivity, MainActivity.class);
                mainIntent.putExtra("isLogin", false);
                baseActivity.startActivity(mainIntent);
                baseActivity.finish();
            }
        });
        LinearLayout llFooter = (LinearLayout) imageLayout.findViewById(R.id.llFooter);
        if (position == 2) {
            llFooter.setVisibility(View.VISIBLE);
        } else {
            llFooter.setVisibility(View.GONE);
        }
        imageView.setImageResource(IMAGES.get(position));
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
