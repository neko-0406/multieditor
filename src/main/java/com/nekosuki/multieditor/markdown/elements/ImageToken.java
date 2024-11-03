package com.nekosuki.multieditor.markdown.elements;

/**
 * 画像
 */
public class ImageToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final TextToken title;
    private final TextToken url;
    private final int id;

    /**
     * Image Token(画像)
     * @param parent 親要素
     * @param title 画像タイトル
     * @param url url
     * @param id id
     */
    public ImageToken(Token parent, TextToken title, TextToken url, int id) {
        this.parent = parent;
        this.title = title;
        this.url = url;
        this.type = TokenType.IMAGE;
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
    public int getId() {return id;}

    @Override
    public String toString() {
        return "ImageToken{" +
                "parent=" + parent.toString() +
                ", type=" + type +
                ", title=" + title +
                ", url=" + url +
                ", id=" + id +
                '}';
    }
}
