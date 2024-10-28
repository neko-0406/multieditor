package com.nekosuki.multieditor.markdown.elements;

/**
 * 太字
 */
public class BoldToken {
    private final Token parent;
    private final TokenType type;
    private final String value;

    /**
     * Bold Token(太字)
     * @param parent 親要素
     * @param value 内容
     */
    public BoldToken(Token parent, String value) {
        this.parent = parent;
        this.type = TokenType.BOLD;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BoldToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
