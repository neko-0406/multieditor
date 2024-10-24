package com.nekosuki.multieditor.markdown.elements;

/**
 * コードブロック
 */
public class CodeBlockToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;

    /**
     * Code Block(コード置き場)
     * @param parent 親要素
     * @param value 内容
     */
    public CodeBlockToken(Token parent, String value) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.CODE_BLOCK;
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
}
