package com.nekosuki.multieditor.components;

import com.nekosuki.multieditor.AppConfig;
import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.tabs.FileType;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class CustomTreeView extends TreeView<FileItem> {
    public CustomTreeView() {
        super();
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);

        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setOnMouseClicked(event -> {
            int configCount = Integer.parseInt(MainApp.getAppConfig().getProperty(AppConfig.CLICK_COUNT, "2"));
            if (event.getClickCount() == configCount) {
                FileTreeItem item = (FileTreeItem) getSelectionModel().getSelectedItem();
                File file = item.getValue().getFile();
                if (file.isFile()) {
                    String fileType = FileType.getFileType(file);

                    Tab tab = switch (fileType) {
                        case FileType.MARKDOWN -> new MarkDownTab(file);
                        case FileType.TEXT -> new TextTab(file);
                        default -> new Tab("File not found...");
                    };
                    CustomTabPane tabPane = MainApp.getComponents().getCustomTabPane();
                    tabPane.getTabs().add(tab);
                    tabPane.getSelectionModel().select(tab);
                }
            }
        });
    }
}
