package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class NewMarkdownFileEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("ファイル名を入力");
        textInputDialog.setContentText("ファイル名：");
        textInputDialog.getDialogPane().setPrefWidth(300);
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(name -> {
            MarkDownTab tab = new MarkDownTab();
            String fileName = name;
            if (!name.endsWith(".md") || !name.endsWith(".markdown")){
                fileName += ".md";
            }
            tab.setText(fileName);
            MainApp.getComponents().getCustomTabPane().getTabs().add(tab);
        });
    }
}
