package com.nekosuki.multieditor.components.tabs;

import com.nekosuki.multieditor.markdown.DisplayMarkDown;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        addEventListener();
    }

    private void addEventListener() {

    }
}
