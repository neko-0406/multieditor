package com.nekosuki.multieditor.process.file_menu;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import com.nekosuki.multieditor.process.config.AppConfig;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeView;

/**
 * ファイルメニューで使う関数置き場
 */
public class CloseDirectoryEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        TreeView<FileItem> treeView = MainApp.getComponents().getCustomTreeView();
        FileTreeItem rootItem = (FileTreeItem) treeView.getRoot();
        TitledPane rootDirTitlePane = MainApp.getComponents().getRootDirTitlePane();
        AppConfig appConfig = MainApp.getAppConfig();
        if (rootItem != null) {
            treeView.setRoot(null);
            rootDirTitlePane.setText("");

            appConfig.getDirectory().setCurrentDir("");
            appConfig.getDirectory().setLastOpenDir("");
            appConfig.storeConfig();
        }
    }
}
