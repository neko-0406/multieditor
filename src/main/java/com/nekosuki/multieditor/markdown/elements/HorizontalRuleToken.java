package com.nekosuki.multieditor.markdown.elements;

/**
 * 水平線
 */
public class HorizontalRuleToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;

    /**
     * Horizontal Rule(水平線)
     * @param parent 親要素
     * @param value 内容
     */
    public HorizontalRuleToken(Token parent, String value) {
        this.parent = parent;
        this.type = TokenType.HORIZONTAL_RULE;
        this.value = value;
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
    public String toString() {
        return "HorizontalRuleToken{" +
                ", type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
