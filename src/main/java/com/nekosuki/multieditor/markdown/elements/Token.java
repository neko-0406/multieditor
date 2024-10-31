package com.nekosuki.multieditor.markdown.elements;

public interface Token {

    int getId();
    TokenType getType();
    String toString();
}
