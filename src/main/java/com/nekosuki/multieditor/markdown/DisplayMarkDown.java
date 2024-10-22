package com.nekosuki.multieditor.markdown;

import java.util.concurrent.Callable;

public class DisplayMarkDown implements Callable<String> {
    private final String input;
    private static final GenerateHTML generateHtml = new GenerateHTML();

    public DisplayMarkDown(String input) {
        this.input = input;
    }

    @Override
    public String call() {
        return generateHtml.genHtmlTextsFromMarkDown(input);
    }
}
