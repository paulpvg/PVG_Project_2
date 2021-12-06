package ru.gb;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import org.slf4j.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

public class  NimbusTest{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(NimbusTest.class);

    @BeforeAll
    static void enableDriver() {

        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setupDriver() {

        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("incognito");
        chromeoptions.addArguments("start-maximized");

        driver = new ChromeDriver(chromeoptions);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://pvg.nimbusweb.me/auth/?f=login&success=/client");
    }

    @Test
    @DisplayName("Авторизация на сайте")
    void authorizationTest() {

        WebElement webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        webElement.sendKeys("ppvg@list.ru");

        webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"password\"]"));
        webElement.sendKeys("gfhjkmgf");

        webElement = driver.findElement(By.xpath("//button/span[contains(text(),'SIGN IN')]"));
        webElement.click();

        //убийство рекламы, роняющей тест
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@style=\"display: block;\"]")));
        List<WebElement> webElementList = driver.findElements(By.xpath("//div[@style=\"display: block;\"]"));
        if(webElementList.size() >= 1){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'display: none;')",webElementList.get(0));
        }

        //проверка на успешную авторизацию
        boolean authorizationOK = false;
        webElementList = driver.findElements(By.xpath("//div[contains(text(),\"ppvg@list.ru\")]"));
        if(webElementList.size() == 1) {
            authorizationOK = true;
            logger.info(ANSI_GREEN + "Авторизация прошла успешно" + ANSI_RESET);
        }

        assertTrue(authorizationOK);
    }

    @Test
    @DisplayName("Создание новой записи")
    void newNoteCreatingTest() {

        WebElement webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        webElement.sendKeys("ppvg@list.ru");

        webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"password\"]"));
        webElement.sendKeys("gfhjkmgf");

        webElement = driver.findElement(By.xpath("//button/span[contains(text(),'SIGN IN')]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//a[contains(@class,\"button-add-circle\")]"));
        webElement.click();

        //убийство рекламы, роняющей тест
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@style=\"display: block;\"]")));
        List<WebElement> webElementList = driver.findElements(By.xpath("//div[@style=\"display: block;\"]"));
        if(webElementList.size() >= 1){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'display: none;')",webElementList.get(0));
        }

        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()=\"New note\"]")));
        webElement = driver.findElement(By.xpath("//h1[text()=\"New note\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//textarea[@placeholder=\"New note\"]"));
        webElement.sendKeys("Morbi in nisl auctor");

        webElement = driver.findElement(By.xpath("//div[@id=\"note-root\"]/div"));
        webElement.sendKeys("Lorem ipsum dolor sit amet, consectetur adipisicing elit,sheets containing Lorem" +
                            "Ipsum passages sed do, At vero eos et accusamus et iusto odio digs qui blanditiis\n");

        webElement = driver.findElement(By.xpath("//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]"));
        webElement.sendKeys("Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis" +
                            "voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\n");

        webElement = driver.findElement(By.xpath("//div[@data-menu-id=\"numberedlist\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//li[contains(@class,\"focused-block\")]"));
        webElement.sendKeys("Fugiat Quo Voluptas\nFugQui In Ea Voluptate\nAutem Vel Eum Iure\n" +
                            "Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse.\nAssumenda Est Cliche Reprehenderit\n\n");

        webElement = driver.findElement(By.xpath("//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]"));
        webElement.sendKeys("Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil" +
                            "molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\n");

        webElement = driver.findElement(By.xpath("//div[@data-menu-id=\"todo\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//li[contains(@class,\"focused-block\")]"));
        webElement.sendKeys("Aut Perferendis Doloribus\nRepreh Qui In Ea Voluptate\nMaiores Alias Consequatur\n" +
                            "Voluptatibus Maiores Alias\nDut Aut Reiciendis Maiores\nDoloribus Volupta Maiores\n\n");

        webElement = driver.findElement(By.xpath("//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]"));
        webElement.sendKeys("Donec feugiat tellus sem, laoreet iaculis orci lobortis vel. Sed sed luctus orci," +
                            "at lacinia risus. Ut porttitor ante ac tincidunt elementum. Curabitur ex dolor, condimentum vitae nunc vel, pulvinar semper justo." +
                            "Vestibulum et aliquet magna, maximus dapibus enim. Vestibulum ex lectus, posuere eu viverra at, mattis et enim. Nam efficitur sem" +
                            "et lectus fringilla, at pharetra nulla luctus. Nunc cursus, augue ac ultricies volutpat, neque erat congue justo, ac pretium tellus" +
                            "eros a neque. Integer ipsum sem, consectetur a mollis ac, cursus eu ipsum.");

        //проверка на успешное создание новой записи
        boolean newNoteCreatingOK = false;
        webElementList = driver.findElements(By.xpath("//span[text()=\"Morbi in nisl auctor\"]"));
        if(webElementList.size() >= 1) {
            newNoteCreatingOK = true;
            logger.info(ANSI_GREEN + "Новая запись успешно создана" + ANSI_RESET);
        }

        webElement = driver.findElement(By.xpath("//svg-icon[@icon=\"more\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//a[text()=\"Delete\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//span[@tooltip-message=\"Trash\"]"));
        Actions actions = new Actions(driver);
        actions.contextClick(webElement).perform();

        webElement = driver.findElement(By.xpath("//a[text()=\"Empty trash\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//a[text()=\"Delete\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//span[@tooltip-message=\"All notes\"]"));
        webElement.click();

        //проверка на успешное удаление новой записи
        boolean newNoteDeletingOK = false;
        webElementList = driver.findElements(By.xpath("//span[text()=\"Empty\"]"));
        if(webElementList.size() == 1) {
            newNoteDeletingOK = true;
            logger.info(ANSI_GREEN + "Новая запись успешно удалена" + ANSI_RESET);
        }

        assertTrue(newNoteCreatingOK && newNoteDeletingOK);
    }

    @Test
    @DisplayName("Поиск на сайте, позитивный")
    void searchPositiveTest(){

        WebElement webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        webElement.sendKeys("ppvg@list.ru");

        webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"password\"]"));
        webElement.sendKeys("gfhjkmgf");

        webElement = driver.findElement(By.xpath("//button/span[contains(text(),'SIGN IN')]"));
        webElement.click();

        //убийство рекламы, роняющей тест
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@style=\"display: block;\"]")));
        List<WebElement> webElementList = driver.findElements(By.xpath("//div[@style=\"display: block;\"]"));
        if(webElementList.size() >= 1){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'display: none;')",webElementList.get(0));
        }

        webElement = driver.findElement(By.xpath("//input[@placeholder=\"Search\"]"));
        webElement.sendKeys("питание");

        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//svg-icon[@icon=\"search\"][@class=\"ng-isolate-scope\"]")));

        webElement = driver.findElement(By.xpath("//svg-icon[@icon=\"search\"][@class=\"ng-isolate-scope\"]"));
        webElement.click();

        boolean searchPositive = false;
        boolean searchNegative = false;

        //проверка на то, что поиск отработал
        webElementList = driver.findElements(By.xpath("//span[contains(text(),\"Found notes:\")]"));
        if (webElementList.size() >= 1){
            searchPositive = true;
            logger.info(ANSI_GREEN + "Поиск успешно отработал" + ANSI_RESET);
        }

        //проверка на то, что поиск ничего не нашел
        webElementList = driver.findElements(By.xpath("//span[contains(text(),\"No results found\")]"));
        if (webElementList.size() >= 1){
            searchNegative = true;
            logger.error(ANSI_YELLOW + "По результатам поиска ничего не найдено");
        }

        assertTrue(searchPositive && !searchNegative);
    }

    @Test
    @DisplayName("Поиск на сайте, негативный")
    void searchNegativeTest(){

        WebElement webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        webElement.sendKeys("ppvg@list.ru");

        webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"password\"]"));
        webElement.sendKeys("gfhjkmgf");

        webElement = driver.findElement(By.xpath("//button/span[contains(text(),'SIGN IN')]"));
        webElement.click();

        //убийство рекламы, роняющей тест
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@style=\"display: block;\"]")));
        List<WebElement> webElementList = driver.findElements(By.xpath("//div[@style=\"display: block;\"]"));
        if(webElementList.size() >= 1){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'display: none;')",webElementList.get(0));
        }

        webElement = driver.findElement(By.xpath("//input[@placeholder=\"Search\"]"));
        webElement.sendKeys("ненаходимоеслово");

        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//svg-icon[@icon=\"search\"][@class=\"ng-isolate-scope\"]")));


        webElement = driver.findElement(By.xpath("//svg-icon[@icon=\"search\"][@class=\"ng-isolate-scope\"]"));
        webElement.click();

        boolean searchPositive = false;
        boolean searchNegative = false;

        //проверка на то, что поиск отработал
        webElementList = driver.findElements(By.xpath("//span[contains(text(),\"Found notes:\")]"));
        if (webElementList.size() >= 1){
            searchPositive = true;
            logger.info(ANSI_GREEN + "Поиск успешно отработал" + ANSI_RESET);
        }

        //проверка на то, что поиск ничего не нашел
        webElementList = driver.findElements(By.xpath("//span[contains(text(),\"No results found\")]"));
        if (webElementList.size() >= 1){
            searchNegative = true;
            logger.info(ANSI_GREEN + "По результатам поиска ничего не найдено" + ANSI_RESET);
        }

        assertTrue(searchPositive && searchNegative);
    }

    @Test
    @DisplayName("Создание новой папки")
    void newFolderCreatingTest() throws InterruptedException {

        WebElement webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        webElement.sendKeys("ppvg@list.ru");

        webElement = driver.findElement(By.xpath("//form[@method=\"POST\"]/input[@name=\"password\"]"));
        webElement.sendKeys("gfhjkmgf");

        webElement = driver.findElement(By.xpath("//button/span[contains(text(),'SIGN IN')]"));
        webElement.click();

        //убийство рекламы, роняющей тест
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@style=\"display: block;\"]")));
        List<WebElement> webElementList = driver.findElements(By.xpath("//div[@style=\"display: block;\"]"));
        if(webElementList.size() >= 1){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'display: none;')",webElementList.get(0));
        }

        webElement = driver.findElement(By.xpath("//svg-icon[@tooltip-message=\"Create folder\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//input[@name=\"folderTitle\"]"));
        webElement.sendKeys("Test folder");

        new WebDriverWait(driver, 5).until(ExpectedConditions.not(ExpectedConditions.attributeContains(By.xpath("//a[text()=\"Create\"]"), "disabled", "disabled"))); //Без этого иногда кнопка не успевает стать активной

        webElement = driver.findElement(By.xpath("//a[text()=\"Create\"]"));
        webElement.click();

        //проверка на успешное создание новой папки
        boolean newFolderCreatingOK = false;
        webElementList = driver.findElements(By.xpath("//span[@tooltip-message=\"Test folder\"]"));
        if (webElementList.size() >= 1){
            newFolderCreatingOK = true;
            logger.info(ANSI_GREEN + "Новая папка создана успешно" + ANSI_RESET);
            Thread.sleep(2000); //хоть пару секунд полюбуемся на созданную папку, пред тем как убить ее
        }

        webElement = driver.findElement(By.xpath("//span[@tooltip-message=\"Test folder\"]"));
        Actions actions = new Actions(driver);
        actions.contextClick(webElement).perform();

        webElement = driver.findElement(By.xpath("//a[text()=\"Delete folder\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//a[text()=\"Yes\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//span[@tooltip-message=\"Trash\"]"));
        actions = new Actions(driver);
        actions.contextClick(webElement).perform();

        webElement = driver.findElement(By.xpath("//a[text()=\"Empty trash\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//a[text()=\"Delete\"]"));
        webElement.click();

        webElement = driver.findElement(By.xpath("//span[@tooltip-message=\"My Notes\"]"));
        webElement.click();

        boolean newFolderDeletingOK = false;
        webElementList = driver.findElements(By.xpath("//span[text()=\"Empty\"]"));
        if (webElementList.size() >= 1){
            newFolderDeletingOK = true;
            logger.info(ANSI_GREEN + "Новая папка успешно удалена" + ANSI_RESET);
        }

        assertTrue(newFolderCreatingOK && newFolderDeletingOK);
    }

    @AfterEach
    void releaseDriver() throws InterruptedException {
        if(driver != null) {
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
