package com.vietinterview.getbee.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.vietinterview.getbee.AccountManager;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Viettel ICT on 11/10/2016.
 */

public class AppUltil {
    private static final String TAG = AppUltil.class.getSimpleName();

    public static int getActionBarHeight(Context context) {
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }

        return actionBarHeight;
    }

    public static void showSnackbar(final View view, String text) {
        Snackbar.make(view, text, 5000)
                .setAction(null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view.requestFocus();
                    }
                }).show();
    }

    @SuppressWarnings("deprecation")
    public static OkHttpClient getOkHttpClient(boolean isHttps) throws KeyManagementException, NoSuchAlgorithmException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization", "Bearer " + AccountManager.getAccessToken());

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        });
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(120, TimeUnit.SECONDS);
        if (isHttps) {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

        }

        return builder.build();
    }


    /**
     * Scaled down image into Memory
     *
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromUri(URL url, int reqWidth, int reqHeight) throws IOException {
        Bitmap bitmap;
        // First decode with inJustDecodeBounds=true to check dimensions
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);

        return bitmap;
    }

    public static boolean getInternetSatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return (netInfo != null && netInfo.isConnected());
    }

    /**
     * Calculate down sample size
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static StringBuilder formatTime(String unFormatTime) {
        int length = unFormatTime.length();
        String time = unFormatTime.substring(length - 6, length);
        String date = unFormatTime.substring(0, length - 6);

        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);
        String second = time.substring(4, time.length());
        int dateLength = date.length();
        String day = date.substring(dateLength - 2, dateLength);
        String month = date.substring(dateLength - 4, dateLength - 2);
        String year = date.substring(0, dateLength - 4);

        StringBuilder timeFormat = new StringBuilder();
        timeFormat.append(hour).append(":").append(minute).append(":").append(second)
                .append("\t").append(day).append("/").append(month).append("/").append(year);

        return timeFormat;
    }

    public static double roundNumber(double number) {

        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(number));
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * Convert time to match request format
     *
     * @return datetime after formatted
     */

    public static String getTimeString(long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static long getUnixTime(String normalTime, String format) {
        DateFormat dfm = new SimpleDateFormat(format, Locale.getDefault());
        long unixTime = 0;
        try {
            Date date = dfm.parse(normalTime);
            unixTime = date.getTime();
        } catch (ParseException e) {
            // Catch here ...
        }

        return unixTime;
    }

//    public static int getCarStateResource(Car car) {
//        int state = 0;
//        if (car != null) {
//            state = car.getmStatus();
//        }
//        int resource = 0;
//        switch (state) {
//            case 3:
//                resource = R.drawable.car_gprs;
//                break;
//            case 4:
//                resource = R.drawable.car_gps;
//                break;
//            case 0:
//            case 10:
//            case 20:
//                resource = R.drawable.car_running;
//                break;
//            case 1:
//            case 11:
//            case 21:
//                resource = R.drawable.car_stop;
//                break;
//            case 2:
//                resource = R.drawable.car_parking;
//                break;
//            case 5:
//                resource = R.drawable.car_overspeed;
//                break;
//        }
//
//        return resource;
//    }

    public static int convertNameToId(String statusName, String[] states) {
        for (int i = 0; i < states.length; i++) {
            if (statusName.equals(states[i])) {
                return i;
            }
        }

        return 0;
    }

    public static String convertStatusToName(int status, String[] stateNames) {
        switch (status) {
            case 0:
            case 10:
            case 20:
                return stateNames[2];
            case 1:
            case 11:
            case 21:
                return stateNames[3];
            case 2:
                return stateNames[4];

            case 3:
                return stateNames[0];
            case 4:
                return stateNames[1];
            case 5:
                return stateNames[5];
        }

        return null;
    }

    public static int convertStatusToId(int status) {
        switch (status) {
            case 0:
            case 10:
            case 20:
                return 2;
            case 1:
            case 11:
            case 21:
                return 3;
            case 2:
                return 4;

            case 3:
                return 0;
            case 4:
                return 1;
            case 5:
                return 5;
        }

        return 0;
    }

//    public static float getDirectionCar(LatLng origin, LatLng destination) {
//        double originLatitude = origin.latitude;
//        double originLongitude = origin.longitude;
//        double destinationLatitude = destination.latitude;
//        double destinationLongitude = destination.longitude;
//
//        double deltaLatitudes = originLatitude - destinationLatitude;
//        double deltaLongitudes = originLongitude - destinationLongitude;
//
//        double radians = Math.atan2(deltaLongitudes, deltaLatitudes);
//        return (float) (radians * (180 / Math.PI));
//    }
//
//    public static float getDirectionCar(com.viettel.maps.base.LatLng origin, com.viettel.maps.base.LatLng destination) {
//        double originLatitude = origin.getLatitude();
//        double originLongitude = origin.getLongitude();
//        double destinationLatitude = destination.getLatitude();
//        double destinationLongitude = destination.getLongitude();
//
//        double deltaLatitudes = originLatitude - destinationLatitude;
//        double deltaLongitudes = originLongitude - destinationLongitude;
//
//        double radians = Math.atan2(deltaLongitudes, deltaLatitudes);
//        return (float) (radians * (180 / Math.PI));
//    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public static void showToast(Context context, String mess) {
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }

    public static void forceHideKeyboard(Activity activity) {
        if (activity != null && activity.getCurrentFocus() != null) {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getWindowToken(), 0);
        }
    }

    public static void showKeyboard(Context context, EditText focusView) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(focusView, InputMethodManager.SHOW_IMPLICIT);
    }

    // check period for choose other tab
    public static boolean controlPeriod(long fromTime, long toTime, boolean isReportCarTab) {
        long delta = toTime - fromTime;
        if (delta > 0) {
            if (isReportCarTab) {
                return delta <= 2 * 3600000 * 24;
            } else {
                return delta <= 3600000 * 24;
            }
        } else {
            return false;
        }
    }

    public static String replaceString(String data){
        String replaceString=data.replace("\"","");
        return replaceString;
    }
}
