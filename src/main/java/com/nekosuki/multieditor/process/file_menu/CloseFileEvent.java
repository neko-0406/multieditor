package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.AppConfig;
import com.nekosuki.multieditor.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloseFileEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

    }

    private File openDirDialog() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("保存するフォルダを選択");
        String selectPath = MainApp.getAppConfig().getProperty(AppConfig.CURRENT_DIR, System.getProperty("user.home"));
        directoryChooser.setInitialDirectory(new File(selectPath));

        File dir = directoryChooser.showDialog(null);
        if (dir != null) {
            if (!dir.exists()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("警告");
                alert.setHeaderText("選択されたフォルダがありません。");
                alert.showAndWait();
                return null;
            }


        }

        return null;
    }

    /**
     * 文字列をutf-8でファイルに書き込むだけ
     * @param path 書き込みたいファイルパス
     * @param value 書き込みたい内容
     * @return boolean 成功したかどうか
     */
    private boolean writeValue(Path path, String value) {
        boolean isSuccess = true;

        try {
            Files.writeString(path, value, Charset.defaultCharset());
        }catch (IOException e) {
            isSuccess = false;
        }

        return isSuccess;
    }
}
