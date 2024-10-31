package com.tui.screens.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TopBarTab {

    ALL("All"),
    HOTELS("Hotels"),
    HOLIDAYS("Holidays");

    private final String label;

    public static TopBarTab fromLabel(String label) {
        return Arrays.stream(values())
                .filter(value -> value.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such label: " + label));
    }
}
