package com.nekosuki.multieditor.markdown.elements;

/**
 * 太字
 */
public class BoldToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final int id;

    /**
     * Bold Token(太字)
     * @param parent 親要素
     * @param value 内容
     * @param id id
     */
    public BoldToken(Token parent, String value, int id) {
        this.parent = parent;
        this.type = TokenType.BOLD;
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
        return "BoldToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", id=" + id +
                '}';
    }
}
