package com.nekosuki.multieditor.components.treeview;

import lombok.Getter;

import java.io.File;

@Getter
public class FileItem {
    private final File file;
    private final FileType fileType;

    public FileItem(File file) {
        this.file = file;
        this.fileType = file.isDirectory() ? FileType.FOLDER : FileType.FILE;
    }

    public String getFileName() {
        return file.getName();
    }

    @Override
    public String toString() {
        return this.getFileName();
    }
}
