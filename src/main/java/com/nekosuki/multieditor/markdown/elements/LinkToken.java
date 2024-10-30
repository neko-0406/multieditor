package com.nekosuki.multieditor.markdown.elements;

public class LinkToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String desc;
    private final String url;
    private final int id;

    /**
     * Link Token(リンク)
     * @param parent 親要素
     * @param desc リンクの説明
     * @param url url
     * @param id id
     */
    public LinkToken(Token parent, String desc, String url, int id) {
        this.parent = parent;
        this.type = TokenType.LINK;
        this.desc = desc;
        this.url = url;
        this.id = id;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LinkToken{" +
                "type=" + type +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
