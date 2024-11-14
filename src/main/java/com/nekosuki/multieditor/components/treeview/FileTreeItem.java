package com.nekosuki.multieditor.components.treeview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.io.File;

public class FileTreeItem extends TreeItem<FileItem> {
    private boolean isLeaf;
    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;

    public FileTreeItem(FileItem fileItem) {
        super(fileItem);
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
                    if (treeItem.isLeaf()) {}//icon付けるならここ;
                    else {}//フォルダアイコン
                    children.add(treeItem);
                }
                return children;
            }
        }
        return FXCollections.emptyObservableList();
    }
}
