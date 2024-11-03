package com.nekosuki.multieditor.markdown.elements;

public class StrikeThroughToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final int id;

    /**
     * StrikeThrough Token(取り消し線)
     * @param parent 親要素
     * @param id id
     */
    public StrikeThroughToken(TextToken parent, int id) {
        this.parent = parent;
        this.type = TokenType.STRIKETHROUGH;
        this.id = id;
    }

    public Token getParent() {
        return parent;
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
        return "StrikeThroughToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", id=" + id +
                '}';
    }
}
