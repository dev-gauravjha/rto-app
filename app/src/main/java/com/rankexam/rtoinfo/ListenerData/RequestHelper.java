package com.rankexam.rtoinfo.ListenerData;

import static com.rankexam.rtoinfo.Data.opid1;
import static com.rankexam.rtoinfo.Data.opversion1;
import static com.rankexam.rtoinfo.Data.opversioncode1;
import static com.rankexam.rtoinfo.ListenerData.RequestLoaders.getAndroidId;

import android.content.Context;
import android.os.Build;

import com.google.firebase.remoteconfig.RemoteConfigConstants;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

public class RequestHelper {
    public static Map<String, Object> getRequestParams(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME, opid1);
        hashMap.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, opversion1);
        hashMap.put("appVersionCode", opversioncode1);
        hashMap.put("manufacturer",  Build.MANUFACTURER);
        hashMap.put("model",  Build.MODEL);
        hashMap.put("osVersion", Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("deviceName",  Build.DEVICE);
        hashMap.put("deviceBrand",  Build.BRAND);
        hashMap.put("deviceWidth", 1208);
        hashMap.put("deviceHeight", 720);
        return hashMap;
    }
    public static String getTimeInMilli() {
        return String.valueOf(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime());
    }
    public static String getRandomNumber() {
        return String.valueOf(new Random().nextInt(1000));
    }
    public static int getDeviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static String encryptStr(Context context, String str, String str2) {

        byte[] bytes = ("android_vehicle-details|encTradetu-" + str + "|" + opversioncode1 + "|" + opid1 + "|" + str2).getBytes();
        StringBuilder sb = new StringBuilder();
        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
//            messageDigest.reset();
//            messageDigest.update(bytes);
//            for (byte b : messageDigest.digest()) {
//                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
//                if (hexString.length() == 1) {
//                    sb.append("0");
//                }
//                sb.append(hexString);
//            }
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(bytes);

            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return sb.toString();
    }

    public static Map<String, String> getRequestHeaders(Context context, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME,  opid1);
        hashMap.put("src", "android_vehicle-details");
        hashMap.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION,opversion1);
        hashMap.put("appVersionCode",  opversioncode1);
        hashMap.put("apiKey", "");
        hashMap.put("clientId", "android_vehicle-details");
        hashMap.put("deviceId", getAndroidId(context));
        hashMap.put("ts", getTimeInMilli());
        hashMap.put("manufacturer",  Build.MANUFACTURER);
        hashMap.put("model",  Build.MODEL);
        hashMap.put("osVersion", "" + Build.VERSION.SDK_INT);
        String randomNumber = getRandomNumber();

        hashMap.put("salt", randomNumber);

        hashMap.put("auth", encryptStr(context, randomNumber, str));

        hashMap.put("deviceWidth", "" +getDeviceWidth(context));
        hashMap.put("deviceHeight", "" + getDeviceHeight(context));
        hashMap.put("city", "");
        hashMap.put("region", "");
        hashMap.put("zip", "");
        hashMap.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.1.1; D2502 Build/19.4.A.0.182)");
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        hashMap.put("fcmToken", "ec1a8f7284383469356aaa6129c356aa0a1150c4d0e0ad704e1bec1f53062644299b61e0a20bde1acc0e7278ae6180e607af3a989da188e110e49f969fe7c432");
        PrintStream printStream = System.out;
        printStream.println("Request Headers: " + hashMap);
        return hashMap;
    }

}
