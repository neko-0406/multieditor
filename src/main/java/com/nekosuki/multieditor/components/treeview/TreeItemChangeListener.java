package com.nekosuki.multieditor.components.treeview;

import javafx.collections.ListChangeListener;
import javafx.scene.control.TreeItem;

public class TreeItemChangeListener implements ListChangeListener<FileTreeItem> {
    private final TreeItem<FileItem> treeItem;

    public TreeItemChangeListener(TreeItem<FileItem> treeItem) {
        this.treeItem = treeItem;
    }

    @Override
    public void onChanged(Change change) {
        if (change.wasAdded()) {
            var addList = change.getAddedSubList();
            treeItem.
        }

        else if (change.wasRemoved()) {}

        else {}
    }
}
