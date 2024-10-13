package com.nekosuki.multieditor.processing;

import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;

public class MarkDownToTextFlow implements Runnable{
    private final String text;
    private final TextFlow textFlow;

    public MarkDownToTextFlow(String text, TextFlow textFlow) {
        this.text = text;
        this.textFlow = textFlow;
    }

    @Override
    public void run() {
        String[] lines = text.split("\n");
        ArrayList<Text> list = new ArrayList<>();
        for (String line : lines) {
            Text text = new Text(line);
            Text newLine = new Text("\n");
            list.add(text);
            list.add(newLine);
        }

        Platform.runLater(() -> {
            textFlow.getChildren().clear();
            textFlow.getChildren().addAll(list);
        });
    }
}
