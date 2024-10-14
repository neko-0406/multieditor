package com.nekosuki.multieditor.markdown;

public class Token {
    int id;
    Token parentToken;
    TokenType tokenType;
    String content;

    public Token(int id, Token parentToken, String content,  TokenType tokenType) {
        this.id = id;
        this.tokenType = tokenType;
        this.content = content;
        this.parentToken = parentToken;
    }

    public int getId() {
        return id;
    }

    public Token getParentToken() {
        return parentToken;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getContent() {
        return content;
    }
}
