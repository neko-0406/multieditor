package com.nekosuki.multieditor.components.treeview;

import javafx.collections.ListChangeListener;
import javafx.scene.control.TreeItem;

public class TreeItemChangeListener implements ListChangeListener<TreeItem<FileItem>> {

    public TreeItemChangeListener() {
    }

    @Override
    public void onChanged(Change change) {
        if (change.wasAdded()) {
            var addList = change.getAddedSubList();
            if (!addList.isEmpty()) {
                for (var i : addList) {
                    System.out.println(i.toString());
                }
            }
            else if (change.wasRemoved()) {}
            else {}
        }
    }
}
