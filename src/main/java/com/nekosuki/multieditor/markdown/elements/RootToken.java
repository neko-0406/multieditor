package com.nekosuki.multieditor.markdown.elements;

public class RootToken implements Token{
    private final TokenType type;

    public RootToken() {
        this.type = TokenType.ROOT;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "RootToken{" +
                "type=" + type +
                '}';
    }
}
