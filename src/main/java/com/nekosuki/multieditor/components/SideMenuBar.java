package com.nekosuki.multieditor.components;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import org.kordamp.ikonli.javafx.FontIcon;

public class SideMenuBar extends ToolBar {
    private final String buttonClass;

    public SideMenuBar() {
        super();
        this.getStyleClass().add("side-menu-bar");
        buttonClass = "side-menu-button";
        this.setOrientation(Orientation.VERTICAL);
        this.getItems().addAll(
            setExplorerButton()
        );
    }

    private Button setExplorerButton() {
        FontIcon fileIcon = new FontIcon("far-folder");
        fileIcon.setIconSize(32);
        Button button = new Button(null, fileIcon);
        button.getStyleClass().add(buttonClass);
        button.setTooltip(new Tooltip("エクスプローラー"));
        return button;
    }
}
