package org.example.google;

import org.example.IParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class googleParser implements IParser {
    @Override
    public Object parse(WebDriver driver, String topic, int count) throws InterruptedException {

        //parse
        WebElement searchArea = driver.findElement(By.cssSelector("input[class='gLFyf gsfi']"));
        searchArea.sendKeys(topic);
        Thread.sleep(100);

        WebElement submitButton = driver.findElement(By.cssSelector("input[class='gNO89b']"));
        submitButton.click();

        WebElement tools = driver.findElement(By.cssSelector("div[class='MUFPAc']"));
        List<WebElement> elements = tools.findElements(By.cssSelector("div[class='hdtb-mitem']"));
        for (WebElement element : elements) {
            WebElement tag = element.findElement(By.tagName("a"));
            String text = tag.getText();
            if (text.contains("Картинки")) {
                tag.click();
                break;
            }
        }

        tools = driver.findElement(By.cssSelector("div[class='islrc']"));
        elements = tools.findElements(By.cssSelector("div[class^='isv-r']"));
        for (int i = 0; i < count; i++) {
            WebElement element = elements.get(i);
            WebElement image = element.findElement(By.cssSelector("img[class='rg_i Q4LuWd']"));
            image.click();
            Thread.sleep(100);

            WebElement rightImageHolder = driver.findElement(By.cssSelector("div[class='l39u4d']"));
            //System.out.println(rightImageHolder.getAttribute("jsname"));//V7lXsd

            WebElement rightImage = rightImageHolder.findElement(By.cssSelector("div[class='pxAole']"));
            //System.out.println(rightImage.getAttribute("jsname"));//CbeVOb

            WebElement rightImage1 = rightImage.findElement(By.cssSelector("div[class='tvh9oe BIB1wf']"));
            WebElement rightImage4 = rightImage1.findElement(By.cssSelector("div[jsname='OrqX8']"));
            WebElement rightImage2 = rightImage4.findElement(By.cssSelector("img[class^='n3VNCb']"));

            String imageSRC = rightImage2.getAttribute("src");
            String imageName = String.valueOf(i);
            outImage(imageSRC,imageName);
        }

        return null;
    }

    private void outImage(String imageSRC, String imageName){
        BufferedImage bufferedImage = null;
        URL imageURL = null;
        try{
            imageURL = new URL(imageSRC);
        } catch (MalformedURLException e) {
            return;
            //unreliable url
        }
        try {
            bufferedImage = ImageIO.read(imageURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (bufferedImage==null)
            return;

        File outputfile = new File("images/" + imageName + ".png");

        try {
            ImageIO.write(bufferedImage, "png", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
