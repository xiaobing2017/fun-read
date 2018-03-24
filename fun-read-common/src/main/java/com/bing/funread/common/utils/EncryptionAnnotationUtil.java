package com.bing.funread.common.utils;

import com.bing.funread.annotation.Decryption;
import com.bing.funread.annotation.Encryption;
import com.bing.funread.response.BaseVo;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * 加密和解密注解功能
 */
public class EncryptionAnnotationUtil {
    private EncryptionAnnotationUtil() { }

    /**
     * 加密操作
     */
    public static void encrypt(Object[] params) throws Exception {
        if (params == null || params.length == 0) {
            return;
        }
        for (Object param : params) {
            encrypt(param);
        }
    }

    public static void encrypt(Object param) throws Exception {
        if (param == null) {
            return;
        }
        if (param instanceof List) {
            List empList = (List) param;
            if (empList.isEmpty()) {
                return;
            }
            for (Object obj : empList) {
                processEncryption(obj);
            }
        } else {
            processEncryption(param);
        }
    }

    public static void decrypt(Object[] params) throws Exception {
        if (params == null || params.length == 0) {
            return;
        }
        for (Object param : params) {
            if (param == null) {
                continue;
            }
            if (param instanceof List) {
                List empList = (List) param;
                if (empList.isEmpty()) {
                    continue;
                }
                for (Object obj : empList) {
                	processDecryption(obj);
                }
            } else {
            	processDecryption(param);
            }
        }
    }

    public static Object decrypt(Object objParam) throws Exception {

        Object retValue = objParam;
        if (retValue == null) {
            return retValue;
        }
        if (retValue instanceof List) {
            List empList = (List) retValue;
            if (empList.isEmpty()) {
                return retValue;
            }
            Class c = empList.get(0).getClass();
            Decryption dn = (Decryption) c.getAnnotation(Decryption.class);
            if (dn != null) {
                Object emp = new ArrayList();
                for (Object obj : empList) {
                    Object o = processDecryption(obj);
                    ((List) emp).add(o);
                }
                retValue = emp;
            }
        } else {
            retValue = processDecryption(retValue);
        }
        return retValue;
    }

    private static void processEncryption(Object parameter) throws Exception {
        Class c = parameter.getClass();
        Encryption en = (Encryption) c.getAnnotation(Encryption.class);
        if (en != null) {
            String[] arrayOfString2 = en.value();
            int localList2 = arrayOfString2.length;
            for (int localList1 = 0; localList1 < localList2; localList1++) {
                String field = arrayOfString2[localList1];
                String s = field.substring(0, 1).toUpperCase() + field.substring(1);
                Method get = c.getMethod("get" + s, null);
                Method set = c.getMethod("set" + s, new Class[] { get.getReturnType() });
                String value = (String) get.invoke(parameter, null);
                if (value != null)
                    set.invoke(parameter, new Object[] { EncryptionUtil.encrypt(value) });
            }
        } else {
            Field[] fields = c.getDeclaredFields();
            if (ArrayUtils.isEmpty(fields)) {
                return;
            }
            for (Field field : fields) {
                field.setAccessible(true);
                Object param = field.get(parameter);
                if (param instanceof List) {
                    List empList = (List) param;
                    if (empList.isEmpty()) {
                        return;
                    }
                    for (Object obj : empList) {
                        processEncryption(obj);
                    }
                } else if (param instanceof BaseVo) {
                    processEncryption(param);
                }
            }
        }
    }

    private static Object processDecryption(Object parameter) throws Exception {
        Class par = parameter.getClass();
        Decryption d = (Decryption) par.getAnnotation(Decryption.class);
        String[] fields;
        Method set;
        String value;
        if (d != null) {
            fields = d.value();
            for (String field : fields) {
                String s = field.substring(0, 1).toUpperCase() + field.substring(1);
                Method get = par.getMethod("get" + s, null);
                set = par.getMethod("set" + s, new Class[] { get.getReturnType() });
                value = (String) get.invoke(parameter, null);
                if (value != null) {
                    set.invoke(parameter, new Object[] { EncryptionUtil.decrypt(value) });
                }
            }
        }
        return parameter;
    }
}

