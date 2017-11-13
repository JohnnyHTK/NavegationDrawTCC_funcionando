package com.example.martinsj.navegationdraw;

/**
 * Created by jonat on 13/11/2017.
 */

public class Cifracesar {

    public static String decrypt(String str)
    {
        String decrypted = "";
        for(int i = 0; i < str.length(); i++)
        {
            int c = str.charAt(i);
            if (Character.isUpperCase(c))
            {
                c = c - (3 % 26);
                if (c < 'A')
                    c = c + 26;
            }
            else if (Character.isLowerCase(c))
            {
                c = c - (3 % 26);
                if (c < 'a')
                    c = c + 26;
            }
            decrypted += (char) c;
        }
        return decrypted;
    }
    public static String encrypt(String str)
    {
        String encrypted = "";
        for(int i = 0; i < str.length(); i++)
        {
            int c = str.charAt(i);

            if (Character.isUpperCase(c))
            {
                //26 letters of the alphabet so mod by 26
                c = c + (3 % 26);
                if (c > 'Z')
                    c = c - 26;
            }
            else if (Character.isLowerCase(c))
            {
                c = c + (3 % 26);
                if (c > 'z')
                    c = c - 26;
            }
            encrypted += (char) c;
        }
        return encrypted;
    }
}
