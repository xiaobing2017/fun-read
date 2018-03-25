package com.bing.funread.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * 加密和解密
 */
public class EncryptionUtil {

    private static final String STATIC = "static/";

    private static final String  PREFIX = "xb";

    private EncryptionUtil() {}

    private static Logger log = LoggerFactory.getLogger(EncryptionUtil.class);
    private static final String CIPHER_MODE = "AES/CBC/PKCS5Padding";
    private static final String KEY = "xiaobing12345678";
    private static final String IV = "x1i2a3o4b5i6n7g8";
    private static final String DATE = "2018";
    private static final String ENCODING = "UTF-8";

    private static SecretKeySpec createKey(String key) {
        byte[] data = null;

        StringBuilder sb = new StringBuilder(16);
        sb.append(org.apache.commons.lang.StringUtils.defaultString(key));

        while(sb.length() < 16) {
            sb.append("0");
        }

        if (sb.length() > 16) {
            sb.setLength(16);
        }

        try {
            data = sb.toString().getBytes(ENCODING);
        } catch (UnsupportedEncodingException var4) {
            log.error(var4.getMessage(), var4);
        }

        return new SecretKeySpec(data, "AES");
    }

    private static IvParameterSpec createIV(String password) {
        byte[] data = null;

        StringBuilder sb = new StringBuilder(16);
        sb.append(org.apache.commons.lang.StringUtils.defaultString(password));

        while(sb.length() < 16) {
            sb.append("0");
        }

        if (sb.length() > 16) {
            sb.setLength(16);
        }

        try {
            data = sb.toString().getBytes(ENCODING);
        } catch (UnsupportedEncodingException var4) {
            log.error(var4.getMessage(), var4);
        }

        return new IvParameterSpec(data);
    }

    private static byte[] encrypt(byte[] content) {
        try {
            SecretKeySpec key = createKey(KEY);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(1, key, createIV(IV));
            return cipher.doFinal(content);
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return new byte[0];
        }
    }

    public static String encrypt(String content) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        if (content.startsWith(PREFIX)) {
            return content;
        }

        try {
            return STATIC + PREFIX + byteToHexString(encrypt(content.getBytes(ENCODING))) + DATE;
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return null;
        }
    }

    private static byte[] decrypt(byte[] content) {
        try {
            SecretKeySpec key = createKey(KEY);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(2, key, createIV(IV));
            return cipher.doFinal(content);
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return new byte[0];
        }
    }

    public static String decrypt(String content) {
        if (StringUtils.isBlank(content) || !content.startsWith(PREFIX)) {
            return content;
        }

        try {
            content = content.substring(2);
            content = content.substring(0, content.length() - DATE.length());
            byte[] data = decrypt(hexStringToByte(content));
            if (data == null || data.length == 0) {
                return null;
            }

            return new String(data, ENCODING);
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return null;
        }
    }

    private static String byteToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte[] var2 = bytes;
        int var3 = bytes.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            String strHex = Integer.toHexString(b);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else if (strHex.length() < 2) {
                sb.append("0").append(strHex);
            } else {
                sb.append(strHex);
            }
        }

        return sb.toString();
    }

    private static byte[] hexStringToByte(String s) {
        byte[] baKeyword = new byte[s.length() / 2];

        for(int i = 0; i < baKeyword.length; ++i) {
            try {
                baKeyword[i] = (byte)(255 & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception var4) {
                log.error(var4.getMessage(), var4);
            }
        }

        return baKeyword;
    }
}
