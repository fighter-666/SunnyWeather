package com.example.sunnyweather.base;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * 说明：
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/12/29 10:03
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class HttpsFactroy {

    public static SSLSocketFactory getSSLSocketFactory(KeyStore keyStore) throws GeneralSecurityException {
        SSLContext sslContext;
        X509TrustManager trustManager;
//        if (keyStore == null) {
//            sslContext = SSLContext.getInstance("SSL");
//            TrustManager[] trustAllCerts = new TrustManager[] { createX509TrustManager() };
//            sslContext.init(null, trustAllCerts, new SecureRandom());
//        } else {
//            sslContext = SSLContext.getInstance("SSL");
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(keyStore);
//            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            keyManagerFactory.init(keyStore, null);
//            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
//        }

        sslContext = SSLContext.getInstance("SSL");
        trustManager = createX509TrustManager(keyStore);
        sslContext.init(null, new TrustManager[] { trustManager }, null);
        return sslContext.getSocketFactory();
    }

    public static X509TrustManager createX509TrustManager(KeyStore keyStore) throws GeneralSecurityException {
        char[] password = "password".toCharArray(); // Any password will work.

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
        //开启以下注释可以屏蔽证书校验，开放抓包能力
//        return new X509TrustManager(){
//
//            @Override
//            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//            }
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
//            }
//        };
    }

    //获取这个SSLSocketFactory
//    public static SSLSocketFactory getSSLSocketFactory() {
//        try {
//            SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, getTrustManager(), new SecureRandom());
//            return sslContext.getSocketFactory();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    //获取HostnameVerifier
//    public static HostnameVerifier getHostnameVerifier() {
//        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
//            @Override
//            public boolean verify(String s, SSLSession sslSession) {
//                return true;
//            }
//        };
//        return hostnameVerifier;
//    }

    //获取TrustManager
//    private static TrustManager[] getTrustManager() {
//        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//            }
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[]{};
//            }
//        }};
//        return trustAllCerts;
//    }

}
