package com.nekosuki.multieditor.markdown;

public class MarkDownLexer {

    private static final String STRONG = "\\*\\*(.*?)\\*\\*|__(.*?)__";


    public Token genTextElement(int id, Token parent, String content) {
        return new Token(id, parent, content, TokenType.TEXT);
    }

    public Token genStrongElement(int id, Token parent, String content) {
        return new Token(id, parent, content, TokenType.STRONG);
    }

    public Token genItalicElement(int id, Token parent, String content) {
        return new Token(id, parent, content, TokenType.ITALIC);
    }

    public boolean matchWithStrongRegexp(String text) {
        return text.matches(STRONG);
    }
}
