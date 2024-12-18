package com.nekosuki.multieditor.components.topmenu_items;

import com.nekosuki.multieditor.process.file_menu.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import org.jetbrains.annotations.NotNull;

public class FileFX extends Menu {

    public FileFX() {
        super("ファイル");
        this.getItems().addAll(
            openFile(),
            newTextFile(),
            newMarkDownFile(),
            saveFile(),
            saveFileAs(),
            closeFile(),
            deleteFiles(),
            new SeparatorMenuItem(),
            openDir(),
            closeDir()
        );
    }

    private @NotNull MenuItem deleteFiles() {
        MenuItem item = new MenuItem("ファイルを削除");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+D"));
        item.setOnAction(new DeleteFileEvent());
        return item;
    }

    private @NotNull MenuItem closeDir() {
        MenuItem item = new MenuItem("フォルダを閉じる");
        item.setOnAction(new CloseDirectoryEvent());
        return item;
    }

    private @NotNull MenuItem openDir() {
        MenuItem item = new MenuItem("フォルダを開く");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+O"));
        item.setOnAction(new OpenDirectoryEvent());
        return item;
    }

    private @NotNull MenuItem closeFile() {
        MenuItem menuItem = new MenuItem("ファイルを閉じる");
        menuItem.setOnAction(new CloseFileEvent());
        return menuItem;
    }

    private @NotNull MenuItem saveFile() {
        MenuItem item = new MenuItem("ファイルを保存");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+S"));
        item.setOnAction(new SaveFileEvent());
        return item;
    }

    private @NotNull MenuItem saveFileAs() {
        MenuItem menuItem = new MenuItem("名前を付けて保存");
        menuItem.setAccelerator(KeyCombination.valueOf("Ctrl+Shift+S"));
        menuItem.setOnAction(new SaveFileAsEvent());
        return menuItem;
    }

    private @NotNull MenuItem newMarkDownFile() {
        MenuItem item = new MenuItem("新しいマークダウンファイル");
        item.setOnAction(new NewMarkdownFileEvent());
        return item;
    }

    private @NotNull MenuItem newTextFile() {
        MenuItem item = new MenuItem("新しいテキストファイル");
        item.setOnAction(new NewTextFileEvent());
        return item;
    }

    private @NotNull MenuItem openFile() {
        MenuItem menuItem = new MenuItem("ファイルを開く");
        menuItem.setOnAction(new OpenFileEvent());
        return menuItem;
    }
}
