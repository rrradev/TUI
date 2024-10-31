package com.tui.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

   public static List<String> toList(String str, String separator) {
        return Arrays.stream(str.split(separator))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
