package ru.gb;

import org.openqa.selenium.*;

public class LoginPage {

    private WebDriver driver;
    private WebElement login;
    private WebElement password;
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        login = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        password = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"password\"]"));
        submit = driver.findElement(By.xpath("//button/span[contains(text(),'SIGN IN')]"));
    }

    public void loginIn(String login, String password) {
        this.login.sendKeys(login);
        this.password.sendKeys(password);
        submit.click();
    }
}
