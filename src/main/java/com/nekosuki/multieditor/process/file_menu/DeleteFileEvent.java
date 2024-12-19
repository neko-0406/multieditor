package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class DeleteFileEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        ObservableList<TreeItem<FileItem>> items;
        Alert alert = null;
        items = MainApp.getComponents().getCustomTreeView().getSelectionModel().getSelectedItems();

        if (items.isEmpty()) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("削除するファイルが選択されていません。");
            alert.setContentText("エクスプローラーから選択してください。");
            alert.show();
            return;
        }

        StringBuilder content = new StringBuilder();
        for (TreeItem<FileItem> item : items) {
            content.append(item.getValue().getFileName()).append(", ");
        }

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ファイルを削除");
        alert.setHeaderText("以下のファイルを完全に削除します。よろしいですか？");
        alert.setContentText(content.toString());
        Optional<ButtonType> option = alert.showAndWait();

//        ButtonType [text=OK, buttonData=OK_DONE]
//        ButtonType [text=取消, buttonData=CANCEL_CLOSE]
        if (option.isPresent()) {
            if (option.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)){
                for (var item : items) {
                    FileType fileType = item.getValue().getFileType();
                    if (fileType == FileType.FOLDER) {
                        Alert dirAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        dirAlert.setTitle("ファイルを削除");
                        dirAlert.setHeaderText("アイテムにフォルダが含まれています。すべて削除しますか？");
                        Optional<ButtonType> dirOption = dirAlert.showAndWait();

                        dirOption.ifPresent(result -> {

                        });
                    }
                }
            }
            else {}
        }

    }
}