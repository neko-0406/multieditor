package com.nekosuki.multieditor.markdown.elements;

/**
 * 見出しh1 ~ h6
 */
public class HeadingToken implements Token {
    private final byte level;
    private final Token parent;
    private final TokenType type;
    private final String value;

    /**
     * Heading Token
     * @param level 見出しのレベル
     * @param parent 親要素
     * @param value 内容
     */
    public HeadingToken(byte level, Token parent, String value) {
        this.parent = parent;
        this.level = level;
        this.value = value;
        this.type = TokenType.HEADING;
    }

    public short getLevel() {
        return level;
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
