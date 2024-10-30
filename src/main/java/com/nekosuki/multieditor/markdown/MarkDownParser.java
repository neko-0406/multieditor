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

    private void tokenizeInlineText(Token parent, String text, Stack<Token> tokens, int id) {
        String processText = text;

        while (!processText.isEmpty()) {
            Matcher matcher = lexer.matcherBold(processText);

            id++;
            if (!matcher.find()) {
                TextToken token = new TextToken(parent, matcher.group(1), id);
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
