package com.nekosuki.multieditor.components.treeview;

import java.io.File;

public class FileItem {
    private final File file;
    private final FileType fileType;

    public FileItem(File file) {
        this(file, FileType.FILE);
    }

    private FileItem(File file, FileType fileType) {
        this.file = file;
        this.fileType = fileType;
    }

    public String getFileName() {
        return file.getName();
    }

    public FileType getFileType() {
        return fileType;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return this.getFileName();
    }
}
