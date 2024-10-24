package com.nekosuki.multieditor.markdown.elements;

public class TextToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;

    /**
     * Text Token(テキスト)
     * @param parent 親要素
     * @param value 内容
     */
    public TextToken(Token parent, String value) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.TEXT;
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
}
