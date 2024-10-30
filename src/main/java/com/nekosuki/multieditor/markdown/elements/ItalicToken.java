package com.nekosuki.multieditor.markdown.elements;

/**
 * イタリック(斜体)
 */
public class ItalicToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final int id;

    /**
     * Italic Token(斜体)
     * @param parent 親要素
     * @param value 内容
     * @param id id
     */
    public ItalicToken(Token parent, String value, int id) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.ITALIC;
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
    public int getId() {return id;}

    @Override
    public String toString() {
        return "ItalicToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
