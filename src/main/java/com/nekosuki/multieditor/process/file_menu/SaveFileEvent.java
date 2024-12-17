package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTabPane;
import com.nekosuki.multieditor.components.tabs.ITextTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class SaveFileEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        CustomTabPane tabPane = MainApp.getComponents().getCustomTabPane();
        Tab tab = tabPane.getSelectionModel().getSelectedItem();

        File file;
        String value;
        boolean isEdited;

        if (!(tab instanceof ITextTab iTextTab)) return;

        file = iTextTab.getFile();
        value = iTextTab.getValue();
        isEdited = iTextTab.isEdited();

        if (file == null) {
            DirectoryChooser chooser = new DirectoryChooser();
            String path = MainApp.getAppConfig().getDirectory().getCurrentDir();
            if (path.isEmpty()) {
                path = System.getProperty("user.home");
            }

            chooser.setInitialDirectory(new File(path));
            chooser.setTitle("ファイルを保存するディレクトリを選択");
            File dir = chooser.showDialog(null);

            if (dir != null) {
                Path base = dir.toPath();
                Path url = base.resolve(tab.getText());
                file = url.toFile();
            }
            else return;
        }

        if (isEdited) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
                bw.write(value);
            }catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            iTextTab.resetEdited();
        }
    }
}
