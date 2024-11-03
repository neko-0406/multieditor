package com.nekosuki.multieditor.markdown.elements;

public class RootToken implements Token{
    private final TokenType type;
    private final int id;

    public RootToken(int id) {
        this.type = TokenType.ROOT;
        this.id = id;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RootToken{" +
                "type=" + type +
                "id=" + id +
                '}';
    }
}
