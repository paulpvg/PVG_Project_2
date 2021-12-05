package ru.gb.Nimbus;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

//Область страницы с телом заметки
public class NoteBodyPage extends AbstractPage {

    @FindBy(xpath = "//h1[text()=\"New note\"]")
    private WebElement headerControl;
    @FindBy(xpath = "//textarea[@placeholder=\"New note\"]")
    private WebElement headerInput;

    @FindBy(xpath = "//div[@id=\"note-root\"]/div")
    private WebElement bodyFirstInput;
    @FindBy(xpath = "//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]")
    private WebElement bodyNextInput;

    @FindBy(xpath = "//div[@data-menu-id=\"numberedlist\"]")
    private WebElement bodyNumberedlistControl;
    @FindBy(xpath = "//li[contains(@class,\"focused-block\")]")
    private WebElement bodyNumberedlistInput;

    @FindBy(xpath = "//div[@data-menu-id=\"todo\"]")
    private WebElement bodyTodoControl;
    @FindBy(xpath = "//li[contains(@class,\"focused-block\")]")
    private WebElement bodyTodoInput;

    @FindBy(xpath = "//p[text()=\"Loading...\"]")
    private WebElement loading;

    public NoteBodyPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод заголовка")
    public NoteBodyPage inputHeader(String header) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(headerControl));
        headerControl.click();
        headerInput.sendKeys(header);
        return this;
    }

    @Step("Ввод первого абзаца текста")
    public NoteBodyPage inputFirstText(String text) {
        bodyFirstInput.sendKeys(text);
        return this;
    }

    @Step("Ввод последующего абзаца текста")
    public NoteBodyPage inputNextText(String text) {
        bodyNextInput.sendKeys(text);
        return this;
    }

    @Step("Ввод нумерованного списка")
    public NoteBodyPage inputNumberedlist(String text) {
        bodyNumberedlistControl.click();
        bodyNumberedlistInput.sendKeys(text);
        return this;
    }

    @Step("Ввод перечня задач")
    public NoteBodyPage inputTodo(String text) {
        bodyTodoControl.click();
        bodyTodoInput.sendKeys(text);
        return this;
    }
}
