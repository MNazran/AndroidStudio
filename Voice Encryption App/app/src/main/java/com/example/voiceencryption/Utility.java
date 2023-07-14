package com.example.voiceencryption;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static String getOnlyStrings(String s) {
        Pattern pattern = Pattern.compile("[^a-z A-Z]");
        Matcher matcher = pattern.matcher(s);
        String mint = matcher.replaceAll("");
        return mint;

    }
    public static String getOnlyDigits(String s) {
        Pattern pattern = Pattern.compile("[^8-9]");
        Matcher matcher = pattern.matcher(s);
        String numDigit = matcher.replaceAll("");
        return numDigit;
    }

}
