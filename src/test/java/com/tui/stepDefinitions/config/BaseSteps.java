package com.tui.stepDefinitions.config;

import io.appium.java_client.android.AndroidDriver;
import lombok.NoArgsConstructor;
import org.assertj.core.api.SoftAssertions;

@NoArgsConstructor
public abstract class BaseSteps {

    protected SoftAssertions softly = new SoftAssertions();

    protected AndroidDriver getDriver() {
        return Hooks.driverThreadLocal.get();
    }
}
