package lyn.projectreportingsystem.util;

import org.springframework.util.DigestUtils;

public class MD5 {

    private final static String SALT = "ag;'a;,hwgerihe.g";

    public static String getMD5(String password){
        String base = password + SALT;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
