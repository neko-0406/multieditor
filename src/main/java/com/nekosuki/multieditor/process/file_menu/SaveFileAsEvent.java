package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTabPane;
import com.nekosuki.multieditor.components.tabs.ITextTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SaveFileAsEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        CustomTabPane tabPane = MainApp.getComponents().getCustomTabPane();
        Tab tab = tabPane.getSelectionModel().getSelectedItem();

        if (tab instanceof ITextTab iTextTab) {
            FileChooser fileChooser = new FileChooser();

            String initPath = MainApp.getAppConfig().getDirectory().getCurrentDir();
            if (initPath.isEmpty()) {
                initPath = System.getProperty("user.home");
            }
            fileChooser.setTitle("名前を付けて保存");
            fileChooser.setInitialDirectory(new File(initPath));
            File file = fileChooser.showSaveDialog(null);

            if (file == null) return;

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
                bw.write(iTextTab.getValue());
            }catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }


    }
}
