package com.tui.util;

import io.appium.java_client.android.nativekey.AndroidKey;

import java.util.Map;

public class AndroidKeyUtils {

    private static final Map<Integer, AndroidKey> digitKeyMap = Map.of(0, AndroidKey.DIGIT_0,
            1, AndroidKey.DIGIT_1,
            2, AndroidKey.DIGIT_2,
            3, AndroidKey.DIGIT_3,
            4, AndroidKey.DIGIT_4,
            5, AndroidKey.DIGIT_5,
            6, AndroidKey.DIGIT_6,
            7, AndroidKey.DIGIT_7,
            8, AndroidKey.DIGIT_8,
            9, AndroidKey.DIGIT_9);

    public static AndroidKey getKeyFromDigit(int digit) {
        return digitKeyMap.get(digit);
    }

}
