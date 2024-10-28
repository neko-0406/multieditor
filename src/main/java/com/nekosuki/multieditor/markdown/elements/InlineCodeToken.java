package com.nekosuki.multieditor.markdown.elements;

/**
 * 埋め込みコードブロック
 */
public class InlineCodeToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String value;

    /**
     * inline code
     * @param parent 親要素
     * @param value 内容
     */
    public InlineCodeToken(Token parent, String value) {
        this.parent = parent;
        this.value = value;
        this.type = TokenType.INLINE_CODE;
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
    public String toString() {
        return "InlineCodeToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
