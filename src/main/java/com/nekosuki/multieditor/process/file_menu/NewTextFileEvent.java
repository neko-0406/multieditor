package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.tabs.TextTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class NewTextFileEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("ファイル名を入力");
        textInputDialog.setContentText("ファイル名：");
        textInputDialog.getDialogPane().setPrefWidth(300);
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(name -> {
            TextTab tab = new TextTab();
            tab.setText(name);
            MainApp.getComponents().getCustomTabPane().getTabs().add(tab);
        });
    }
}
