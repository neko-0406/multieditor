package com.nekosuki.multieditor.markdown.elements;

/**
 * イタリック(斜体)
 */
public class ItalicToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;

    /**
     * Italic Token(斜体)
     * @param parent 親要素
     * @param value 内容
     */
    public ItalicToken(Token parent, String value) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.ITALIC;
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
        return "ItalicToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
