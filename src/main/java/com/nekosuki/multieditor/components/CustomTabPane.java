package com.nekosuki.multieditor.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class CustomTabPane extends TabPane{
    public CustomTabPane() {
        super();
        this.getTabs().add(new Tab());
    }
}
