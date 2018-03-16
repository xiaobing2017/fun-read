package com.bing.funread.common.utils;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Description:HTTP请求工具类
 * Author: zhangfusheng
 * Date: 2018/3/16 下午2:52
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static <T> T httpsRequest(String requestUrl, String requestMethod, String outputStr, Class<T> clazz) {
        logger.info("https request parameters url:{},method:{},output:{}", requestUrl, requestMethod, outputStr);
        try {
            TrustManager[] tm = {new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
                @Override
                public X509Certificate[] getAcceptedIssuers() {return null;}
            }};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);

            if (outputStr != null) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            InputStream inputStream = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bReader = new BufferedReader(reader);
            String str;
            StringBuilder builder = new StringBuilder();
            while ((str = bReader.readLine()) != null) {
                builder.append(str);
            }

            bReader.close();
            reader.close();
            inputStream.close();
            conn.disconnect();

            logger.info("https response data:{}", builder.toString());
            if (clazz != null) {
                return (T) JSONObject.parseObject(builder.toString(), clazz);
            } else {
                return (T) JSONObject.parseObject(builder.toString());
            }
        } catch (Exception e) {
            logger.error("https request error:{}", e);
        }
        return null;
    }
}
