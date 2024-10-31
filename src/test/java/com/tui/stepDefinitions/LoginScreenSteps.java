package com.tui.stepDefinitions;

import com.tui.entities.LoginInfoDto;
import com.tui.screens.LoginScreen;
import com.tui.screens.enums.LoginScreenErrorField;
import com.tui.stepDefinitions.config.BaseSteps;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.tui.util.StringUtils.toList;

public class LoginScreenSteps extends BaseSteps {

    private LoginScreen loginScreen;

    @ParameterType("(.+)")
    public List<LoginScreenErrorField> errorFields(String errorFields) {
        List<String> errorList = toList(errorFields, ",");
        return errorList.stream()
                .map(LoginScreenErrorField::fromLabel)
                .collect(Collectors.toList());
    }

    @When("the user logs in with credentials:")
    public void theUserLogsInWithValidCredentials(LoginInfoDto loginInfo) {
        loginScreen = new LoginScreen(getDriver());
        loginScreen.login(loginInfo);
    }

    @Then("he should see the {errorFields} error field(s) with message {string}")
    public void shouldSeeErrors(List<LoginScreenErrorField> errorFields, String message) {
        errorFields.forEach(
                errorField -> softly.assertThat(loginScreen.getErrorMessage(errorField))
                        .as(errorField.getLabel() + " error field is correct")
                        .isEqualTo(message)
        );
        softly.assertThat(loginScreen.getNumberOfErrors())
                .as("Number of errors displayed is correct")
                .isEqualTo(errorFields.size());
        softly.assertAll();
    }
}
