package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTabPane;
import com.nekosuki.multieditor.components.popup.CloseFileAlertDialog;
import com.nekosuki.multieditor.components.popup.ResultType;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloseFileEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        CustomTabPane tabPane = MainApp.getComponents().getCustomTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        File file;
        String value;
        boolean isEdited;

        if (selectedTab instanceof MarkDownTab mTab) {
            file = mTab.getFile();
            isEdited = mTab.isEdited();
            value = mTab.getCodeArea().getText();
        }
        else if (selectedTab instanceof TextTab tTab) {
            file = tTab.getFile();
            isEdited = tTab.isEdited();
            value = tTab.getCodeArea().getText();
        }
        else {
            ObservableList<Tab> tabs = tabPane.getTabs();
            tabs.remove(selectedTab);
            return;
        }

        if (isEdited) {
            CloseFileAlertDialog dialog = new CloseFileAlertDialog();
            ResultType type = dialog.showAndWait();

            if (type == ResultType.YES) {
                if (file.exists()) {
                    boolean isSuccess = writeValue(file.toPath(), value);
                    if (!isSuccess) {
                        displayErrorDialog();
                    }
                    else {
                        ObservableList<Tab> tabs = tabPane.getTabs();
                        tabs.remove(selectedTab);
                    }
                }
                else {
                    File saveDir = openDirDialog();
                    if (saveDir != null){
                        String fileName = selectedTab.getText();
                        Path filePath = saveDir.toPath().resolve(fileName);
                        writeValue(filePath, value);
                        ObservableList<Tab> tabs = tabPane.getTabs();
                        tabs.remove(selectedTab);
                    }
                }
            }else if (type == ResultType.NO) {
                ObservableList<Tab> tabs = tabPane.getTabs();
                tabs.remove(selectedTab);
            }
        }




    }

    /**
     * ファイルの保存失敗をアラートするだけ
     */
    private void displayErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("ファイルの保存に失敗しました。");
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
            Files.writeString(path, value, StandardCharsets.UTF_8);
        }catch (IOException e) {
            isSuccess = false;
        }

        return isSuccess;
    }
}
