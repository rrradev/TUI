package com.tui.screens;

import com.tui.util.AndroidKeyUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.tui.util.NumericUtils.toDigits;

public abstract class BaseScreen {

    protected static final Duration SCROLL_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(5);

    protected final AndroidDriver driver;
    private final WebDriverWait wait;


    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public abstract boolean isDisplayed();

    public void fill(WebElement element, String data) {
        waitForVisibilityOf(element);
        element.clear();
        element.sendKeys(data);
    }

    public void tap(WebElement element) {
        waitToBeClickable(element);
        element.click();
    }

    public boolean isVisible(WebElement element) {
        try {
            waitForVisibilityOf(element);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public void typeDigits(int number) {
        List<Integer> digits = toDigits(number);
        for (Integer digit : digits) {
            driver.pressKey(new KeyEvent(AndroidKeyUtils.getKeyFromDigit(digit)));
        }
    }

    protected void scroll(WebElement from, WebElement to, int yOffset, int xOffset) {
        int fromX = from.getLocation().getX();
        int fromY = from.getLocation().getY();
        int toX = to.getLocation().getX() + xOffset;
        int toY = to.getLocation().getY() + yOffset;

        new Actions(driver)
                .moveToLocation(fromX, fromY)
                .clickAndHold()
                .moveToLocation(toX, toY)
                .pause(Duration.ofMillis(500L))
                .release()
                .perform();
    }

    protected String getText(WebElement element) {
        waitForVisibilityOf(element);
        return element.getText();
    }

    protected <T> void consumeIfPresent(T value, Consumer<T> consumer) {
        Optional.ofNullable(value)
                .ifPresent(consumer);
    }

    private void waitForVisibilityOf(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
