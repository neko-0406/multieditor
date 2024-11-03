package com.nekosuki.multieditor.markdown.elements;

/**
 * コードブロック
 */
public class CodeBlockToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final int id;

    /**
     * Code Block(コード置き場)
     * @param parent 親要素
     * @param id id
     */
    public CodeBlockToken(Token parent, int id) {
        this.parent = parent;
        this.type = TokenType.CODE_BLOCK;
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
        return "CodeBlockToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", id=" + id +
                '}';
    }
}
