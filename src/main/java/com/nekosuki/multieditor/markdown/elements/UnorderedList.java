package com.nekosuki.multieditor.markdown.elements;

public class UnorderedList implements Token{
    private final Token parent;
    private final TokenType type;
    private final short level;
    private final String value;

    public UnorderedList(Token parent, String value, short level) {
        this.parent = parent;
        this.value = value;
        this.level = level;
        this.type = TokenType.UNORDERED_LIST;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getType() {
        return type;
    }

    public short getLevel() {
        return level;
    }

    public String getValue() {
        return value;
    }
}
