package com.nekosuki.multieditor.components.tabs;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTreeView;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import com.nekosuki.multieditor.components.treeview.FileType;
import com.nekosuki.multieditor.markdown.GenerateHTML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import lombok.Getter;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class MarkDownTab extends Tab implements ITextTab {
    @Getter
    private final CodeArea codeArea;
    private final WebView webView;

    @Getter
    private final File file;
    private boolean isEdited;
    private final static GenerateHTML generateHtml = new GenerateHTML();

    public MarkDownTab() {
        super();
        codeArea = new CodeArea();
        webView = new WebView();
        SplitPane splitPane = new SplitPane();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        splitPane.getItems().addAll(codeArea,webView);
        this.setContent(splitPane);
        isEdited = false;
        this.file = null;
        addEventListener();
    }

    public MarkDownTab(File markdownFile) {
        super();
        codeArea = new CodeArea();
        webView = new WebView();
        this.file = markdownFile;
        this.setText(markdownFile.getName());
        SplitPane splitPane = new SplitPane();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        splitPane.getItems().addAll(codeArea,webView);
        this.setContent(splitPane);
        String markdown = readMarkDownFile(markdownFile);
        codeArea.replaceText(markdown);
        loadHtml(markdown);
        addEventListener();
    }

    @Override
    public boolean isEdited() {return isEdited;}

    private void addEventListener() {
        codeArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isEdited) {
                isEdited = true;
                FontIcon editIcon = new FontIcon("far-edit");
                editIcon.setIconSize(20);
                this.setGraphic(editIcon);
            }
            loadHtml(newValue);
        });
    }

    private void loadHtml(String value) {
        String html = generateHtml.genHtmlTextsFromMarkDown(value);
        webView.getEngine().loadContent(html);
    }

    private static String readMarkDownFile(File file) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
