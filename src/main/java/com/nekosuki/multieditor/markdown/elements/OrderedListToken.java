package com.nekosuki.multieditor.markdown.elements;

public class OrderedListToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final short level;

    public OrderedListToken(Token parent, String value, short level) {
        this.parent = parent;
        this.value = value;
        this.level = level;
        this.type = TokenType.ORDERED_LIST;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public short getLevel() {
        return level;
    }
}
