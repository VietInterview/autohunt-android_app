
package com.vietinterview.getbee.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by V4-OS01 on 3/11/2016.
 */

public class TrustManagerManipulator implements X509TrustManager {


    private static TrustManager[] trustManagers;
    private static final X509Certificate[] acceptedIssuers = new X509Certificate[]{};

    public boolean isClientTrusted(X509Certificate[] chain) {
        return true;
    }

    public boolean isServerTrusted(X509Certificate[] chain) {
        return true;
    }

    public synchronized static void allowAllSSL() {

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
//        SSLContext context = null;
        if (trustManagers == null) {
            trustManagers = new TrustManager[]{new TrustManagerManipulator()};
        }
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, trustManagers, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (NoSuchAlgorithmException e) {
        } catch (KeyManagementException e) {
        }
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return acceptedIssuers;
    }
}