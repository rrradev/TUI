package com.tui.stepDefinitions.config;

import com.tui.entities.LoginInfoDto;
import com.tui.screens.LoginScreen;
import com.tui.util.PropertiesHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hooks {

    static AppiumDriverLocalService service;
    static ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeAll
    public static void beforeAll() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @Before(order = 1)
    public void beforeScenario() {
        String apkPath = Paths.get("src", "test", "resources", "apk", "app-release.apk")
                .toAbsolutePath().toString();
        UiAutomator2Options options = new UiAutomator2Options()
                .setAppPackage("com.tui.qa.challenge")
                .setApp(apkPath);
        try {
            AndroidDriver driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723"), options
            );
            driverThreadLocal.set(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Before(value = "not @login", order = 2)
    public void beforeScenarioNotLogin() throws ParseException, IOException {
        PropertiesHelper propertiesHelper = new PropertiesHelper("config.properties");
        String dateString = propertiesHelper.getProperty("date_of_birth");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);

        new LoginScreen(driverThreadLocal.get()).login(
                LoginInfoDto.builder()
                        .username(propertiesHelper.getProperty("username"))
                        .password(propertiesHelper.getProperty("password"))
                        .dateOfBirth(date)
                        .build()
        );
    }

    @After
    public void afterScenario() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }

    @AfterAll
    public static void afterAll() {
        service.stop();
    }
}
