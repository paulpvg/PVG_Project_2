package ru.gb;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

//Область страницы с телом записи
public class NoteBody {

    private WebDriver driver;

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

    public NoteBody(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NoteBody inputHeader(String header) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(headerControl));
        headerControl.click();
        headerInput.sendKeys(header);

        return this;
    }

    public NoteBody inputFirstText(String text) {
        bodyFirstInput.sendKeys(text);

        return this;
    }

    public NoteBody inputNextText(String text) {
        bodyNextInput.sendKeys(text);

        return this;
    }

    public NoteBody inputNumberedlist(String text) {
        bodyNumberedlistControl.click();
        bodyNumberedlistInput.sendKeys(text);

        return this;
    }

    public NoteBody inputTodo(String text) {
        bodyTodoControl.click();
        bodyTodoInput.sendKeys(text);

        return this;
    }
}
