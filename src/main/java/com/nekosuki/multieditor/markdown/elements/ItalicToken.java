package com.nekosuki.multieditor.markdown.elements;

/**
 * イタリック(斜体)
 */
public class ItalicToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final int id;

    /**
     * Italic Token(斜体)
     * @param parent 親要素
     * @param id id
     */
    public ItalicToken(Token parent, int id) {
        this.parent = parent;
        this.type = TokenType.ITALIC;
        this.id = id;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public int getId() {return id;}

    @Override
    public String toString() {
        return "ItalicToken{" +
                "type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}
