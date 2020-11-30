package com.login.sso_login.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author liurong
 * @date 2020/10/26 21:56
 */
public class SecretUtils {

    private static final String AES_KEY_ALGORITM = "AES";

    private static final String SECURE_RANDOM_KEY = "SHA1PRNG";

    private static final String ENCODE_RULES = "ronge@23";

    public static String AesEncrypt(String inputData) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_KEY_ALGORITM);
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM_KEY);
            secureRandom.setSeed(ENCODE_RULES.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey key = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance(AES_KEY_ALGORITM);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            //加密内容
            byte[] contents = inputData.getBytes(StandardCharsets.UTF_8);
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return new String(base64Encoder.encode(cipher.doFinal(contents)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String AesDecrypt(String inputData) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_KEY_ALGORITM);
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM_KEY);
            secureRandom.setSeed(ENCODE_RULES.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
//            byte[] keyData=secretKey.getEncoded();
//            SecretKey origin_key=new SecretKeySpec(keyData,AES_KEY_ALGORITM);
            Cipher cipher = Cipher.getInstance(AES_KEY_ALGORITM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] content = new BASE64Decoder().decodeBuffer(inputData);
            return new String(cipher.doFinal(content), "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


}
