package com.gionee.practices.crash;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Author: wsq
 * Date: 18-5-11
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class CrashFileUtils {
    private static final String TAG = "CrashFileUtils";

    private CrashFileUtils() {
    }

    public static synchronized void writeLogToFile(File logFile, String crashLog) {
        logFile.getParentFile().mkdirs();
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            Log.d(TAG, "writeLogToFile: " + logFile.getAbsolutePath());
            fileWriter = new FileWriter(logFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter
                    .append("==============================================")
                    .append("\n")
                    .append(crashLog);
            bufferedWriter.flush();
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedWriter) {
                    bufferedWriter.close();
                }
                if (null != fileWriter) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
