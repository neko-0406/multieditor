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
        Token parent = new RootToken();
        int i = 0;
        char beforeChar;

        //これは**テスト**です
        // 行頭から始める要素
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
        else if (lexer.matchUnOrderedList(line)) {  //unOrderedList
            Matcher matcher = lexer.matcherUnorderedList(line);
            if (matcher.matches()) {
                String group1 = matcher.group(1);
                String value = group1.substring(group1.length() - 1);
                String indent = group1.replace(value, "");
                byte level = (byte) (indent.length() / indentLength);
                UnorderedListToken token = new UnorderedListToken(parent, value, level);
                parent = token;
                tokens.push(token);
                line = matcher.group(2);
            }
        }
        else if (lexer.matchOrderedList(line)) {  // orderedList
            Matcher matcher = lexer.matcherOrderedList(line);
            if (matcher.matches()) {
                String group1 = matcher.group(1);
                String value = group1.substring(group1.length() -2);
                String indent = group1.replace(value, "");
                byte level = (byte) (indent.length() / indentLength);
                OrderedListToken token = new OrderedListToken(parent, value, level);
                parent = token;
                tokens.push(token);
                line = matcher.group(2);
            }
        }
        else if (lexer.matchHorizontalRule(line)) {
            Matcher matcher = lexer.matcherHorizontalRule(line);
            if (matcher.matches()) {
                HorizontalRuleToken token = new HorizontalRuleToken(parent, matcher.group(1));
                parent = token;
                tokens.push(token);
            }
        }

        StringBuilder sb = new StringBuilder(line);
        StringBuilder text = new StringBuilder();
        for (char word : line.toCharArray()) {

            i++;
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
