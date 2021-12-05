package ru.gb;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.logging.*;

import java.io.*;
import java.time.Duration;
import java.util.List;

public abstract class AbstractTest {

    private WebDriver driver;

    @BeforeAll
    static void enableDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("incognito");
        chromeoptions.addArguments("start-maximized");
        chromeoptions.setPageLoadTimeout(Duration.ofSeconds(10));

        driver = new ChromeDriver(chromeoptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://pvg.nimbusweb.me/auth/?f=login&success=/client");
    }

    @AfterEach
    void releaseDriver() throws InterruptedException {
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();

        if (allLogRows.size() > 0 ) {
            File destination = new File("./target/" + FileName.getFilename() + "-log_browser.txt");
            try {
                FileUtils.writeLines(destination, allLogRows);

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        if(driver != null) {
            Thread.sleep(5000);
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
