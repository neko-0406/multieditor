package com.nekosuki.multieditor.components.topmenu_items;

import com.nekosuki.multieditor.AppConfig;
import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.tabs.FileType;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import com.sun.tools.javac.Main;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

public class FileFX extends Menu {

    public FileFX() {
        super("ファイル");
        this.getItems().addAll(
            openFile(),
            newTextFile(),
            newMarkDownFile(),
            saveFile(),
            new SeparatorMenuItem(),
            openDir(),
            closeDir()
        );
    }

    private MenuItem closeDir() {
        MenuItem item = new MenuItem("フォルダを閉じる");
        item.setOnAction(event -> {
            TreeItem<FileItem> fileItem = MainApp.getComponents().getCustomTreeView().getRoot();
            if (fileItem != null) {
                MainApp.getComponents().getCustomTreeView().setRoot(null);
                MainApp.getComponents().getRootDirTitlePane().setText("");
                MainApp.getAppConfig().replaceProperty(AppConfig.CURRENT_DIR, "");
                MainApp.getAppConfig().replaceProperty(AppConfig.LAST_OPEN_DIR, "");
                MainApp.getAppConfig().writeProperties();
            }
        });
        return item;
    }

    private MenuItem openDir() {
        MenuItem item = new MenuItem("フォルダを開く");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+O"));
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
            MainApp.getAppConfig().replaceProperty(AppConfig.CURRENT_DIR, file.getAbsolutePath());
            FileTreeItem fileTreeItem = new FileTreeItem(new FileItem(file));
            fileTreeItem.setExpanded(true);
            MainApp.getComponents().getRootDirTitlePane().setText(file.getName());
            MainApp.getComponents().getCustomTreeView().setRoot(fileTreeItem);
            MainApp.getAppConfig().writeProperties();
        });
        return item;
    }

    private MenuItem saveFile() {
        MenuItem item = new MenuItem("保存");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+S"));
        item.setOnAction(event -> {
            TabPane tabPane = MainApp.getComponents().getCustomTabPane();
            Tab tab = tabPane.getSelectionModel().getSelectedItem();

            if (tab instanceof MarkDownTab mTab) {
                mTab.saveFile();
            }
            else if (tab instanceof TextTab tTab) {
                tTab.saveFile();
            }
        });
        return item;
    }

    private MenuItem saveFileAs() {
        MenuItem menuItem = new MenuItem("名前を付けて保存");
        menuItem.setAccelerator(KeyCombination.valueOf("Ctrl+Shift+S"));


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
