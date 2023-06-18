package com.koala.service.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/16 18:37
 * @description
 */
public class AESUtils {
    public static boolean initialized = false;

    public static final String ALGORITHM = "AES/ECB/PKCS7Padding";

    /**
     * @param  String str  要被加密的字符串
     * @param  byte[] key  加/解密要用的长度为32的字节数组（256位）密钥
     * @return byte[]  加密后的字节数组
     */
    public static byte[] aes256Encode(String str, byte[] key){
        initialize();
        byte[] result = null;
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); //生成加密解密需要的Key
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            result = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param  byte[] bytes  要被解密的字节数组
     * @param  byte[] key    加/解密要用的长度为32的字节数组（256位）密钥
     * @return String  解密后的字符串
     */
    public static String aes256Decode(byte[] bytes, byte[] key){
        initialize();
        String result = null;
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); //生成加密解密需要的Key
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decoded = cipher.doFinal(bytes);
            result = new String(decoded, StandardCharsets.UTF_8);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void initialize(){
        if (initialized) return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

}
