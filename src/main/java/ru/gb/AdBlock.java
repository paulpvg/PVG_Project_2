package ru.gb;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

//Рекламный блок, рушащий тесты
public class AdBlock {

    private WebDriver driver;
    private String closeControlXpath;
    private List<WebElement> closeControl;


    public AdBlock(WebDriver driver) {
        this.driver = driver;
        closeControlXpath = "//div[@style=\"display: block;\"]";
        closeControl = driver.findElements(By.xpath("//div[@style=\"display: block;\"]"));
    }

    public void killAdBlock() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath(closeControlXpath))); // .visibilityOf(WebElement) не срабатывает, пришлось так ловить
        if (this.closeControl.size() >=1){
            JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].setAttribute('style', 'display: none;')",closeControl.get(0));
        }
    }
}
