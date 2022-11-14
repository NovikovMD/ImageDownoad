package org.example;

import org.openqa.selenium.WebDriver;

public interface IParser<T> {
    public T parse(WebDriver driver, String topic, int count) throws InterruptedException;
}
