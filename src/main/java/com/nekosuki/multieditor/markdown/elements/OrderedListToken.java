package com.nekosuki.multieditor.markdown.elements;

public class OrderedListToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final short level;
    private final int id;

    public OrderedListToken(Token parent, String value, short level, int id) {
        this.parent = parent;
        this.value = value;
        this.level = level;
        this.type = TokenType.ORDERED_LIST;
        this.id = id;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderedListToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", level=" + level +
                '}';
    }
}
