package cn.edu.hbpu.education.utils;

import java.util.UUID;

public class RandomVericodeUtil {
    public static String getRandomVericode(){
        String substring = UUID.randomUUID().toString().substring(0, 6);
        return substring;
    }
}
