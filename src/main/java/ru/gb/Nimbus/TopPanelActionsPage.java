package ru.gb.Nimbus;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

//Верхняя панель - меню
public class TopPanelActionsPage extends AbstractPage {

    @FindBy(xpath = "//svg-icon[@icon=\"more\"]")
    private WebElement moreButton;

    @FindBy(xpath = "//a[text()=\"Delete\"]")
    private WebElement deleteButton;

    public TopPanelActionsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Удаление созданной новой записи")
    public void deleteNote() {
        moreButton.click();
        deleteButton.click();
    }
}
