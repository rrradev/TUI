package com.tui.screens;

import com.tui.entities.ContentCardDto;
import com.tui.screens.components.TopBar;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.tui.screens.components.ContentCard.*;

@Getter
public class ContentScreen extends BaseScreen {

    TopBar topBar;

    public ContentScreen(AndroidDriver driver) {
        super(driver);
        this.topBar = new TopBar(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public boolean isDisplayed() {
        return this.topBar.isDisplayed();
    }


    public ContentCardDto findCardWithName(String name) {
        WebElement contentCardElementWithName = findContentCardElementWithName(name);

        return ContentCardDto.builder()
                .name(name)
                .destination(contentCardElementWithName.findElement(DESTINATION).getText())
                .rating(contentCardElementWithName.findElement(RATING).getText())
                .type(contentCardElementWithName.findElement(TYPE).getText())
                .price(contentCardElementWithName.findElement(PRICE).getText())
                .build();
    }

    public List<WebElement> getContentCardsDisplayed() {
        return driver.findElements(ROOT);
    }

    private WebElement findContentCardElementWithName(String name) {
        long startTime = System.currentTimeMillis();
        String prevTopCardName = "";

        while (System.currentTimeMillis() - startTime < SCROLL_TIMEOUT.toMillis()) {
            List<WebElement> contentCards = getContentCardsDisplayed();

            if (contentCards.isEmpty()) {
                continue;
            }

            WebElement topCard = contentCards.get(0);
            String topCardName = topCard.findElement(NAME).getText();

            if (topCardName.equals(name)) {
                return topCard;
            }

            if (contentCards.size() > 1) {
                if (topCardName.equals(prevTopCardName)) {
                    String secondCardName = contentCards.get(1)
                            .findElement(NAME)
                            .getText();

                    if (secondCardName.equals(name)) {
                        return contentCards.get(1);
                    }
                } else {
                    scroll(contentCards.get(1), topCard);
                    prevTopCardName = topCardName;
                }
            }
        }

        throw new IllegalArgumentException("Could not find content card with name: " + name);
    }


}
