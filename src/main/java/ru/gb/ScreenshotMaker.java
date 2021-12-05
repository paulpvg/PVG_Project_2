package ru.gb;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.*;

public class ScreenshotMaker{
    public static void makeScreenshot(WebDriver driver, String filename) {
        File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File("./target/" + filename);
        try {
            FileUtils.copyFile(temp, destination);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
