package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.google.googleSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class pageLoader {
    private String url;
    public pageLoader(parserSettings parser){
        url = googleSettings.BASE_URL;
    }


    public WebDriver getDriver(){
        String path = "C:\\Users\\Novikov\\.cache\\selenium\\geckodriver\\win64\\0.32.0\\geckodriver.exe";
        //WebDriverManager.firefoxdriver().setup();
        System.setProperty("webdriver.gecko.driver",
                path);
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        return driver;
    }
}
