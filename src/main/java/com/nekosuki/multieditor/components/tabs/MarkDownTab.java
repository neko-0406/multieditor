package com.nekosuki.multieditor.components.tabs;

import com.nekosuki.multieditor.markdown.GenerateHTML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.web.WebView;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MarkDownTab extends Tab {
    private final CodeArea codeArea;
    private final WebView webView;
    private File file;
    private final static GenerateHTML generateHtml = new GenerateHTML();

    public MarkDownTab() {
        super();
        codeArea = new CodeArea();
        webView = new WebView();
        SplitPane splitPane = new SplitPane();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        splitPane.getItems().addAll(codeArea,webView);
        this.setContent(splitPane);
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
        addEventListener();
        String markdown = readMarkDownFile(markdownFile);
        codeArea.replaceText(markdown);
        loadHtml(markdown);
    }
    public void undo() {
        codeArea.undo();
    }
    public void redo() {
        codeArea.redo();
    }

    private void addEventListener() {
        codeArea.textProperty().addListener((observable, oldValue, newValue) -> loadHtml(newValue));
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
