package com.nekosuki.multieditor.markdown.elements;

/**
 * 埋め込みコードブロック
 */
public class InlineCodeToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final int id;

    /**
     * inline code
     * @param parent 親要素
     * @param id id
     */
    public InlineCodeToken(Token parent, int id) {
        this.parent = parent;
        this.type = TokenType.INLINE_CODE;
        this.id = id;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "InlineCodeToken{" +
                "type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}
