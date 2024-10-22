package com.nekosuki.multieditor.components.tabs;

import com.nekosuki.multieditor.markdown.DisplayMarkDown;
import com.nekosuki.multieditor.markdown.GenerateHTML;
import javafx.application.Platform;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

public class MarkDownTab extends Tab {
    private final CodeArea codeArea;
    private final WebView webView;
    private final static GenerateHTML generateHtml = new GenerateHTML();

    public MarkDownTab() {
        super();
        webView = new WebView();
        codeArea = new CodeArea();
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(codeArea, webView);
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        this.setContent(splitPane);
        webView.getEngine().loadContent(generateHtml.getTemplateHtml());
        addEventListener();
    }

    private void addEventListener() {
        codeArea.textProperty().addListener((observable, oldValue, newValue) -> {
            String html = generateHtml.genHtmlTextsFromMarkDown(newValue);
            WebEngine engine = webView.getEngine();
            engine.loadContent(html);
        });
    }


}
