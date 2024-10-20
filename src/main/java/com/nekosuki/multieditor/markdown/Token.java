package com.nekosuki.multieditor.markdown;

public class Token {
    private final int id;
    private final Token parent;
    private final TokenType elementType;
    private final State state;
    private final String content;

    public Token(int id, Token parent, TokenType tokenType, State state, String content) {
        this.id = id;
        this.parent = parent;
        this.state = state;
        this.elementType = tokenType;
        this.content = content;
    }

    public static Token genRootToken() {
        return new Token(0, null, TokenType.ROOT,State.NONE, "");
    }

    public int getId() {
        return id;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getElementType() {
        return elementType;
    }

    public State getState() {
        return state;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", elementType=" + elementType +
                ", state=" + state +
                ", parent=" + parent.getId() +
                ", content='" + content + '\'' +
                '}';
    }
}
