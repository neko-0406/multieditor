package com.nekosuki.multieditor.components.tabs;

import javafx.scene.control.Tab;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TextTab extends Tab{
    private final CodeArea codeArea;
    private File file;

    public TextTab() {
        super();
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        this.setContent(codeArea);
    }

    public TextTab(File file) {
        super();
        this.file = file;
        this.setText(file.getName());
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        this.setContent(codeArea);
        codeArea.replaceText(readFile(file));
    }

    public void undo() {
        codeArea.undo();
    }
    public void redo() {
        codeArea.redo();
    }

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
