package com.nekosuki.multieditor.components.tabs;

import com.nekosuki.multieditor.markdown.GenerateHTML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.text.TextFlow;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

public class MarkDownTab extends Tab {
    private final CodeArea codeArea;
    private final TextFlow textFlow;
    private final static GenerateHTML generateHtml = new GenerateHTML();

    public MarkDownTab() {
        super();
        codeArea = new CodeArea();
        textFlow = new TextFlow();
        SplitPane splitPane = new SplitPane();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        splitPane.getItems().addAll(codeArea,textFlow);
        this.setContent(splitPane);
        addEventListener();
    }

    private void addEventListener() {
        codeArea.textProperty().addListener((observable, oldValue, newValue) -> {
            String html = generateHtml.genHtmlTextsFromMarkDown(newValue);
        });
    }


}
