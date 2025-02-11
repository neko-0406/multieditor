package com.nekosuki.multieditor.components.topmenu_items;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.tabs.FileType;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
import com.nekosuki.multieditor.process.file_menu.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;

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
            new SeparatorMenuItem(),
            openDir(),
            closeDir()
        );
    }

    private MenuItem closeDir() {
        MenuItem item = new MenuItem("フォルダを閉じる");
        item.setOnAction(new CloseDirectoryEvent());
        return item;
    }

    private MenuItem openDir() {
        MenuItem item = new MenuItem("フォルダを開く");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+O"));
        item.setOnAction(new OpenDirectoryEvent());
        return item;
    }

    private MenuItem closeFile() {
        MenuItem menuItem = new MenuItem("ファイルを閉じる");
        menuItem.setOnAction(new CloseFileEvent());
        return menuItem;
    }

    private MenuItem saveFile() {
        MenuItem item = new MenuItem("ファイルを保存");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+S"));
        item.setOnAction(new SaveFileEvent());
        return item;
    }

    private MenuItem saveFileAs() {
        MenuItem menuItem = new MenuItem("名前を付けて保存");
        menuItem.setAccelerator(KeyCombination.valueOf("Ctrl+Shift+S"));
        menuItem.setOnAction(new SaveFileAsEvent());
        return menuItem;
    }

    private MenuItem newMarkDownFile() {
        MenuItem item = new MenuItem("新しいマークダウンファイル");
        item.setOnAction(event -> {
            TextInputDialog textInputDialog = new TextInputDialog();
            textInputDialog.setHeaderText("ファイル名を入力");
            textInputDialog.setContentText("ファイル名：");
            textInputDialog.getDialogPane().setPrefWidth(300);
            Optional<String> result = textInputDialog.showAndWait();
            result.ifPresent(name -> {
                MarkDownTab tab = new MarkDownTab();
                String fileName = name;
                if (!name.endsWith(".md") || !name.endsWith(".markdown")){
                    fileName += ".md";
                }
                tab.setText(fileName);
                MainApp.getComponents().getCustomTabPane().getTabs().add(tab);
            });
        });
        return item;
    }

    private MenuItem newTextFile() {
        MenuItem item = new MenuItem("新しいテキストファイル");
        item.setOnAction(event -> {
            TextInputDialog textInputDialog = new TextInputDialog();
            textInputDialog.setHeaderText("ファイル名を入力");
            textInputDialog.setContentText("ファイル名：");
            textInputDialog.getDialogPane().setPrefWidth(300);
            Optional<String> result = textInputDialog.showAndWait();
            result.ifPresent(name -> {
                TextTab tab = new TextTab();
                tab.setText(name);
                MainApp.getComponents().getCustomTabPane().getTabs().add(tab);
            });
        });
        return item;
    }

    private MenuItem openFile() {
        MenuItem menuItem = new MenuItem("ファイルを開く");
        menuItem.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File(System.getProperty("user.home")));
            chooser.setTitle("OpenFile");
            File file = chooser.showOpenDialog(null);
            if (file != null) {
                String filetype = FileType.getFileType(file);
                Tab tab;
                if (filetype.equals(FileType.MARKDOWN)) {
                    tab = new MarkDownTab(file);
                }
                else if (filetype.equals(FileType.TEXT)) {
                    tab = new TextTab(file);
                }
                else {
                    tab = new Tab();
                }
                MainApp.getComponents().getCustomTabPane().getTabs().add(tab);
            }
        });
        return menuItem;
    }
}
