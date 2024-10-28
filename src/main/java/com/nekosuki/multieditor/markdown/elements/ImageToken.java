package com.nekosuki.multieditor.markdown.elements;

/**
 * 画像
 */
public class ImageToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final String title;
    private final String url;

    /**
     * Image Token(画像)
     * @param parent 親要素
     * @param title 画像タイトル
     * @param url url
     */
    public ImageToken(Token parent, String title, String url) {
        this.parent = parent;
        this.title = title;
        this.url = url;
        this.type = TokenType.IMAGE;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ImageToken{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
