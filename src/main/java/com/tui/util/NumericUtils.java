package com.tui.util;

import java.util.ArrayList;
import java.util.List;

public class NumericUtils {

    public static List<Integer> toDigits(int number) {
        ArrayList<Integer> digits = new ArrayList<>();

        while (number > 0) {
            digits.add(0, number % 10);
            number /= 10;
        }
        return digits;
    }
}
