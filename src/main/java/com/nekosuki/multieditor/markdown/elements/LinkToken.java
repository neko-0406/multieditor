package com.nekosuki.multieditor.markdown.elements;

public class LinkToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final TextToken title;
    private final TextToken url;
    private final int id;

    /**
     * Link Token(リンク)
     * @param parent 親要素
     * @param title リンクの説明
     * @param url url
     * @param id id
     */
    public LinkToken(Token parent, TextToken title, TextToken url, int id) {
        this.parent = parent;
        this.type = TokenType.LINK;
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getType() {
        return type;
    }

    public TextToken getTitle() {
        return title;
    }

    public TextToken getUrl() {
        return url;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LinkToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", title=" + title +
                ", url=" + url +
                ", id=" + id +
                '}';
    }
}
