package org.example;

import org.example.google.googleParser;
import org.example.google.googleSettings;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String imageTopic = "Слоны";
        worker<ArrayList<String>> parser = new worker<>(new googleParser());
        parser.setParserSettings(new googleSettings(20));
        //parser.addNewDataList(new NewData());

        parser.startParse(imageTopic);
    }

    static class NewData implements IOnNewDataHandler<ArrayList<String>> {

        @Override
        public void OnNewData(Object sender, ArrayList<String> args) {
            //if we need to output something on screen
        }
    }
}