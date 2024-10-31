package com.tui.stepDefinitions;

import com.tui.entities.ContentCardDto;
import com.tui.screens.ContentScreen;
import com.tui.screens.enums.TopBarTab;
import com.tui.stepDefinitions.config.BaseSteps;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentScreenSteps extends BaseSteps {

    private ContentScreen contentScreen;

    @ParameterType("(.+)")
    public TopBarTab topBarName(String topBarName) {
        return TopBarTab.fromLabel(topBarName);
    }

    @Then("he should see the top navigation bar")
    public void heShouldSeeTheTopNavigationBar() {
        contentScreen = new ContentScreen(getDriver());

        assertThat(contentScreen.getTopBar().isDisplayed())
                .as("Top navigation bar is displayed")
                .isTrue();
    }

    @When("the user navigates to {topBarName} from the top bar")
    public void theUserNavigatesTo(TopBarTab topBarTab) {
        contentScreen = new ContentScreen(getDriver());
        contentScreen.getTopBar().openTab(topBarTab);
    }

    @Then("he can see a holiday listed:")
    public void heCanSeeAHolidayListed(ContentCardDto expectedCard) {
        ContentCardDto actualCard = contentScreen.findCardWithName(expectedCard.getName());
        assertThat(actualCard)
                .isEqualTo(expectedCard);
    }
}
