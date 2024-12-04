package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.AppConfig;
import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTabPane;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
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
        File file = null;
        boolean isEdited = false;

        if (selectedTab instanceof MarkDownTab mTab) {
            file = mTab.getFile();
            isEdited = mTab.isEdited();
        }
        else if (selectedTab instanceof TextTab tTab) {
            file = tTab.getFile();
            isEdited = tTab.isEdited();
        }

        if (isEdited) {  // 未編集
            tabPane.getTabs().remove(selectedTab);
        }
        else {  // 編集済み
            if (file != null) {  // ファイルあり

            }
            else {  // ファイルなし

            }
        }
        showWarningDialog();
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
        String selectPath = MainApp.getAppConfig().getProperty(AppConfig.CURRENT_DIR, System.getProperty("user.home"));
        directoryChooser.setInitialDirectory(new File(selectPath));

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
