package com.nekosuki.multieditor.components.topmenu_items;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTabPane;
import com.nekosuki.multieditor.components.Splitter;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;

public class FileFX extends Menu {
    public FileFX() {
        super("ファイル");
        this.getItems().addAll(
            getOpenFile()
        );
    }

    private MenuItem getOpenFile() {
        MenuItem menuItem = new MenuItem("ファイルを開く");
        menuItem.setOnAction(event -> {
            CustomTabPane tabPane = MainApp.getComponents().getCustomTabPane();
            tabPane.getTabs().add(new MarkDownTab());
        });
        return menuItem;
    }
}
