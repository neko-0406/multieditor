package com.nekosuki.multieditor.markdown.elements;

/**
 * 太字
 */
public class BoldToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final int id;

    /**
     * Bold Token(太字)
     * @param parent 親要素
     * @param id id
     */
    public BoldToken(Token parent, int id) {
        this.parent = parent;
        this.type = TokenType.BOLD;
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
        return "BoldToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", id=" + id +
                '}';
    }
}
