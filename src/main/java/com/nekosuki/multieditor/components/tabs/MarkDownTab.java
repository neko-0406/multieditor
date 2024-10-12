package com.nekosuki.multieditor.components.tabs;

import javafx.scene.control.Tab;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

public class MarkDownTab extends Tab {
    private final CodeArea codeArea;

    public MarkDownTab() {
        super();
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        this.setContent(codeArea);
    }
}
