package com.tui.screens.components;

import org.openqa.selenium.By;

public class ContentCard {

    public static final By ROOT = By.xpath("//*[starts-with(@resource-id, 'content_card_root')]");
    public static final By NAME = By.xpath(".//*[starts-with(@resource-id, 'content_card_hotel_name')]");
    public static final By DESTINATION = By.xpath(".//*[starts-with(@resource-id, 'content_card_destination')]");
    public static final By RATING = By.xpath(".//*[starts-with(@resource-id, 'content_card_rating')]");
    public static final By TYPE = By.xpath(".//*[starts-with(@resource-id, 'content_card_board_type')]");
    public static final By PRICE = By.xpath(".//*[starts-with(@resource-id, 'content_card_price_button')]/android.widget.TextView");

}
