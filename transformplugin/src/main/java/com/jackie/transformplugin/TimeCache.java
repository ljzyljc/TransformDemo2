package com.jackie.transformplugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author feifei5292190@gmail.com
 * @date 2020-01-04
 */
public class TimeCache {

    public static Map<String, Long> sStartTimes = new HashMap<>();
    public static Map<String, Long> sEndTimes = new HashMap<>();

    public static void setStartTime(String methodName, long time) {
        sStartTimes.put(methodName, time);
    }

    public static void setEndTime(String methodName, long time) {
        sEndTimes.put(methodName, time);
    }

    public static String getCostTime(String methodName) {
        long start = sStartTimes.get(methodName);
        long end = sEndTimes.get(methodName);
        return "method: " + methodName + " main " + Long.valueOf(end - start) + " ns";
    }
}
