package com.nekosuki.multieditor.components.topmenu_items;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.tabs.FileType;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
import com.sun.tools.javac.Main;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.stage.DirectoryChooser;
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
            new SeparatorMenuItem(),
            openDir()
        );
    }

    private MenuItem openDir() {
        MenuItem item = new MenuItem("フォルダを開く");
        item.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("フォルダを選択");
            String path = MainApp.getAppConfig().getProperty("current_dir", System.getProperty("user.home"));
            if (path.isEmpty()) path = System.getProperty("user.home");
            directoryChooser.setInitialDirectory(new File(path));

            File file = directoryChooser.showDialog(null);
            if (!file.exists()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Folder no found....");
                alert.setHeaderText("選択されたフォルダが存在していません");
                alert.setContentText("");
                alert.show();
                return;
            }
            MainApp.getAppConfig().replaceProperty("current_dir", file.getAbsolutePath());
        });
        return item;
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
