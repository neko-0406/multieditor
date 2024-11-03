package com.nekosuki.multieditor.markdown.elements;

/**
 * 水平線
 */
public class HorizontalRuleToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final int id;

    /**
     * Horizontal Rule(水平線)
     * @param parent 親要素
     * @param id id
     */
    public HorizontalRuleToken(Token parent, int id) {
        this.parent = parent;
        this.type = TokenType.HORIZONTAL_RULE;
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
        return "HorizontalRuleToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", id=" + id +
                '}';
    }
}
