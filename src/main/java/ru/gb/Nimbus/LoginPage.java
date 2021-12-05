package ru.gb.Nimbus;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

//Стартовая страница авторизации
public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//form[@method=\"POST\"]/input[@name=\"login\"]")
    private WebElement login;
    @FindBy(xpath = "//form[@method=\"POST\"]/input[@name=\"password\"]")
    private WebElement password;
    @FindBy(xpath = "//button/span[contains(text(),'SIGN IN')]")
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод логина и пароля")
    public void loginIn(String login, String password) {
        this.login.sendKeys(login);
        this.password.sendKeys(password);
        submit.click();
    }
}
