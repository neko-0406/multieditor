package com.nekosuki.multieditor.markdown;

public class RootToken {

    public static Token getRootToken() {
        return new Token(0, null, "", TokenType.ROOT);
    }
}
