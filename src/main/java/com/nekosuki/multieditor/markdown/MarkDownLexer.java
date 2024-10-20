package com.nekosuki.multieditor.markdown;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkDownLexer {
    // 各記号の検知パターン
    public final Pattern headingPattern = Pattern.compile("^(#{1,6})\\s+(.+)$");
    public final Pattern boldPattern = Pattern.compile("(\\*\\*|__)(.*?)\\1");
    public final Pattern listPattern = Pattern.compile("^(\\s*[*\\-+]\\s+|\\s*\\d+\\.\\s+)(.+)$");
    public final Pattern italicPattern = Pattern.compile("([*_])([^*_]+)\\1");
    public final Pattern linkPattern = Pattern.compile("\\[([^]]+)]\\(([^)]+)\\)");
    public final Pattern imagePattern = Pattern.compile("!\\[([^]]*)]\\(([^)]+)\\)");
    public final Pattern inlineCodePattern = Pattern.compile("`([^`]+)`");
    public final Pattern codeBlockPattern = Pattern.compile("```[\\s\\S]*?```");

    // それぞれのタイプのトークンを返すメソット
    public Token getHeadingElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.HEADING, State.NONE, content);
    }
    public Token getTextElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.TEXT, State.NONE, content);
    }
    public Token getBoldElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.BOLD, State.NONE, content);
    }
    public Token getListElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.BULLET_LIST, State.LIST, content);
    }
    public Token getItalicElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.ITALIC, State.NONE, content);
    }
    public Token getLinkElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.LINKS, State.NONE, content);
    }
    public Token getImageElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.IMAGES, State.NONE, content);
    }
    public Token getInlineCodeBlockElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.BLOCK_QUOTES, State.BLOCK_QUOTE, content);
    }
    public Token getCodeBlockElement(int id, Token parent, String content) {
        return new Token(id, parent, TokenType.BLOCK_QUOTES, State.BLOCK_QUOTE, content);
    }

    // それぞれの文字列に対するマッチの可否
    public Matcher matchWithBoldElement(String text) {
        return boldPattern.matcher(text);
    }
    public Matcher matchWithHeadingElement(String text) {
        return headingPattern.matcher(text);
    }
    public Matcher matchWithListElement(String text) {
        return listPattern.matcher(text);
    }
    public Matcher matchWithItalicElement(String text) {
        return italicPattern.matcher(text);
    }
    public Matcher matchWithLinkElement(String text) {
        return linkPattern.matcher(text);
    }
    public Matcher matchWithImageElement(String text) {
        return imagePattern.matcher(text);
    }
    public Matcher matchWithInlineCodeBlockElement(String text) {
        return inlineCodePattern.matcher(text);
    }
    public Matcher matchWithCodeBlockElement(String text) {
        return codeBlockPattern.matcher(text);
    }
}
