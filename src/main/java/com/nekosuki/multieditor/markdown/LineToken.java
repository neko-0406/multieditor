package com.nekosuki.multieditor.markdown;

import java.util.Stack;

public class LineToken {
    private final Stack<Token> elements;
    private final Token rootToken;

    public LineToken() {
        this.elements = new Stack<>();
        this.rootToken = Token.genRootToken();
    }

    public void convertTokenTree(String text) {

    }
}
