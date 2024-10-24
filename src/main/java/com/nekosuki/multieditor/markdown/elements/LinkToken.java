package com.nekosuki.multieditor.markdown.elements;

public class LinkToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String desc;
    private final String url;

    /**
     * Link Token(リンク)
     * @param parent 親要素
     * @param desc リンクの説明
     * @param url url
     */
    public LinkToken(Token parent, String desc, String url) {
        this.parent = parent;
        this.type = TokenType.LINK;
        this.desc = desc;
        this.url = url;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }
}
