package com.nekosuki.multieditor.markdown.elements;

public class TextToken implements Token{
    private final Token parent;
    private final TokenType type;
    private  String value;
    private final int id;

    /**
     * Text Token(テキスト)
     * @param parent 親要素
     * @param value 内容
     */
    public TextToken(Token parent, String value, int id) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.TEXT;
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

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TextToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", id=" + id +
                '}';
    }
}
