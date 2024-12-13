package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTabPane;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

import java.nio.file.Path;

public class SaveFileEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        CustomTabPane tabPane = MainApp.getComponents().getCustomTabPane();
        Tab tab = tabPane.getSelectionModel().getSelectedItem();

        Path filePath;
        String value;
        boolean isEdited = false;

        if (tab instanceof MarkDownTab mTab) {
            filePath = mTab.getFile().toPath();
            value = mTab.getCodeArea().getText();
            isEdited = mTab.isEdited();
        }else if (tab instanceof TextTab tTab) {
            filePath = tTab.getFile().toPath();
            value = tTab.getCodeArea().getText();
            isEdited = tTab.isEdited();
        }
    }
}
