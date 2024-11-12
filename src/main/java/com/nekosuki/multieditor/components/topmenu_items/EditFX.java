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
            undo()
        );
    }

    private MenuItem undo() {
        MenuItem item = new MenuItem("元に戻す");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+Z"));
        item.setOnAction(event -> {
            Tab tab = MainApp.getComponents().getCustomTabPane().getSelectionModel().getSelectedItem();
            if (tab instanceof MarkDownTab mTab) {
                mTab.undo();
            }
            else if (tab instanceof TextTab tTab) {
                tTab.redo();
            }
        });

        return item;
    }
}
