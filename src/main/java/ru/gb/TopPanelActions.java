package ru.gb;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

//Верхняя панель - меню
public class TopPanelActions {

    private WebDriver driver;

    @FindBy(xpath = "//svg-icon[@icon=\"more\"]")
    private WebElement moreButton;

    @FindBy(xpath = "//a[text()=\"Delete\"]")
    private WebElement deleteButton;

    public TopPanelActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void deleteNote() {
        moreButton.click();
        deleteButton.click();
    }
}
