package ru.gb;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;

import org.slf4j.*;

import java.util.List;

public class Sidebar {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(Sidebar.class);

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

    public Sidebar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        loginControlList.get(0).click();
        logoutButton.click();
    }

    public void emptyTrash() {
        Actions actions = new Actions(driver);
        actions.contextClick(trashControl).perform();

        emptyTrashButton.click();
        deleteButton.click();
        allNotesControl.click();
    }

    public void creatingNewFolder() {
        folderCreateButton.click();
        folderInputName.sendKeys("Test folder");
        createButton.click();
    }

    public void deletingNewFolder() {
        Actions actions = new Actions(driver);
        actions.contextClick(testFolderControlList.get(0)).perform();

        folderDeleteButton.click();
        yesButton.click();
    }

    public boolean creatingNewFolderOk() throws InterruptedException {
        if (testFolderControlList.size() >= 1){
            logger.info(ANSI_GREEN + "Новая папка успешно создана" + ANSI_RESET);
            Thread.sleep(2000); //хоть пару секунд полюбуемся на созданную папку, пред тем как убить ее
            return true;
        }
        else {
            logger.error(ANSI_RED + "Не удалось создать новую папку" + ANSI_RESET);
            return false;
        }
    }

    public boolean loginOk() {
        if (loginControlList.size() >= 1){
            logger.info(ANSI_GREEN + "Успешно залогинились" + ANSI_RESET);
            return true;
        }
        else {
            logger.error(ANSI_RED + "Не удалось залогиниться" + ANSI_RESET);
            return false;
        }
    }

    public boolean emptyTrashOk() {
        if (emptyLabelList.size() >= 1){
            logger.info(ANSI_GREEN + "Корзина пуста. Весь тестовый мусор удален" + ANSI_RESET);
            return true;
        }
        else {
            logger.error(ANSI_RED + "Не удалось очистить корзину" + ANSI_RESET);
            return false;
        }
    }
}
