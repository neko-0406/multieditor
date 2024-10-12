package com.nekosuki.multieditor.components.topmenu_items;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileFX extends Menu {
    public FileFX() {
        super("ファイル");
        this.getItems().addAll(
            getOpenFile()
        );
    }

    private MenuItem getOpenFile() {
        MenuItem menuItem = new MenuItem("ファイルを開く");

        return menuItem;
    }
}
