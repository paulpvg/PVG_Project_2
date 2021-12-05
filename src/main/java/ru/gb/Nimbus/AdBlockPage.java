package ru.gb.Nimbus;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import io.qameta.allure.Step;

//Рекламный блок, рушащий тесты
public class AdBlockPage extends AbstractPage {

    private String closeControlXpath = "//div[@style=\"display: block;\"]";

    @FindBy(xpath = "//div[@style=\"display: block;\"]")
    private List<WebElement> closeControl;


    public AdBlockPage(WebDriver driver) {
        super(driver);
    }

    @Step("Убийство рекламного блока")
    public void killAdBlock() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(closeControlXpath))); // .visibilityOf(WebElement) не срабатывает, пришлось так ловить
        if (this.closeControl.size() >=1) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
                js.executeScript("arguments[0].setAttribute('style', 'display: none;')",closeControl.get(0));
        }
    }
}
