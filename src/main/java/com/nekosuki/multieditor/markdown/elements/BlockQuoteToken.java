package com.nekosuki.multieditor.markdown.elements;

/**
 * 引用ブロック
 */
public class BlockQuoteToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final int id;

    /**
     * Block Quote(引用)
     * @param parent 親要素
     * @param value 内容
     * @param id id
     */
    public BlockQuoteToken(Token parent, String value, int id) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.BLOCK_QUOTE;
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

    public int getId() {return id;}

    @Override
    public String toString() {
        return "BlockQuoteToken{" +
                ", type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
