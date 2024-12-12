package com.nekosuki.multieditor.components;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

@Getter
public class Components {
    private final Splitter splitter;
    private final SideMenuBar sideMenuBar;
    private final StatusBar statusBar;
    private final CustomTabPane customTabPane;
    private final DisplaySideMenuArea displaySideMenuArea;
    private final CustomTreeView customTreeView;
    private final MenuBar menuBar;
    private final TitledPane rootDirTitlePane;

    public Components() {
        sideMenuBar = new SideMenuBar();
        statusBar = new StatusBar();
        customTabPane = new CustomTabPane();
        displaySideMenuArea = new DisplaySideMenuArea();
        menuBar = new MenuBar();
        splitter = new Splitter();
        customTreeView = new CustomTreeView();
        rootDirTitlePane = new TitledPane();

        readySplitter();
    }

    private void readySplitter() {
        AnchorPane.setTopAnchor(rootDirTitlePane, 0.0);
        AnchorPane.setLeftAnchor(rootDirTitlePane, 0.0);
        AnchorPane.setBottomAnchor(rootDirTitlePane, 0.0);
        AnchorPane.setRightAnchor(rootDirTitlePane, 0.0);
        displaySideMenuArea.getChildren().add(rootDirTitlePane);
        rootDirTitlePane.setContent(customTreeView);
        rootDirTitlePane.setCollapsible(false);
        splitter.getItems().addAll(displaySideMenuArea, customTabPane);
    }
}
