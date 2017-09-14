package com.zm.tool.library.util;

import android.util.Log;

/**
 * 日志类
 */
public class Logger {
    public static final String TAG = "Logger";
    public static boolean isDebug = true;// 是否开启

    public static void d(String message, Object... values) {
        if (isDebug) {
            if (message == null) message = "null";
            String info = String.format(message, values);
            Log.d(TAG, track() + info);
        }

    }

    protected static String track() {
        Throwable throwable = new Throwable();
        StackTraceElement[] elements = throwable.getStackTrace();
        int len = elements.length;
        if (len > 2) {
            StackTraceElement element = elements[2];
            String classname = element.getClassName();
            StringBuilder shortClassName = new StringBuilder();
            int dotCount = 0;
            for (int i = classname.length() - 1; i >= 0; i--) {
                if (dotCount == 1) {
                    break;
                }
                shortClassName.append(classname.charAt(i));
                if (classname.charAt(i) == '.') {
                    dotCount++;
                }
            }
            return String.format("%s:%s (%s)", shortClassName.reverse().toString(), element.getMethodName(),
                    element.getLineNumber());
        }
        return null;
    }

    public static void i(String message, Object... values) {
        if (isDebug) {
            String info = String.format(message, values);
            Log.i(TAG, track() + info);
        }

    }

    public static void e(Throwable throwable) {
        if (isDebug) {
            Log.e(TAG, track() + throwable.getMessage(), throwable);
        }

    }

    public static void e(String message, Throwable throwable) {
        if (isDebug) {
            Log.e(TAG, track() + message, throwable);
        }

    }

    public static void e(String message, Object... values) {
        if (isDebug) {
            String info = String.format(message, values);
            Log.e(TAG, track() + info);
        }

    }

    public static void w(String message, Object... values) {
        if (isDebug) {
            String info = String.format(message, values);
            Log.w(TAG, track() + info);
        }

    }
}
