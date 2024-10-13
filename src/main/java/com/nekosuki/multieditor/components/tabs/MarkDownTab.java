package com.nekosuki.multieditor.components.tabs;

import com.nekosuki.multieditor.processing.MarkDownToTextFlow;
import javafx.animation.PauseTransition;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

public class MarkDownTab extends Tab {
    private final CodeArea codeArea;
    private final TextFlow textFlow;

    public MarkDownTab() {
        super();
        textFlow = new TextFlow();
        codeArea = new CodeArea();
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(codeArea, textFlow);
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        this.setContent(splitPane);

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
        pauseTransition.setOnFinished(event -> {
            Thread thread = new Thread(new MarkDownToTextFlow(codeArea.getText(), textFlow));
            thread.start();
        });

        this.codeArea.textProperty().addListener((obs, oldValue, newValue) -> pauseTransition.playFromStart());
    }
}
