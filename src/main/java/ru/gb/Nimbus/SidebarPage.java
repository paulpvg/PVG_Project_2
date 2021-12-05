package ru.gb.Nimbus;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import ru.gb.*;

import java.time.Duration;
import java.util.List;

//Боковое меню
public class SidebarPage extends AbstractPage {

    @FindBy(xpath = "//div[contains(text(),\"ppvg@list.ru\")]")
    private List<WebElement> loginControlList;
    @FindBy(xpath = "//div[contains(text(),\"Log out\")]")
    private WebElement logoutButton;

    @FindBy(xpath = "//span[@tooltip-message=\"All notes\"]")
    private WebElement allNotesControl;

    @FindBy(xpath = "//svg-icon[@tooltip-message=\"Create folder\"]")
    private WebElement folderCreateButton;
    @FindBy(xpath = "//input[@name=\"folderTitle\"]")
    private WebElement folderInputName;
    @FindBy(xpath = "//a[text()=\"Create\"]")
    private WebElement createButton;
    @FindBy(xpath = "//span[@tooltip-message=\"Test folder\"]")
    private List<WebElement> testFolderControlList;
    @FindBy(xpath = "//a[text()=\"Delete folder\"]")
    private WebElement folderDeleteButton;
    @FindBy(xpath = "//a[text()=\"Yes\"]")
    private WebElement yesButton;

    @FindBy(xpath = "//span[@tooltip-message=\"Trash\"]")
    private WebElement trashControl;
    @FindBy(xpath = "//a[text()=\"Empty trash\"]")
    private WebElement emptyTrashButton;
    @FindBy(xpath = "//a[text()=\"Delete\"]")
    private WebElement deleteButton;
    @FindBy(xpath = "//span[text()=\"Empty\"]")
    private List<WebElement> emptyLabelList;

    @FindBy(xpath = "//p[text()=\"Loading...\"]")
    private WebElement loading;

    public SidebarPage(WebDriver driver) {
        super(driver);
    }

    @Step("Очистка корзины")
    public void emptyTrash() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(trashControl));

        Actions actions = new Actions(getDriver());
        actions.contextClick(trashControl).perform();

        emptyTrashButton.click();
        deleteButton.click();
        allNotesControl.click();
    }

    @Step("Создание новой папки")
    public void creatingNewFolder() {
        folderCreateButton.click();
        folderInputName.sendKeys("Test folder");

        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.not(ExpectedConditions.attributeContains(createButton, "disabled", "disabled"))); //Без этого иногда кнопка не успевала становиться активной

        createButton.click();
    }

    @Step("Удаление созданной папки")
    public void deletingNewFolder() {
        Actions actions = new Actions(getDriver());
        actions.contextClick(testFolderControlList.get(0)).perform();

        folderDeleteButton.click();
        yesButton.click();
    }

    @Step("Проверка на успешность создания новой папки")
    public boolean creatingNewFolderOk() throws InterruptedException {
        if (testFolderControlList.size() >= 1) {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-creatingNewFolderOk.png");
            Thread.sleep(2000); //хоть пару секунд полюбуемся на созданную папку, пред тем как убить ее
            return true;
        }
        else {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-creatingNewFolderOk_Error.png");
            return false;
        }
    }

    @Step("Проверка на успешную аторизацию")
    public boolean loginOk() {
        if (loginControlList.size() >= 1){
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-loginOk.png");
            return true;
        }
        else {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-loginOk_Error.png");
            return false;
        }
    }

    @Step("Проверка на успешную очистку корзины")
    public boolean emptyTrashOk() {
        if (emptyLabelList.size() >= 1) {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-emptyTrashOk.png");
            return true;
        }
        else {
            ScreenshotMaker.makeScreenshot(getDriver(), FileName.getFilename() + "-emptyTrashOk_Error.png");
            return false;
        }
    }
}
