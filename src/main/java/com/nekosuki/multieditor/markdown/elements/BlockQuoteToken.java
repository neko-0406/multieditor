package com.nekosuki.multieditor.markdown.elements;

/**
 * 引用ブロック
 */
public class BlockQuoteToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final int id;

    /**
     * Block Quote(引用)
     * @param parent 親要素
     * @param id id
     */
    public BlockQuoteToken(Token parent, int id) {
        this.parent = parent;
        this.type = TokenType.BLOCK_QUOTE;
        this.id = id;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getType() {
        return type;
    }

    public int getId() {return id;}

    @Override
    public String toString() {
        return "BlockQuoteToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", id=" + id +
                '}';
    }
}
