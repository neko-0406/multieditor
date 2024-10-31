package com.nekosuki.multieditor.markdown.elements;

public class BoldAndItalicToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final int id;

    /**
     * Bold and Italic token
     * @param parent 親要素
     * @param value 内容
     * @param id id
     */
    public BoldAndItalicToken(Token parent, String value, int id) {
        this.parent = parent;
        this.value = value;
        this.id = id;
        this.type = TokenType.BOLD_AND_ITALIC;
    }

    public Token getParent() {
        return parent;
    }

    @Override
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
        return "BoldAndItalicToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", id=" + id +
                '}';
    }
}
