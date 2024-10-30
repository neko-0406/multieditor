package com.nekosuki.multieditor.markdown.elements;

public class StrikeThroughToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final int id;

    /**
     * StrikeThrough Token(取り消し線)
     * @param parent 親要素
     * @param value 内容
     */
    public StrikeThroughToken(TextToken parent, String value, int id) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.STRIKETHROUGH;
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
        return "StrikeThroughToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
