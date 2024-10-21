package com.nekosuki.multieditor.components;

import com.nekosuki.multieditor.components.topmenu_items.FileFX;

public class Components {
    private final Splitter splitter;
    private final SideMenuBar sideMenuBar;
    private final StatusBar statusBar;
    private final CustomTabPane customTabPane;
    private final DisplaySideMenuArea displaySideMenuArea;
    private final MenuBar menuBar;

    public Components() {
        sideMenuBar = new SideMenuBar();
        statusBar = new StatusBar();
        customTabPane = new CustomTabPane();
        displaySideMenuArea = new DisplaySideMenuArea();
        menuBar = new MenuBar();
        splitter = new Splitter();

        readySplitter();
    }

    public Splitter getSplitter() {
        return splitter;
    }

    public SideMenuBar getSideMenuBar() {
        return sideMenuBar;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public CustomTabPane getCustomTabPane() {
        return customTabPane;
    }

    public DisplaySideMenuArea getDisplaySideMenuArea() {
        return displaySideMenuArea;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    private void readySplitter() {
        splitter.getItems().addAll(displaySideMenuArea, customTabPane);
    }
}
