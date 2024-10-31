package com.tui.screens.components;

import com.tui.screens.BaseScreen;
import com.tui.screens.enums.TopBarTab;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TopBar extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"top_app_bar_holidays_tab\")")
    WebElement holidaysTab;

    public TopBar(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public boolean isDisplayed() {
        return isVisible(holidaysTab);
    }

    public void openTab(TopBarTab tab) {
        switch (tab) {
            case HOLIDAYS:
                tap(holidaysTab);
                break;
            default:
                throw new IllegalArgumentException("No such tab: " + tab.getLabel());
        }
    }
}
