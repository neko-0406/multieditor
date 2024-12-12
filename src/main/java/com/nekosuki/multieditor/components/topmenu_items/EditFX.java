package com.nekosuki.multieditor.components.topmenu_items;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCombination;

public class EditFX extends Menu {
    public EditFX() {
        super("編集");
        this.getItems().addAll(
            undo(),
            redo()
        );
    }

    private MenuItem undo() {
        MenuItem item = new MenuItem("元に戻す");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+Z"));
        item.setOnAction(event -> {
            Tab tab = MainApp.getComponents().getCustomTabPane().getSelectionModel().getSelectedItem();
        });

        return item;
    }

    private MenuItem redo() {
        MenuItem item = new MenuItem("やり直す");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+Y"));
        item.setOnAction(event -> {
            Tab tab = MainApp.getComponents().getCustomTabPane().getSelectionModel().getSelectedItem();
        });

        return item;
    }
}
