package com.jeza;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;

public class MD5_Digest {
    public static String digest(Path filePath) {
        StringBuilder d = new StringBuilder("");
        try {
            MessageDigest alg = MessageDigest.getInstance("MD5");

            byte[] input = Files.readAllBytes(filePath); //获得给文件的字节数组
            byte[] hash = alg.digest(input); //获得 md5

            for (byte aHash : hash) {
                int v = aHash & 0xFF;
                if (v < 16) d.append("0"); //位数低就补个0
                d.append(Integer.toHexString(v).toUpperCase()); //全部切成大写字母
                d.append(" "); //每两位空一格
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d.toString();
    }
}
