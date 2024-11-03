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
        String testString = "これは***テスト***です。***abc***";
        tokenize(testString);
    }

    private Stack<Token> tokenize(String line) {
        Stack<Token> tokens = new Stack<>(); // 要素トークン入れる用
        int id = 0;
        Token parent = new RootToken(id);

        tokenizeInlineText(id, line, parent, tokens);

        return tokens;
    }

    private void tokenizeInlineText(int id, String text, Token parent, Stack<Token> tokenStack) {
        String processText = text;
        id++;

        Matcher matcher = lexer.matchRegex(processText);

        while (!processText.isEmpty()) {

            if (matcher.find()) {
                // テキスト(text)
                if (matcher.group("normalText") != null) {
                    TextToken token = new TextToken(parent, matcher.group("normalText"), id);
                    tokenStack.push(token);
                    parent = token;
                    processText = matcher.replaceFirst(token.getValue());
                }
                // 見出し(block quote)
                else if (matcher.group("headingHashes") != null && matcher.group("headingText") != null) {
                    byte level = Byte.parseByte(matcher.group("headingHashes"));
                    HeadingToken token = new HeadingToken(level, parent, id);
                    tokenStack.push(token);
                    parent = token;

                    tokenizeInlineText(id, matcher.group("headingText"), parent, tokenStack);
                }
                // 引用(block quote)
                else if (matcher.group("blockQuote") != null) {
                    BlockQuoteToken token = new BlockQuoteToken(parent, id);
                    tokenStack.push(token);
                    parent = token;

                    tokenizeInlineText(id, matcher.group("blockQuote"), parent, tokenStack);
                }
                // 順序無しリスト(unordered list)
                else if (matcher.group("unorderedListText") != null) {
                    int blank = 0;
                    if (matcher.group("unorderedListBlank") != null) {
                        blank = matcher.group("unorderedListBlank").length();
                    }
                    byte level = (byte) (blank / indentLength);
                    UnorderedListToken token = new UnorderedListToken(parent, level, id);
                    tokenStack.push(token);
                    parent = token;

                    tokenizeInlineText(id, matcher.group("unorderedListText"), parent, tokenStack);
                }
                // 順序ありリスト(ordered list)
                else if (matcher.group("orderedListText") != null) {
                    int blank = 0;
                    if (matcher.group("orderedListBlank") != null) {
                        blank = matcher.group("orderedListBlank").length();
                    }
                    byte level = (byte) (blank / indentLength);
                    OrderedListToken token = new OrderedListToken(parent, level, id);
                    tokenStack.push(token);
                    parent = token;

                    tokenizeInlineText(id, matcher.group("orderedListText"), parent, tokenStack);
                }
                // 水平線(horizontal rule)
                else if (matcher.group("horizontalRule") != null) {
                    HorizontalRuleToken token = new HorizontalRuleToken(parent,id);
                    tokenStack.push(token);
                    parent = token;
                }
                // リンク(link)
                else if (matcher.group("linkUrl") != null && matcher.group("linkDesc") != null) {
                    TextToken url = new TextToken(parent, matcher.group("linkUrl"), id);
                    tokenizeInlineText(id, matcher.group("linkDesc"), parent, tokenStack);
                }
                // 画像(image)
                else if (matcher.group("imageLink") != null) {


                }
                // コードブロック(code block)
                else if (matcher.group("codeBlock") != null) {

                }
                // イタリック(italic)
                else if (matcher.group("italic1") != null || matcher.group("italic2") != null) {

                }
                // 太字 (bold)
                else if (matcher.group("bold1") != null || matcher.group("bold2") != null) {

                }
                // 斜線(strikethrough)
                else if (matcher.group("strikethrough") != null) {

                }
                // 埋め込みコード(inline code)
                else if (matcher.group("inlineText") != null) {

                }
            }
        }


    }
}
