package com.mark.frame.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by lulei on 2017/9/22.
 */
public class LogUtil {
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String result;
        try {
            t.printStackTrace(pw);
            result = sw.toString();
        } finally {
            pw.close();
        }
        return result;
    }
}
