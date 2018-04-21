package com.elliotb.Helpers;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordHash {

    public static byte[] hashPassword(final char[] password, final byte[] salt){

        try{
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, 10, 256);
            SecretKey secretKey = skf.generateSecret(spec);

            byte[] res = secretKey.getEncoded();
            return res;

        }catch (NoSuchAlgorithmException | InvalidKeySpecException ex){
            throw new RuntimeException(ex);
        }

    }

}
