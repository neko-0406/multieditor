package com.nekosuki.multieditor.components.tabs;

import javafx.scene.control.Tab;
import lombok.Getter;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TextTab extends Tab implements ITextTab {
    @Getter
    private final CodeArea codeArea;
    private boolean isEdited;

    @Getter
    private final File file;

    public TextTab() {
        super();
        isEdited = false;
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        this.setContent(codeArea);
        file = null;
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
    @Override
    public boolean isEdited() {return isEdited;}

    /**
     * コンストラクタで渡されたファイルを読み込み
     * @param file 表示したいテキストファイル
     * @return String
     */
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
