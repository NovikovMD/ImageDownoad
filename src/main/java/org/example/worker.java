package org.example;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class worker<T> {
    private IParser<T> parser;
    private parserSettings parserSettings;
    private pageLoader pageLoader;
    private ArrayList<IOnNewDataHandler> onNewDataList = new ArrayList<>();

    public worker(IParser<T> parser) {
        this.parser = parser;
    }

    public void addNewDataList(IOnNewDataHandler t){
        onNewDataList.add(t);
    }
    public void setParserSettings(parserSettings parserSettings) {
        this.parserSettings = parserSettings;
        pageLoader=new pageLoader(parserSettings);
    }


    public void startParse(String topic) throws InterruptedException {
        WebDriver driver = pageLoader.getDriver();
        parser.parse(driver,topic, parserSettings.count);
        driver.quit();
    }
}
