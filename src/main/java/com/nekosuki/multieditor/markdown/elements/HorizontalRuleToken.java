package com.nekosuki.multieditor.markdown.elements;

/**
 * 水平線
 */
public class HorizontalRuleToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final int id;

    /**
     * Horizontal Rule(水平線)
     * @param parent 親要素
     * @param value 内容
     * @param id id
     */
    public HorizontalRuleToken(Token parent, String value, int id) {
        this.parent = parent;
        this.type = TokenType.HORIZONTAL_RULE;
        this.value = value;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "HorizontalRuleToken{" +
                ", type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
