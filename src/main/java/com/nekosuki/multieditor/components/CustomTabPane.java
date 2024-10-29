package com.nekosuki.multieditor.components;

import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebView;

import java.util.Stack;

public class CustomTabPane extends TabPane{

    private Stack<WebView> webViewStack;

    public CustomTabPane() {
        super();
    }
}
