package com.nekosuki.multieditor.components.tabs;

import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
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
            Path filepath = file.toPath();
            Files.writeString(filepath, codeArea.getText());
            if (isEdited) {
                this.setGraphic(null);
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
