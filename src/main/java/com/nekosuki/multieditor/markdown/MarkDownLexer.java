package com.nekosuki.multieditor.markdown;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkDownLexer {

    private final Pattern boldPattern = Pattern.compile("(\\*\\*|__)(.*?)\\1");


    public Token getTextElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.TEXT, State.NONE, content);
    }

    public Token getBoldElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.BOLD, State.NONE, content);
    }



    public Matcher matchWithBoldElement(String text) {
        return boldPattern.matcher(text);
    }
}
