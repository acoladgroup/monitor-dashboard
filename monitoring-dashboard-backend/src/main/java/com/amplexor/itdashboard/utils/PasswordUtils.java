package com.amplexor.itdashboard.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by marquesh on 13/08/2018.
 */
public class PasswordUtils {

    /**
     * Validation of hashed and/or salted password.
     *
     * @param hash  Hashed password
     * @param clear Clear password
     * @return return true if the password is correct else false.
     * @throws NoSuchAlgorithmException if an error occurs
     */
    public static boolean checkPassword(String hash, String clear) throws NoSuchAlgorithmException {
        String salt = "";
        if (hash != null && hash.length() > 35) {
            salt = hash.substring(7, 11);
        }

        byte[] sha1 = MessageDigest.getInstance("SHA1").digest((salt + clear).getBytes());
        String hashed = "{SHA-1}" + salt + new sun.misc.BASE64Encoder().encode(sha1);
        return hashed.equals(hash);
    }
}
