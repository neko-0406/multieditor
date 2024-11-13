package com.nekosuki.multieditor.components.topmenu_items;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.tabs.FileType;
import com.nekosuki.multieditor.components.tabs.MarkDownTab;
import com.nekosuki.multieditor.components.tabs.TextTab;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;

public class FileFX extends Menu {
    private static int i = 0;

    public FileFX() {
        super("ファイル");
        this.getItems().addAll(
            openFile(),
            newFile()
        );
    }

    private MenuItem newFile() {
        MenuItem item = new MenuItem("新規作成");
        item.setAccelerator(KeyCombination.valueOf("Ctrl+N"));
        item.setOnAction(event -> {
            i++;
            TextTab tab = new TextTab();
            tab.setText("Untitled-" + i);
            MainApp.getComponents().getCustomTabPane().getTabs().add(tab);
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
