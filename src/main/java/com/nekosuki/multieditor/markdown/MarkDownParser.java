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
        this.symbols = new char[]{'*','_','`','#','+','-','>','~','\u200B'};
        this.indentLength = Byte.parseByte(MainApp.getAppConfig().getProperty("indent_length", "4"));
    }

    public void parse(String text) {
        String testString = "これは***テスト***です。***abc***";
        tokenize(testString);
    }

    private Stack<Token> tokenize(String line) {
        Stack<Token> tokens = new Stack<>(); // 要素トークン入れる用
        Token parent = new RootToken(0);

        var q = tokenizeInlineText(line, parent);

        //これは**テスト**です

        return tokens;
    }

    private void tokenizeInlineText(String text, Token parent) {
        String processText = text + '\u200B';
        Stack<Token> tokenStack = new Stack<>();
        int id = 1;
        int i = 0;
        char c;
        // 私は*朝に*ご飯を**食べました**。

        while (!processText.isEmpty()) {
            System.out.println(i);
            System.out.println(processText);
            c = processText.charAt(i);

            if (isSymbol(c)) {
                if (i != 0) {
                    String t = processText.substring(0, i);
                    TextToken textToken = new TextToken(parent, t, id);
                    tokenStack.push(textToken);
                    i = 0;
                    processText = processText.replace(t, "");
                }
                // italic and bold or horizontal rule
                else if (processText.startsWith("***") || processText.startsWith("___") || processText.startsWith("---")) {
                        Matcher IBmatcher = lexer.matcherBoldAndItalic(processText);
                        if (IBmatcher.find()) {
                            BoldAndItalicToken token = new BoldAndItalicToken(parent, IBmatcher.group(1), id);
                            tokenStack.push(token);
                            parent = token;
                            processText = IBmatcher.replaceFirst("");
                        }
                        Matcher Hmatcher = lexer.matcherHorizontalRule(processText);
                        if (Hmatcher.matches()) {
                            HorizontalRuleToken token = new HorizontalRuleToken(parent, Hmatcher.group(1), id);
                            tokenStack.push(token);
                            parent = token;
                            processText = Hmatcher.replaceFirst("");
                        }
                }
                // bold
                else if (processText.startsWith("**") || processText.startsWith("__")) {
                    Matcher matcher = lexer.matcherBold(processText);
                    if (matcher.find()) {

                    }
                }
                // italic
                else if (processText.startsWith("*") || processText.startsWith("_")) {}

            } else {
                i++;
            }

            if (c == '\u200B') break;
        }

        while (!tokenStack.isEmpty()) {
            System.out.println(tokenStack.pop());
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
