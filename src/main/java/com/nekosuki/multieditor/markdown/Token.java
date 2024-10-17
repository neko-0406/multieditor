package com.nekosuki.multieditor.markdown;

public class Token {
    int id;
    Token parent;
    TokenType elementType;
    State state;
    String content;

    public Token(int id, Token parent, TokenType tokenType, State state, String content) {
        this.id = id;
        this.parent = parent;
        this.state = state;
        this.elementType = tokenType;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getElementType() {
        return elementType;
    }

    public State getState() {
        return state;
    }

    public String getContent() {
        return content;
    }
}
