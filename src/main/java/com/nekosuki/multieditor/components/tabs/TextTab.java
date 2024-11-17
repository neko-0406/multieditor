package com.nekosuki.multieditor.components.tabs;

import com.nekosuki.multieditor.AppConfig;
import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTreeView;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.stage.DirectoryChooser;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextTab extends Tab{
    private final CodeArea codeArea;
    private boolean isEdited;
    private File file;

    public TextTab() {
        super();
        isEdited = false;
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        this.setContent(codeArea);

        addEventListener();
    }

    public TextTab(File file) {
        super();
        this.file = file;
        this.setText(file.getName());
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        this.setContent(codeArea);
        codeArea.replaceText(readFile(file));

        addEventListener();
    }

    private void addEventListener() {
        this.codeArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isEdited) {
                isEdited = true;
                FontIcon editIcon = new FontIcon("far-edit");
                editIcon.setIconSize(20);
                this.setGraphic(editIcon);
            }
        });
    }

    public void undo() {
        codeArea.undo();
    }
    public void redo() {
        codeArea.redo();
    }
    public boolean isEdited() {return isEdited;}
    public void saveFile() {
        try {
            if (file != null && file.exists() && isEdited) {
                Path filepath = file.toPath();
                Files.writeString(filepath, codeArea.getText());
                this.setGraphic(null);
                isEdited = false;
            }
            else if ((file == null || !file.exists()) && isEdited) {
                String dirPath = MainApp.getAppConfig().getProperty(AppConfig.CURRENT_DIR, "");
                if (dirPath.isEmpty()) {  // フォルダを開いていない場合
                    DirectoryChooser chooser = new DirectoryChooser();
                    chooser.setTitle("保存するフォルダを選択");
                    chooser.setInitialDirectory(new File(System.getProperty("user.home")));
                    File dir = chooser.showDialog(null);
                    if (dir != null) {
                        Path filepath = dir.toPath().resolve(getText());
                        Files.writeString(filepath, codeArea.getText());
                        this.setGraphic(null);
                        isEdited = false;
                    }
                }else {
                    CustomTreeView treeView = MainApp.getComponents().getCustomTreeView();
                    TreeItem<FileItem> fileItem = treeView.getSelectionModel().getSelectedItem();
                    if (fileItem != null) {
                        File dir = fileItem.getValue().getFile();
                        if (dir.isFile()) dir = dir.getParentFile();
                        Path filepath = dir.toPath().resolve(getText());
                        Files.writeString(filepath, codeArea.getText());
                    }
                    FileTreeItem treeItem = new FileTreeItem(new FileItem(new File(dirPath)));
                    treeView.setRoot(treeItem);
                    this.setGraphic(null);
                    isEdited = false;
                }
            }
        }catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ファイルの保存に失敗しました");
            alert.show();
            throw new RuntimeException(e.getMessage());
        }
    }

    public File getFile() {return file;}

    private static String readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringBuilder.toString();
    }
}
