package ru.gb;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.*;

import java.util.List;

//Область страницы с перечнем записей и поиском
public class NotesList {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(Sidebar.class);

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

    public NotesList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void creatingNewNote() {
        newNoteButton.click();
    }

    public void search(String text) {
        searchInput.sendKeys(text);
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
    }

    public boolean creatingNewNoteOk() {
        if (newNotePresentList.size() >= 1){
            logger.info(ANSI_GREEN + "Новая запись успешно создана" + ANSI_RESET);
            return true;
        }
        else {
            logger.error(ANSI_RED + "Не удалось создать новую запись" + ANSI_RESET);
            return false;
        }
    }

    public boolean searchPositiveOk() {
        if (searchPositiveList.size() >= 1){
            logger.info(ANSI_GREEN + "Поиск успешно отработал" + ANSI_RESET);
            return true;
        }
        else return false;
    }

    public boolean searchNegativeOk(){
        if (searchNegativeList.size() >= 1){
            logger.info(ANSI_GREEN + "По результатам поиска ничего не найдено" + ANSI_RESET);
            return true;
        }
        else return false;
    }
}
