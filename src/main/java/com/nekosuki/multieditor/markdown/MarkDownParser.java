package com.nekosuki.multieditor.markdown;

import java.util.Stack;

public class MarkDownParser {

    private final MarkDownLexer lexer = new MarkDownLexer();

    public void convert(String markdown) {
        String[] mdArray = markdown.split("\r\n|\r|\n");
    }
}
