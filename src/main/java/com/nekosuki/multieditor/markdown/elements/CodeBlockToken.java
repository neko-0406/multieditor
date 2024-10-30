package com.nekosuki.multieditor.markdown.elements;

/**
 * コードブロック
 */
public class CodeBlockToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final int id;

    /**
     * Code Block(コード置き場)
     * @param parent 親要素
     * @param value 内容
     * @param id id
     */
    public CodeBlockToken(Token parent, String value, int id) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.CODE_BLOCK;
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
        return "CodeBlockToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
