package com.nekosuki.multieditor.components;

public class Components {
    private final Splitter splitter;
    private final SideMenuBar sideMenuBar;
    private final StatusBar statusBar;
    private final CustomTabPane customTabPane;
    private final DisplaySideMenuArea displaySideMenuArea;
    private final CustomTreeView customTreeView;
    private final MenuBar menuBar;

    public Components() {
        sideMenuBar = new SideMenuBar();
        statusBar = new StatusBar();
        customTabPane = new CustomTabPane();
        displaySideMenuArea = new DisplaySideMenuArea();
        menuBar = new MenuBar();
        splitter = new Splitter();
        customTreeView = new CustomTreeView();

        readySplitter();
    }

    public CustomTreeView getCustomTreeView() {return customTreeView;}

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
        displaySideMenuArea.getChildren().add(customTreeView);
        splitter.getItems().addAll(displaySideMenuArea, customTabPane);
    }
}
