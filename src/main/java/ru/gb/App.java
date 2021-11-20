package ru.gb;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class App {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) throws InterruptedException {

        System.setProperty(
                "webdriver.chrome.driver", "src/main/resources/chromedriver.exe"
        );

        System.out.println("--------------------------------------------------------------------------------------------\n" +
                "        Выполнение Тест-кейс №1. Авторизация на сайте Nimbus.\n" +
                "--------------------------------------------------------------------------------------------\n");

        if (Authorization()) {
            System.out.println(ANSI_GREEN + "Тест авторизации на сайте Nimbus пройден успешно\n" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Тест авторизации на сайте Nimbus не пройден\n" + ANSI_RESET);
        }

        System.out.println("--------------------------------------------------------------------------------------------\n" +
                "        Выполнение Тест-кейс №2. Создание новой записи.\n" +
                "--------------------------------------------------------------------------------------------\n");

        if (NewNoteCreating()) {
            System.out.println(ANSI_GREEN + "Тест на создание новой записи пройден успешно\n" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Тест на создание новой записи не пройден\n" + ANSI_RESET);
        }

    }

    public static boolean Authorization() throws InterruptedException {

        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("incognito");
        chromeoptions.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(chromeoptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://pvg.nimbusweb.me/auth/?f=login&success=/client");

        System.out.println("\nПрохождение теста:\n");

        boolean stepOk = true;

        //Step 01: type xpath=//form[@method="POST"]/input[@name="login"]
        List<WebElement> webElementListStep01 = driver.findElements(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        if(stepOk && webElementListStep01.size() == 1) {
            webElementListStep01.get(0).sendKeys("ppvg@list.ru");
            System.out.println("Step 01: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Локатор найден, шаг теста: " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep01.size() > 0){
            System.out.println("Step 01: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else  if (stepOk && webElementListStep01.size() == 0){
            System.out.println("Step 01: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 02: type xpath=//form[@method="POST"]/input[@name="password"]
        List<WebElement> webElementListStep02 = driver.findElements(By.xpath("//form[@method=\"POST\"]/input[@name=\"password\"]"));
        if(stepOk && webElementListStep02.size() == 1) {
            webElementListStep02.get(0).sendKeys("gfhjkmgf");
            System.out.println("Step 02: xpath=//form[@method=\"POST\"]/input[@name=\"password\"] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep02.size() > 0){
            System.out.println("Step 02: xpath=//form[@method=\"POST\"]/input[@name=\"password\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep02.size() == 0) {
            System.out.println("Step 02: xpath=//form[@method=\"POST\"]/input[@name=\"password\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 03: click xpath=//button/span[contains(text(),'SIGN IN')]
        List<WebElement> webElementListStep03 = driver.findElements(By.xpath("//button/span[contains(text(),'SIGN IN')]"));
        if(stepOk && webElementListStep03.size() == 1) {
            webElementListStep03.get(0).click();
            System.out.println("Step 03: xpath=//button/span[contains(text(),'SIGN IN')] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep03.size() > 0){
            System.out.println("Step 03: xpath=//button/span[contains(text(),'SIGN IN')] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep03.size() == 0){
            System.out.println("Step 03: xpath=//button/span[contains(text(),'SIGN IN')] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 04: find xpath=//div[contains(text(),"ppvg@list.ru")]
        boolean authorizationOK = false;
        List<WebElement> webElementListStep04 = driver.findElements(By.xpath("//div[contains(text(),\"ppvg@list.ru\")]"));
        if(stepOk && webElementListStep04.size() == 1) {
            System.out.println("Step 04: xpath=//div[contains(text(),\"ppvg@list.ru\")] - Локатор найден, авторизация прошла: " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            authorizationOK = true;
            System.out.println(ANSI_CYAN + "\nАвторизация прошла успешно. " + ANSI_RESET + "Теперь можно и разлогиниться\n");
            System.out.println("Выдержим паузу в 5 с и разлогиниваемся\n");
            Thread.sleep(5000);
        }
        else if(stepOk && webElementListStep04.size() > 0){
            System.out.println("Step 04: xpath=//div[contains(text(),\"ppvg@list.ru\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep04.size() == 0){
            System.out.println("Step 04: xpath=//div[contains(text(),\"ppvg@list.ru\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 05: click Xpath=//div[contains(text(),"ppvg@list.ru")]
        List<WebElement> webElementListStep05 = driver.findElements(By.xpath("//div[contains(text(),\"ppvg@list.ru\")]"));
        if(stepOk && webElementListStep05.size() == 1) {
            webElementListStep05.get(0).click();
            System.out.println("Step 05: xpath=//div[contains(text(),\"ppvg@list.ru\")] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep05.size() > 0){
            System.out.println("Step 05: xpath=//div[contains(text(),\"ppvg@list.ru\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep05.size() == 0){
            System.out.println("Step 05: xpath=//div[contains(text(),\"ppvg@list.ru\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 06: click xpath=//div[contains(text(),"Log out")]
        List<WebElement> webElementListStep06 = driver.findElements(By.xpath("//div[contains(text(),\"Log out\")]"));
        if(stepOk && webElementListStep06.size() == 1) {
            webElementListStep06.get(0).click();
            System.out.println("Step 06: xpath=//div[contains(text(),\"Log out\")] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep06.size() > 0){
            System.out.println("Step 06: xpath=//div[contains(text(),\"Log out\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep06.size() == 0){
            System.out.println("Step 06: xpath=//div[contains(text(),\"Log out\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 07: find xpath=//form[@method="POST"]/input[@name="login"]
        boolean logOutOK = false;
        List<WebElement> webElementListStep07 = driver.findElements(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        if(stepOk && webElementListStep07.size() == 1) {
            System.out.println("Step 07: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Локатор найден, логаут прошел: " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            logOutOK = true;
            System.out.println(ANSI_CYAN + "\nУспешно разлогинились. " + ANSI_RESET + "До новых встреч!!!\n");
        }
        else if(stepOk && webElementListStep07.size() > 0){
            System.out.println("Step 07: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep07.size() == 0){
            System.out.println("Step 07: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        Thread.sleep(2000);
        driver.quit();

        if(authorizationOK && logOutOK){
            System.out.println("Все шаги теста пройдены успешно " + ANSI_GREEN + ":)\n" + ANSI_RESET);
            return true;
        }
        else{
            System.out.println("Не сложилось... " + ANSI_RED + ":(\n" + ANSI_RESET);
            return false;
        }
    }

    public static boolean NewNoteCreating() throws InterruptedException {

        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("incognito");
        chromeoptions.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(chromeoptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://pvg.nimbusweb.me/auth/?f=login&success=/client");

        System.out.println("\nПрохождение теста:\n");

        boolean stepOk = true;

        //Step 01: type xpath=//form[@method="POST"]/input[@name="login"]
        List<WebElement> webElementListStep01 = driver.findElements(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        if(stepOk && webElementListStep01.size() == 1) {
            webElementListStep01.get(0).sendKeys("ppvg@list.ru");
            System.out.println("Step 01: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Локатор найден, шаг теста: " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep01.size() > 0){
            System.out.println("Step 01: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else  if (stepOk && webElementListStep01.size() == 0){
            System.out.println("Step 01: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 02: type xpath=//form[@method="POST"]/input[@name="password"]
        List<WebElement> webElementListStep02 = driver.findElements(By.xpath("//form[@method=\"POST\"]/input[@name=\"password\"]"));
        if(stepOk && webElementListStep02.size() == 1) {
            webElementListStep02.get(0).sendKeys("gfhjkmgf");
            System.out.println("Step 02: xpath=//form[@method=\"POST\"]/input[@name=\"password\"] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep02.size() > 0){
            System.out.println("Step 02: xpath=//form[@method=\"POST\"]/input[@name=\"password\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep02.size() == 0) {
            System.out.println("Step 02: xpath=//form[@method=\"POST\"]/input[@name=\"password\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 03: click xpath=//button/span[contains(text(),'SIGN IN')]
        List<WebElement> webElementListStep03 = driver.findElements(By.xpath("//button/span[contains(text(),'SIGN IN')]"));
        if(stepOk && webElementListStep03.size() == 1) {
            webElementListStep03.get(0).click();
            System.out.println("Step 03: xpath=//button/span[contains(text(),'SIGN IN')] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep03.size() > 0){
            System.out.println("Step 03: xpath=//button/span[contains(text(),'SIGN IN')] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep03.size() == 0){
            System.out.println("Step 03: xpath=//button/span[contains(text(),'SIGN IN')] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 04: click xpath=//a[contains(@class,"button-add-circle")]
        List<WebElement> webElementListStep04 = driver.findElements(By.xpath("//a[contains(@class,\"button-add-circle\")]"));
        if(stepOk && webElementListStep04.size() == 1) {
            webElementListStep04.get(0).click();
            System.out.println("Step 04: xpath=//a[contains(@class,\"button-add-circle\")] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep04.size() > 0){
            System.out.println("Step 04: xpath=//a[contains(@class,\"button-add-circle\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep04.size() == 0){
            System.out.println("Step 04: xpath=//a[contains(@class,\"button-add-circle\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 05: click xpath=//h1[text()="New note"]
        Thread.sleep(2000);
        List<WebElement> webElementListStep05 = driver.findElements(By.xpath("//h1[text()=\"New note\"]"));
        if(stepOk && webElementListStep05.size() == 1) {
            webElementListStep05.get(0).click();
//            webElementListStep05.get(0).sendKeys("Новая запись для автотеста");
            System.out.println("Step 05: xpath=//h1[text()=\"New note\"] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep05.size() > 0){
            System.out.println("Step 05: xpath=//h1[text()=\"New note\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep05.size() == 0){
            System.out.println("Step 05: xpath=//h1[text()=\"New note\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 06: type xpath=//textarea[@placeholder="New note"]
        List<WebElement> webElementListStep06 = driver.findElements(By.xpath("//textarea[@placeholder=\"New note\"]"));
        if(stepOk && webElementListStep06.size() == 1) {
            webElementListStep06.get(0).sendKeys("Morbi in nisl auctor");
            System.out.println("Step 06: xpath=//textarea[@placeholder=\"New note\"] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep06.size() > 0){
            System.out.println("Step 06: xpath=//textarea[@placeholder=\"New note\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep06.size() == 0) {
            System.out.println("Step 06: xpath=//textarea[@placeholder=\"New note\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 07: type xpath=//div[@id="note-root"]/div
        List<WebElement> webElementListStep07 = driver.findElements(By.xpath("//div[@id=\"note-root\"]/div"));
        if(stepOk && webElementListStep07.size() == 1) {
            webElementListStep07.get(0).sendKeys("Lorem ipsum dolor sit amet, consectetur adipisicing elit,sheets containing Lorem " +
                    "Ipsum passages sed do, At vero eos et accusamus et iusto odio digs qui blanditiis\n");
            System.out.println("Step 07: xpath=//div[@id=\"note-root\"]/div - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep07.size() > 0){
            System.out.println("Step 07: xpath=//div[@id=\"note-root\"]/div - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep07.size() == 0) {
            System.out.println("Step 07: xpath=//div[@id=\"note-root\"]/div - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 08: type xpath=//div[@id="note-root"]/div[contains(@class, "focused-block")]
        List<WebElement> webElementListStep08 = driver.findElements(By.xpath("//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]"));
        if(stepOk && webElementListStep08.size() == 1) {
            webElementListStep08.get(0).sendKeys("Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis " +
                    "voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\n");
            System.out.println("Step 08: xpath=//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]- Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep08.size() > 0){
            System.out.println("Step 08: xpath=//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep08.size() == 0) {
            System.out.println("Step 08: xpath=//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 09: click xpath=//div[@data-menu-id="numberedlist"]
        List<WebElement> webElementListStep09 = driver.findElements(By.xpath("//div[@data-menu-id=\"numberedlist\"]"));
        if(stepOk && webElementListStep09.size() == 1) {
            webElementListStep09.get(0).click();
            System.out.println("Step 09: xpath=//div[@data-menu-id=\"numberedlist\"]- Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep09.size() > 0){
            System.out.println("Step 09: xpath=//div[@data-menu-id=\"numberedlist\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep09.size() == 0) {
            System.out.println("Step 09: xpath=//div[@data-menu-id=\"numberedlist\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 10: type xpath=//li[contains(@class,"focused-block")]
        List<WebElement> webElementListStep10 = driver.findElements(By.xpath("//li[contains(@class,\"focused-block\")]"));
        if(stepOk && webElementListStep10.size() == 1) {
            webElementListStep10.get(0).sendKeys("Fugiat Quo Voluptas\nFugQui In Ea Voluptate\nAutem Vel Eum Iure\n" +
                    "Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse.\nAssumenda Est Cliche Reprehenderit\n\n");
            System.out.println("Step 10: xpath=//li[contains(@class,\"focused-block\")]- Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep10.size() > 0){
            System.out.println("Step 10: xpath=//li[contains(@class,\"focused-block\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep10.size() == 0) {
            System.out.println("Step 10: xpath=//li[contains(@class,\"focused-block\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 11: type xpath=//div[@id="note-root"]/div[contains(@class, "focused-block")]
        List<WebElement> webElementListStep11 = driver.findElements(By.xpath("//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]"));
        if(stepOk && webElementListStep11.size() == 1) {
            webElementListStep11.get(0).sendKeys("Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil " +
                    "molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\n");
            System.out.println("Step 11: xpath=//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]- Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep11.size() > 0){
            System.out.println("Step 11: xpath=//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep11.size() == 0) {
            System.out.println("Step 11: xpath=//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 12: click xpath=//div[@data-menu-id="todo"]
        List<WebElement> webElementListStep12 = driver.findElements(By.xpath("//div[@data-menu-id=\"todo\"]"));
        if(stepOk && webElementListStep12.size() == 1) {
            webElementListStep12.get(0).click();
            System.out.println("Step 12: xpath=//div[@data-menu-id=\"todo\"]- Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep12.size() > 0){
            System.out.println("Step 12: xpath=//div[@data-menu-id=\"todo\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep12.size() == 0) {
            System.out.println("Step 12: xpath=//div[@data-menu-id=\"todo\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 13: type xpath=//li[contains(@class,"focused-block")]
        List<WebElement> webElementListStep13 = driver.findElements(By.xpath("//li[contains(@class,\"focused-block\")]"));
        if(stepOk && webElementListStep13.size() == 1) {
            webElementListStep13.get(0).sendKeys("Aut Perferendis Doloribus\nRepreh Qui In Ea Voluptate\nMaiores Alias Consequatur\n" +
                    "Voluptatibus Maiores Alias\nDut Aut Reiciendis Maiores\nDoloribus Volupta Maiores\n\n");
            System.out.println("Step 13: xpath=//li[contains(@class,\"focused-block\")]- Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep13.size() > 0){
            System.out.println("Step 13: xpath=//li[contains(@class,\"focused-block\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep13.size() == 0) {
            System.out.println("Step 13: xpath=//li[contains(@class,\"focused-block\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 14: type xpath=//div[@id="note-root"]/div[contains(@class, "focused-block")]
        List<WebElement> webElementListStep14 = driver.findElements(By.xpath("//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]"));
        if(stepOk && webElementListStep14.size() == 1) {
            webElementListStep14.get(0).sendKeys("Donec feugiat tellus sem, laoreet iaculis orci lobortis vel. Sed sed luctus orci, " +
                    "at lacinia risus. Ut porttitor ante ac tincidunt elementum. Curabitur ex dolor, condimentum vitae nunc vel, pulvinar semper justo. " +
                    "Vestibulum et aliquet magna, maximus dapibus enim. Vestibulum ex lectus, posuere eu viverra at, mattis et enim. Nam efficitur sem " +
                    "et lectus fringilla, at pharetra nulla luctus. Nunc cursus, augue ac ultricies volutpat, neque erat congue justo, ac pretium tellus " +
                    "eros a neque. Integer ipsum sem, consectetur a mollis ac, cursus eu ipsum.\n");
            System.out.println("Step 14: xpath=//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")]- Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep14.size() > 0){
            System.out.println("Step 14: xpath=//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep14.size() == 0) {
            System.out.println("Step 14: xpath=//div[@id=\"note-root\"]/div[contains(@class, \"focused-block\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 15: find xpath=//span[text()="Morbi in nisl auctor"]
        boolean newNoteCreatingOK = false;
        List<WebElement> webElementListStep15 = driver.findElements(By.xpath("//span[text()=\"Morbi in nisl auctor\"]"));
        if(stepOk && webElementListStep15.size() >= 1) {
            System.out.println("Step 15: xpath=//span[text()=\"Morbi in nisl auctor\"] - Локатор найден, запись создана: " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            newNoteCreatingOK = true;
            System.out.println(ANSI_CYAN + "\nНовая запись успешно создана. " + ANSI_RESET + "Полюбовались на новую запись, теперь можно и удалить ее\n");
            System.out.println("Выдержим паузу в 5 с и удаляем созданную запись\n");
            Thread.sleep(5000);
        }
        else if (stepOk && webElementListStep15.size() == 0){
            System.out.println("Step 15: xpath=//span[text()=\"Morbi in nisl auctor\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 16: click xpath=//svg-icon[@icon="more"]
        List<WebElement> webElementListStep16 = driver.findElements(By.xpath("//svg-icon[@icon=\"more\"]"));
        if(stepOk && webElementListStep16.size() == 1) {
            webElementListStep16.get(0).click();
            System.out.println("Step 16: xpath=//svg-icon[@icon=\"more\"]- Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep16.size() > 0){
            System.out.println("Step 16: xpath=//svg-icon[@icon=\"more\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep16.size() == 0) {
            System.out.println("Step 16: xpath=//svg-icon[@icon=\"more\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 17: click xpath=//a[text()="Delete"]
        List<WebElement> webElementListStep17 = driver.findElements(By.xpath("//a[text()=\"Delete\"]"));
        if(stepOk && webElementListStep17.size() == 1) {
            webElementListStep17.get(0).click();
            System.out.println("Step 17: xpath=//a[text()=\"Delete\"] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep17.size() > 0){
            System.out.println("Step 17: xpath=//a[text()=\"Delete\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep17.size() == 0) {
            System.out.println("Step 17: xpath=//a[text()=\"Delete\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 18: contextclick xpath=//span[@tooltip-message="Trash"]
        List<WebElement> webElementListStep18 = driver.findElements(By.xpath("//span[@tooltip-message=\"Trash\"]"));
        if(stepOk && webElementListStep18.size() == 1) {
//            webElementListStep18.get(0).click();
//            webElementListStep18.get(0).click();
            Actions actions = new Actions(driver);
            actions.contextClick(webElementListStep18.get(0)).perform();

            System.out.println("Step 18: xpath=//span[@tooltip-message=\"Trash\"] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep18.size() > 0){
            System.out.println("Step 18: xpath=//span[@tooltip-message=\"Trash\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep18.size() == 0) {
            System.out.println("Step 18: xpath=//span[@tooltip-message=\"Trash\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 19: click xpath=//a[text()="Empty trash"]
        List<WebElement> webElementListStep19 = driver.findElements(By.xpath("//a[text()=\"Empty trash\"]"));
        if(stepOk && webElementListStep19.size() == 1) {
            webElementListStep19.get(0).click();
            System.out.println("Step 19: xpath=//a[text()=\"Empty trash\"] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep19.size() > 0){
            System.out.println("Step 19: xpath=//a[text()=\"Empty trash\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep19.size() == 0) {
            System.out.println("Step 19: xpath=//a[text()=\"Empty trash\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 20: click xpath=//a[text()="Delete"]
        List<WebElement> webElementListStep20 = driver.findElements(By.xpath("//a[text()=\"Delete\"]"));
        if(stepOk && webElementListStep20.size() == 1) {
            webElementListStep20.get(0).click();
            System.out.println("Step 20: xpath=//a[text()=\"Delete\"] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep20.size() > 0){
            System.out.println("Step 20: xpath=//a[text()=\"Delete\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep20.size() == 0) {
            System.out.println("Step 20: xpath=//a[text()=\"Delete\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 21: click xpath=//span[@tooltip-message="All notes"]
        List<WebElement> webElementListStep21 = driver.findElements(By.xpath("//span[@tooltip-message=\"All notes\"]"));
        if(stepOk && webElementListStep21.size() == 1) {
            webElementListStep21.get(0).click();
            System.out.println("Step 21: xpath=//span[@tooltip-message=\"All notes\"] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep21.size() > 0){
            System.out.println("Step 21: xpath=//span[@tooltip-message=\"All notes\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep21.size() == 0) {
            System.out.println("Step 21: xpath=//span[@tooltip-message=\"All notes\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 22: find xpath=//span[text()="Empty"]
        boolean newNoteDeletingOK = false;
        List<WebElement> webElementListStep22 = driver.findElements(By.xpath("//span[text()=\"Empty\"]"));
        if(stepOk && webElementListStep22.size() == 1) {
            System.out.println("Step 22: xpath=//span[text()=\"Empty\"] - Локатор найден, запись удалена: " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            newNoteDeletingOK = true;
            System.out.println(ANSI_CYAN + "\nНовая запись успешно создана. " + ANSI_RESET + "Полюбовались на новую запись, теперь можно и удалить ее\n");
            System.out.println("Выдержим паузу в 5 с и разлогиниваемся\n");
            Thread.sleep(5000);
        }
        else if (stepOk && webElementListStep22.size() == 0){
            System.out.println("Step 22: xpath=//span[text()=\"Empty\"] - Локатор не найден, запись не удалена :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 23: click Xpath=//div[contains(text(),"ppvg@list.ru")]
        List<WebElement> webElementListStep23 = driver.findElements(By.xpath("//div[contains(text(),\"ppvg@list.ru\")]"));
        if(stepOk && webElementListStep23.size() == 1) {
            webElementListStep23.get(0).click();
            System.out.println("Step 23: xpath=//div[contains(text(),\"ppvg@list.ru\")] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep23.size() > 0){
            System.out.println("Step 23: xpath=//div[contains(text(),\"ppvg@list.ru\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep23.size() == 0){
            System.out.println("Step 23: xpath=//div[contains(text(),\"ppvg@list.ru\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 24: click xpath=//div[contains(text(),"Log out")]
        List<WebElement> webElementListStep24 = driver.findElements(By.xpath("//div[contains(text(),\"Log out\")]"));
        if(stepOk && webElementListStep24.size() == 1) {
            webElementListStep24.get(0).click();
            System.out.println("Step 24: xpath=//div[contains(text(),\"Log out\")] - Локатор найден, шаг теста:  " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        }
        else if(stepOk && webElementListStep24.size() > 0){
            System.out.println("Step 24: xpath=//div[contains(text(),\"Log out\")] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep24.size() == 0){
            System.out.println("Step 24: xpath=//div[contains(text(),\"Log out\")] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        //Step 25: find xpath=//form[@method="POST"]/input[@name="login"]
        boolean logOutOK = false;
        List<WebElement> webElementListStep25 = driver.findElements(By.xpath("//form[@method=\"POST\"]/input[@name=\"login\"]"));
        if(stepOk && webElementListStep25.size() == 1) {
            System.out.println("Step 25: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Локатор найден, логаут прошел: " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            logOutOK = true;
            System.out.println(ANSI_CYAN + "\nУспешно разлогинились. " + ANSI_RESET + "До новых встреч!!!\n");
        }
        else if(stepOk && webElementListStep25.size() > 0){
            System.out.println("Step 25: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Плохой локатор, множественные совпадения. " + ANSI_RED + "FAILED" + ANSI_RESET);
            stepOk = false;
        }
        else if (stepOk && webElementListStep25.size() == 0){
            System.out.println("Step 25: xpath=//form[@method=\"POST\"]/input[@name=\"login\"] - Совпадений не обнаружено, тест упал :( " + ANSI_RED + "FAILED" + ANSI_RESET );
            stepOk = false;
        }

        Thread.sleep(2000);
        driver.quit();

        if(newNoteCreatingOK && newNoteDeletingOK && logOutOK){
            System.out.println("Все шаги теста пройдены успешно " + ANSI_GREEN + ":)\n" + ANSI_RESET);
            return true;
        }
        else{
            System.out.println("Не сложилось... " + ANSI_RED + ":(\n" + ANSI_RESET);
            return false;
        }
    }
}
