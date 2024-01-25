package com.rankexam.rtoinfo;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Data {
    static {
        System.loadLibrary("hello-jni");
    }

    public Data() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidParameterSpecException, BadPaddingException, InvalidKeyException {
    }


    public static native String MainURl();
    public static final String MainURl = MainURl();

    public static native String rskey();
    public static final String rskey = rskey();
    public static native String opversion();
    public static final String opversion = opversion();
    public static native String opversioncode();
    public static final String opversioncode = opversioncode();

    public static native String opid();
    public static final String opid = opid();
    public static String url;
    public static String opid1;
    public static String opversion1;
    public static String opversioncode1;
    static {
        try {
            url = decryptMsg(parseHexStr2Byte(MainURl), rskey);
            opid1 = decryptMsg(parseHexStr2Byte(opid), rskey);
            opversion1 = decryptMsg(parseHexStr2Byte(opversion), rskey);
            opversioncode1 = decryptMsg(parseHexStr2Byte(opversioncode), rskey);

        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidParameterSpecException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static native String rikey();
    public static final String rikey = rikey();

    public static byte[] parseHexStr2Byte(String hexStr) {
        Log.i("decryptMsg",rikey());
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1),
                    16);
            int low = Integer.parseInt(
                    hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static String decryptMsg(byte[] cipherText, String  secret)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException
    {

        try {
            IvParameterSpec iv = new IvParameterSpec(rikey().getBytes());
            SecretKeySpec skeySpec = new SecretKeySpec(secret.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(cipherText);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
