package ru.gb;

import org.openqa.selenium.*;
import java.util.List;

public class AdBlock {

    private WebDriver driver;
    private List<WebElement> closeControl;

    public AdBlock(WebDriver driver) {
        this.driver = driver;
        closeControl = driver.findElements(By.xpath("//div[@style=\"display: block;\"]"));
    }

    public void killAdBlock() {
        if (this.closeControl.size() >=1){
            JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].setAttribute('style', 'display: none;')",closeControl.get(0));
        }
    }
}
