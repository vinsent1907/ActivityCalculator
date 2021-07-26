package com.activitycalculator.util;

public class ActivityUtil {
    public static double halfLife(long duration, double startAct, double finalAct) {
        return -(duration * Math.log(2) / (Math.log(finalAct / startAct)));
    }

    public static double activityAtTime(double act, long time, double halfLife) {
        return act * Math.pow(2.0, time / halfLife); //109.771
    }
}
