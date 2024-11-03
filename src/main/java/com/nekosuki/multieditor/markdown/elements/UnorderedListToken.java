package com.nekosuki.multieditor.markdown.elements;

public class UnorderedListToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final byte level;
    private final int id;

    public UnorderedListToken(Token parent, byte level, int id) {
        this.parent = parent;
        this.level = level;
        this.type = TokenType.UNORDERED_LIST;
        this.id = id;
    }

    public Token getParent() {
        return parent;
    }

    @Override
    public int getId() {
        return id;
    }

    public TokenType getType() {
        return type;
    }

    public byte getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "UnorderedListToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", level=" + level +
                ", id=" + id +
                '}';
    }
}
