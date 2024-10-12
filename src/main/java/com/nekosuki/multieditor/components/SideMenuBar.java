package com.nekosuki.multieditor.components;

import com.nekosuki.multieditor.IconManager;
import com.nekosuki.multieditor.Icons;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

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
        Button button = new Button(null, IconManager.getIcon(Icons.FILES));
        button.setMinHeight(32);
        button.setMinWidth(32);
        button.getStyleClass().add(buttonClass);
        return button;
    }
}
