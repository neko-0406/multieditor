package com.nekosuki.multieditor.components;

import com.nekosuki.multieditor.AppConfig;
import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.tabs.FileType;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.util.List;

public class CustomTreeView extends TreeView<FileItem> {
    public CustomTreeView() {
        super();

        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setShowRoot(false);
        this.setOnMouseClicked(event -> {
            int configCount = Integer.parseInt(MainApp.getAppConfig().getProperty(AppConfig.CLICK_COUNT, "2"));
            if (event.getClickCount() == configCount) {
                FileTreeItem item = (FileTreeItem) getSelectionModel().getSelectedItem();
                if (item == null) return;
                File file = item.getValue().getFile();
                CustomTabPane tabPane = MainApp.getComponents().getCustomTabPane();
                List<Tab> tabs = tabPane.getTabs();
                Tab sameTab = null;
                for (Tab t : tabs) {
                    if (t.getText().equals(file.getName())) {
                        sameTab = t;
                        break;
                    }
                }

                if (file.isFile() && sameTab == null) {
                    String fileType = FileType.getFileType(file);

                    Tab tab = switch (fileType) {
                        case FileType.MARKDOWN -> new MarkDownTab(file);
                        case FileType.TEXT -> new TextTab(file);
                        default -> new Tab("File not found...");
                    };
                    tabPane.getTabs().add(tab);
                    tabPane.getSelectionModel().select(tab);
                }else {
                    tabPane.getSelectionModel().select(sameTab);
                }
            }
        });
    }

}
