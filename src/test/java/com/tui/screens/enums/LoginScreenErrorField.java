package com.tui.screens.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum LoginScreenErrorField {

    USERNAME("Username"),
    PASSWORD("Password"),
    DATE_OF_BIRTH("Date of birth");

    private final String label;

    public static LoginScreenErrorField fromLabel(String label) {
        return Arrays.stream(LoginScreenErrorField.values())
                .filter(value -> value.label.equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such label: " + label));
    }
}
