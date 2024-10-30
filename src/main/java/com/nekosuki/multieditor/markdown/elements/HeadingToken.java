package com.nekosuki.multieditor.markdown.elements;

/**
 * 見出しh1 ~ h6
 */
public class HeadingToken implements Token {
    private final byte level;
    private final Token parent;
    private final TokenType type;
    private final String value;
    private final int id;

    /**
     * Heading Token
     * @param level 見出しのレベル
     * @param parent 親要素
     * @param value 内容
     * @param id id
     */
    public HeadingToken(byte level, Token parent, String value, int id) {
        this.parent = parent;
        this.level = level;
        this.value = value;
        this.type = TokenType.HEADING;
        this.id = id;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "HeadingToken{" +
                "level=" + level +
                ", type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
