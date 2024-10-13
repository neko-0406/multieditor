package com.nekosuki.multieditor.components;

import javafx.scene.control.SplitPane;

public class Splitter extends SplitPane{
    public Splitter() {
        super();
        this.getItems().addAll(
            new DisplaySideMenuArea(),
            new CustomTabPane()
        );
        this.setDividerPositions(0.2);
    }
}