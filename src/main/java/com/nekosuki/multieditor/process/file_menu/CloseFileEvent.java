package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTabPane;
import com.nekosuki.multieditor.components.popup.CloseFileAlertDialog;
import com.nekosuki.multieditor.components.tabs.ITextTab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloseFileEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        CustomTabPane tabPane = MainApp.getComponents().getCustomTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        File file;
        boolean isEdited;
        new CloseFileAlertDialog();

        if (selectedTab instanceof ITextTab iTab) {
            file = iTab.getFile();
            isEdited = iTab.isEdited();
        } else return;

        if (file != null && isEdited) {

        }
    }


    private void showWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("ファイルが保存されていません!");
        alert.setContentText("ファイルを保存してから閉じますか?");
        alert.getButtonTypes().clear();
        ButtonType yesButton = new ButtonType("はい");
        ButtonType noButton = ButtonType.NO;
        alert.getButtonTypes().addAll(yesButton, noButton);
        alert.showAndWait();
    }

    /**
     * フォルダ選択ダイアログ表示
     * @return File or null
     */
    private File openDirDialog() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("保存するフォルダを選択");

        String currentPath = MainApp.getAppConfig().getDirectory().getCurrentDir();
        File file;

        if (currentPath.isEmpty()) {
            file = new File(System.getProperty("user.home"));
        }
        else {
            file = new File(currentPath);
        }

        directoryChooser.setInitialDirectory(file);

        File dir = directoryChooser.showDialog(null);
        if (dir != null) {  // フォルダ選択
            if (!dir.exists()) {  // フォルダが無い
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("警告");
                alert.setHeaderText("選択されたフォルダがありません。");
                alert.showAndWait();
                return null;
            }
        }

        return dir;
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
