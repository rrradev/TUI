package com.tui.screens;

import com.tui.entities.LoginInfoDto;
import com.tui.screens.components.DatePicker;
import com.tui.screens.enums.LoginScreenErrorField;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginScreen extends BaseScreen {

    private static final By FIELD_ERRORS = AppiumBy.xpath("//*[ends-with(@resource-id, 'field_error')]");

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"username_input_field\")")
    WebElement usernameInput;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"password_input_field\")")
    WebElement passwordInput;
    @AndroidFindBy(xpath = "//android.view.View[@resource-id=\"date_of_birth_field_calendar_icon\"]/android.widget.Button")
    WebElement calendarButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"login_form_submit_button\")")
    WebElement submitButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"username_input_field_error\")")
    WebElement usernameInputError;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"password_input_field_error\")")
    WebElement passwordInputError;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"date_of_birth_field_error\")")
    WebElement dateOfBirthInputError;

    public LoginScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public boolean isDisplayed() {
        return isVisible(usernameInput);
    }

    public void login(LoginInfoDto loginInfo) {
        fill(usernameInput, loginInfo.getUsername());
        fill(passwordInput, loginInfo.getPassword());
        tap(calendarButton);
        DatePicker datePicker = new DatePicker(driver);
        consumeIfPresent(loginInfo.getDateOfBirth(), datePicker::selectDate);
        datePicker.confirm();
        tap(submitButton);
    }

    public String getErrorMessage(LoginScreenErrorField errorField) {
        WebElement errorFieldElement;

        switch (errorField) {
            case USERNAME:
                errorFieldElement = usernameInputError;
                break;
            case PASSWORD:
                errorFieldElement = passwordInputError;
                break;
            case DATE_OF_BIRTH:
                errorFieldElement = dateOfBirthInputError;
                break;
            default:
                throw new IllegalArgumentException("No such error field: " + errorField.getLabel());
        }
        return getText(errorFieldElement);
    }

    public int getNumberOfErrors() {
        return driver.findElements(FIELD_ERRORS).size();
    }
}
