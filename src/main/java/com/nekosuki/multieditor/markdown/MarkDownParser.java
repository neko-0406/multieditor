package com.nekosuki.multieditor.markdown;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.markdown.elements.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;

public class MarkDownParser {
    private final MarkDownLexer lexer;
    private final char[] symbols;
    private final byte indentLength;

    public MarkDownParser() {
        this.lexer = new MarkDownLexer();
        this.symbols = new char[]{'*','_','`','#','+','-','>','~'};
        this.indentLength = Byte.parseByte(MainApp.getAppConfig().getProperty("indent_length", "4"));
    }

    public void parse(String text) {
        String testString = "### これは*テスト*です。";
        tokenize(testString);
    }

    private Stack<Token> tokenize(String line) {
        Stack<Token> tokens = new Stack<>(); // 要素トークン入れる用
        int id = 0;
        Token parent = new RootToken(id);
        tokens.push(parent);

        //これは**テスト**です

        return tokens;
    }

    private void tokenizeInlineText(String text, Token parent, int id) {
        Stack<TokenType> tokenTypeStack = new Stack<>();
        Stack<Token> tokenStack = new Stack<>();
        char[] chars = text.toCharArray();
        StringBuilder symbol = new StringBuilder();
        StringBuilder tokenValue = new StringBuilder();
        String tokenSymbol;

        for (char c : chars) {
            if (isSymbol(c)) {  // 記号
                if (!tokenValue.isEmpty()) { // abc/*
                    id++;
                    TextToken textToken = new TextToken(parent, tokenValue.toString(), id);
                    parent = textToken;
                    tokenStack.push(textToken);  // textトークンpush
                    tokenTypeStack.push(textToken.getType());  // textType push
                    tokenValue = new StringBuilder();  // 文字列リセット
                }
                symbol.append(c);
            }else {  // テキスト
                if (!symbol.isEmpty()) { // */abc
                    tokenSymbol = symbol.toString();
                    switch (tokenSymbol) {
                        // italic
                        case "*", "_" -> {
                            id++;
                            ItalicToken italicToken = new ItalicToken(parent, "*", id);
                            parent = italicToken;
                            tokenStack.push(italicToken);
                            tokenTypeStack.push(italicToken.getType());
                        }
                        // bold
                        case "**", "__" -> {
                            id++;
                            BoldToken boldToken = new BoldToken(parent, "**", id);
                            parent = boldToken;
                            tokenStack.push(boldToken);
                            tokenTypeStack.push(boldToken.getType());
                        }
                        // italic and bold
                        case "***", "___" -> {
                            id++;
                            BoldToken boldToken = new BoldToken(parent, "**", id);
                            parent = boldToken;
                            tokenStack.push(boldToken);
                            tokenTypeStack.push(boldToken.getType());
                            id++;
                            ItalicToken italicToken = new ItalicToken(parent, "*", id);
                            parent = italicToken;
                            tokenStack.push(italicToken);
                            tokenTypeStack.push(italicToken.getType());
                        }
                    }
                    symbol = new StringBuilder();
                }
                tokenValue.append(c);
            }
        }
    }

    private boolean isSymbol(char word) {
        boolean bool = false;
        for (char c : symbols) {
            if (word == c) {
                bool = true;
                break;
            }
        }

        return bool;
    }
}
