package com.example.springwebsocket.util;

import java.text.DecimalFormat;

public class RuntimeUtil {

    final static DecimalFormat memoryFormat = new DecimalFormat("#,##0.00 Mb");

    final static double MB_Numeral = 1024d * 1024d;

    public RuntimeUtil() {

    }

    // 총 힙 메모리
    public static double getTotalMemoryInMb() {
        return Runtime.getRuntime().totalMemory() / MB_Numeral;
    }

    // 사용 가능한 힙 메모리
    public static double getFreeMemoryInMb() {
        return Runtime.getRuntime().freeMemory() / MB_Numeral;
    }

    // 사용된 힙 메모리
    public static double getUsedMemoryInMb() {
        return getTotalMemoryInMb() - getFreeMemoryInMb();
    }

    public static String getTotalMemoryStringInMb() {
        return memoryFormat.format(getTotalMemoryInMb());
    }

    public static String getFreeMemoryStringInMb() {
        return memoryFormat.format(getFreeMemoryInMb());
    }

    public static String getUsedMemoryStringInMb() {
        return memoryFormat.format(getUsedMemoryInMb());
    }


}
