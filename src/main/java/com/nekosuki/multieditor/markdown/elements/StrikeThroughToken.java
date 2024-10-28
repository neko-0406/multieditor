package com.nekosuki.multieditor.markdown.elements;

public class StrikeThroughToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;

    /**
     * StrikeThrough Token(取り消し線)
     * @param parent 親要素
     * @param value 内容
     */
    public StrikeThroughToken(TextToken parent, String value) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.STRIKETHROUGH;
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
        return "StrikeThroughToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
