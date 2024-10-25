package com.nekosuki.multieditor.markdown;

import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.markdown.elements.*;

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

    }

    private void tokenize(String line) {
        Stack<Token> tokens = new Stack<>(); // 要素トークン入れる用
        Stack<TokenType> tokenTypes = new Stack<>(); // 現在の要素選択用
        Token parent = new RootToken();
        int i = 0;
        char word;
        char beforeChar;

        // # This is *test*

        if (lexer.matchHeading(line)) {  // Heading
            Matcher matcher = lexer.matcherHeading(line);
            if (matcher.matches()) {
                byte level = (byte) matcher.group(1).length();
                HeadingToken token = new HeadingToken(level, parent, matcher.group(1));
                parent = token;
                tokens.push(token);
                line = matcher.group(2);
            }
        }
        else if (lexer.matchBlockQuote(line)) {  // BlockQuote
            Matcher matcher = lexer.matcherBlockQuote(line);
            if (matcher.matches()) {
                BlockQuoteToken token = new BlockQuoteToken(parent, matcher.group(1));
                parent = token;
                tokens.push(token);
                line = matcher.group(2);
            }
        }
        else if (lexer.matchUnOrderedList(line)) {
            Matcher matcher = lexer.matcherUnorderedList(line);
            if (matcher.matches()) {
                byte level = (byte) (matcher.group(1).length() / indentLength);
                UnorderedListToken token = new UnorderedListToken(parent, ">", level);
                parent = token;
                tokens.push(token);
                line = matcher.group(2);
            }
        }
        else if (lexer.matchOrderedList(line)) {
            Matcher matcher = lexer.matcherOrderedList(line);
            if (matcher.matches()) {

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
