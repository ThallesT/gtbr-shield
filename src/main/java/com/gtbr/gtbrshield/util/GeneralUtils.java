package com.gtbr.gtbrshield.util;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneralUtils {

    public static String generatePin() {
        return RandomStringUtils.randomNumeric(6);
    }
}
