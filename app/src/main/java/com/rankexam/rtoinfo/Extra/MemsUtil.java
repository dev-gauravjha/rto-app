package com.rankexam.rtoinfo.Extra;


public class MemsUtil {
    public static final float BYTES_IN_MB = 1048576.0f;

    public static float megabytesFree() {
        return megabytesAvailable() - (((float) Runtime.getRuntime().totalMemory()) / 1048576.0f);
    }

    public static float megabytesAvailable() {
        return ((float) Runtime.getRuntime().maxMemory()) / 1048576.0f;
    }
}
