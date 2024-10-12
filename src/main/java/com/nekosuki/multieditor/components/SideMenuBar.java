package com.nekosuki.multieditor.components;

import com.nekosuki.multieditor.IconManager;
import com.nekosuki.multieditor.Icons;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class SideMenuBar extends ToolBar {
    public SideMenuBar() {
        super();
        this.setOrientation(Orientation.VERTICAL);
        this.getItems().addAll(
            setExplorerButton()
        );
    }

    private Button setExplorerButton() {
        return new Button(null, IconManager.getIcon(Icons.FILES));
    }
}
