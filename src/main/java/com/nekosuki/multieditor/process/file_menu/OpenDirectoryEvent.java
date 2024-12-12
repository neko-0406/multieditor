package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import com.nekosuki.multieditor.process.config.AppConfig;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class OpenDirectoryEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        AppConfig appConfig = MainApp.getAppConfig();
        TreeView<FileItem> treeView = MainApp.getComponents().getCustomTreeView();
        TitledPane titledPane = MainApp.getComponents().getRootDirTitlePane();

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("フォルダを開く");
        String homePath = System.getProperty("user.home");
        String currentPath = MainApp.getAppConfig().getDirectory().getCurrentDir();
        String dirPath = !currentPath.isEmpty() ? currentPath : homePath;
        chooser.setInitialDirectory(new File(dirPath));

        File file = chooser.showDialog(null);
        if (!file.exists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Folder no found....");
            alert.setHeaderText("選択されたフォルダが存在していません");
            alert.setContentText("");
            alert.show();
            return;
        }

        appConfig.getDirectory().setCurrentDir(file.getAbsolutePath());
        FileTreeItem fItem = new FileTreeItem(new FileItem(file));
        fItem.setExpanded(true);
        titledPane.setText(file.getName());
        treeView.setRoot(fItem);
        appConfig.storeConfig();
    }
}
