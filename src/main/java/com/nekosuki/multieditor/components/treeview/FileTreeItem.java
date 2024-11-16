package com.nekosuki.multieditor.components.treeview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;

public class FileTreeItem extends TreeItem<FileItem> {
    private boolean isLeaf;
    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;

    private static final int iconSize = 20;

    public FileTreeItem(FileItem fileItem) {
        super(fileItem);
        FileType type = this.getValue().getFileType();
        switch (type) {
            case FILE -> {
                FontIcon fileIcon = new FontIcon("far-file");
                fileIcon.setIconSize(iconSize);
                this.setGraphic(fileIcon);
            }
            case FOLDER -> {
                FontIcon folderIcon = new FontIcon("far-folder");
                folderIcon.setIconSize(20);
                this.setGraphic(folderIcon);
            }
        }

        this.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue && newValue && this.getValue().getFileType() == FileType.FOLDER) {  //フォルダを開いたとき
                FontIcon folderIcon = new FontIcon("far-folder-open");
                folderIcon.setIconSize(iconSize);
                this.setGraphic(folderIcon);
            }
            else if (oldValue && !newValue && this.getValue().getFileType() == FileType.FOLDER) {
                FontIcon folderIcon = new FontIcon("far-folder");
                folderIcon.setIconSize(iconSize);
                this.setGraphic(folderIcon);
            }
        });
    }

    @Override
    public ObservableList<TreeItem<FileItem>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren  = false;
            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    @Override
    public boolean isLeaf() {
        if (isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
            isLeaf = getValue().getFileType() == FileType.FILE;
        }
        return isLeaf;
    }

    private ObservableList<FileTreeItem> buildChildren(FileTreeItem fileTreeItem) {
        File file = fileTreeItem.getValue().getFile();
        if (file != null && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                ObservableList<FileTreeItem> children = FXCollections.observableArrayList();
                for (File childFile : files) {
                    FileTreeItem treeItem = new FileTreeItem(new FileItem(childFile));
                    children.add(treeItem);
                }
                return children;
            }
        }
        return FXCollections.emptyObservableList();
    }
}
