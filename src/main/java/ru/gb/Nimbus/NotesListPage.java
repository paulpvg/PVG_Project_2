package ru.gb.Nimbus;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import ru.gb.FileName;
import ru.gb.ScreenshotMaker;

import java.time.Duration;
import java.util.List;

//Область страницы с перечнем заметок и поиском
public class NotesListPage extends AbstractPage {

    @FindBy(xpath = "//a[contains(@class,\"button-add-circle\")]")
    private WebElement newNoteButton;

    @FindBy(xpath = "//span[text()=\"Morbi in nisl auctor\"]")
    private List<WebElement> newNotePresentList;

    @FindBy(xpath = "//input[@placeholder=\"Search\"]")
    private WebElement searchInput;
    @FindBy(xpath = "//svg-icon[@icon=\"search\"][@class=\"ng-isolate-scope\"]")
    private WebElement searchButton;

    @FindBy(xpath = "//span[contains(text(),\"Found notes:\")]")
    private List<WebElement> searchPositiveList;
    @FindBy(xpath = "//span[contains(text(),\"No results found\")]")
    private List<WebElement> searchNegativeList;

    public NotesListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Создание новой заметки")
    public void creatingNewNote() {
        newNoteButton.click();
    }

    @Step("Поиск")
    public void search(String text) {
        searchInput.sendKeys(text);
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
    }

    @Step("Проверка на успешность создания новой записи")
    public boolean creatingNewNoteOk() {
        if (newNotePresentList.size() >= 1) {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-creatingNewNoteOk.png");
            return true;
        }
        else {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-creatingNewNoteOk_Error.png");
            return false;
        }
    }

    @Step("Проверка на работу поиска в позитивном тесте")
    public boolean searchPositiveOk() {
        if (searchPositiveList.size() >= 1) {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-searchPositiveOk.png");
            return true;
        }
        else {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-searchPositiveOk_Error.png");
            return false;
        }
    }

    @Step("Проверка на работу поиска в негативном тесте")
    public boolean searchNegativeOk() {
        if (searchNegativeList.size() >= 1) {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-searchNegativeOk.png");
            return true;
        }
        else {
            return false;
        }
    }
}
