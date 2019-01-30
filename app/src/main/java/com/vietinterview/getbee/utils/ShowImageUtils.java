package com.vietinterview.getbee.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vietinterview.getbee.AccountManager;

/**
 * Created by hiepnguyennghia on 11/9/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ShowImageUtils {
    public static void showImage(Context context, String urlImage, int iconError, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .error(iconError)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(context).load(AccountManager.getApiConstant().getIMG_URL() + urlImage).apply(options).into(imageView);
    }

    public static void showImageCus(Context context, String urlImage, int iconError, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .error(iconError)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(context).load(urlImage).apply(options).into(imageView);
    }
}
