package com.tui.screens.components;

import com.tui.screens.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Calendar;
import java.util.Date;

public class DatePicker extends BaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(0)")
    WebElement editButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\")")
    WebElement dateInput;
    @AndroidFindBy(xpath = "//*[@resource-id=\"date_of_birth_dialog_confirm_button\"]/android.widget.Button")
    WebElement confirmButton;

    public DatePicker(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public boolean isDisplayed() {
        return isVisible(editButton);
    }

    public void selectDate(Date date) {
        tap(editButton);
        tap(dateInput);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        typeDigits(calendar.get(Calendar.MONTH) + 1);
        typeDigits(calendar.get(Calendar.DAY_OF_MONTH));
        typeDigits(calendar.get(Calendar.YEAR));

    }

    public void confirm() {
        tap(confirmButton);
    }
}
