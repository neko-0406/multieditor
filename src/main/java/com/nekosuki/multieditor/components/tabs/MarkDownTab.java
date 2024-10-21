package com.nekosuki.multieditor.components.tabs;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

public class MarkDownTab extends Tab {
    private final CodeArea codeArea;
    private final WebView webView;
    private static int num = 0;

    public MarkDownTab() {
        super();
        this.setText(String.valueOf(num));
        num++;
        webView = new WebView();
        codeArea = new CodeArea();
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(codeArea, webView);
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        this.setContent(splitPane);
    }
}
