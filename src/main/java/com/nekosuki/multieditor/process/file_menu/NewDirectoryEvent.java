package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.Optional;

public class NewDirectoryEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

        String folderName;
        File selectedFolder = showSelectDirectoryDialog();

        if (selectedFolder != null) {
            folderName = showInputDirectoryNameDialog();
            if (folderName == null) return;
            File dirPath = new File(selectedFolder, folderName);

            if (dirPath.exists()) {
                showExistFolderAlert();
                return;
            }

            boolean isSuccess = dirPath.mkdir();
            if (!isSuccess) {
                showFailedCreateFolderAlert();
            }
        }
    }

    /**
     * フォルダの作成失敗を伝えるやつ
     */
    private void showFailedCreateFolderAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("フォルダを作成");
        alert.setHeaderText("フォルダの作成に失敗しました。");
        alert.show();
    }

    /**
     * 同じフォルダがあった場合に表示するやつ
     */
    private void showExistFolderAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("新しいフォルダ");
        alert.setHeaderText("同じ名前のフォルダが既にあります");
        alert.show();
    }

    /**
     * フォルダを作るためのフォルダを選択するやつ
     * @return フォルダのパス
     */
    private File showSelectDirectoryDialog() {
        String basePath;
        basePath = MainApp.getAppConfig().getDirectory().getCurrentDir();
        if (basePath.isEmpty()) {
            basePath = System.getProperty("user.home");
        }
        File folder = new File(basePath);
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("フォルダを選択");
        directoryChooser.setInitialDirectory(folder);
        return directoryChooser.showDialog(null);
    }

    /**
     * フォルダ名入力ダイアログを出すやつ
     * @return フォルダ名 or null
     */
    private String  showInputDirectoryNameDialog() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("フォルダ名を入力");
        textInputDialog.setContentText("フォルダ名：");
        textInputDialog.getDialogPane().setPrefWidth(300);

        Optional<String> dirName = textInputDialog.showAndWait();

        return dirName.orElse(null);
    }
}
