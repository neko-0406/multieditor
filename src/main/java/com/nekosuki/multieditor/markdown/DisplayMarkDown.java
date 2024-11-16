package com.nekosuki.multieditor.markdown;

import javafx.scene.web.WebEngine;

public class DisplayMarkDown implements Runnable {
    private final String input;
    private final WebEngine webEngine;
    private static final GenerateHTML generateHtml = new GenerateHTML();

    public DisplayMarkDown(String input, WebEngine webEngine) {
        this.input = input;
        this.webEngine = webEngine;
    }

    @Override
    public void run() {
        String html = generateHtml.genHtmlTextsFromMarkDown(input);
        System.out.println(html);
        webEngine.loadContent(html);
    }
}
