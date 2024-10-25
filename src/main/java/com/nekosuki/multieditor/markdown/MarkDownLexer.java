package com.nekosuki.multieditor.markdown;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * テキストをAST(木構造)に変換
 */
public class MarkDownLexer {
    private final String headingRegex; // 見出しh1 ~ h6
    private final String italicRegex; // 強調(Italic)
    private final String boldRegex; // 太字
    private final String boldAndItalicRegex; // 太字＋強調
    private final String strikethroughRegex; // 取り消し線
    private final String linkRegex; // リンク
    private final String imageRegex; // 画像
    private final String inlineCodeRegex; // '`'で囲まれた方
    private final String codeBlockRegex; // '```'で囲まれた方
    private final String blockQuoteRegex; // 引用
    private final String unorderedListRegex; // 順番なしリスト
    private final String orderedListRegex; // 順番ありリスト
    private final String horizontalRuleRegex; // 水平線

    private final Pattern headingPattern;
    private final Pattern italicPattern;
    private final Pattern boldPattern;
    private final Pattern boldAndItalicPattern;
    private final Pattern strikethroughPattern;
    private final Pattern linkPattern;
    private final Pattern imagePattern;
    private final Pattern inlineCodePattern;
    private final Pattern codeBlockPattern;
    private final Pattern blockQuotePattern;
    private final Pattern unorderedListPattern;
    private final Pattern orderedListPattern;
    private final Pattern horizontalPattern;

    public MarkDownLexer() {
        // 行頭から使うやつ
        this.headingRegex = "^(#{1,6})\\s*(.+)$"; // #
        this.blockQuoteRegex = "^>\\s*(.+)$";  // >
        this.unorderedListRegex = "^(\\s*[*\\-+])\\s(.+)$";  // * or - or +
        this.orderedListRegex = "^(\\s*\\d+\\.)\\s+(.+)$";  // n.
        this.horizontalRuleRegex = "^(\\*\\*\\*|---|___)\\s*$";  // - or * or _

        // 文中に埋め込み可能
        this.linkRegex = "\\[(.+?)]\\((https?://[^ ]+)\\)";
        this.imageRegex = "!\\[(.*?)]\\((https?|files)://[^ ]+\\)";
        this.codeBlockRegex = "```[\\s\\S]*?```";
        this.italicRegex = "\\*(.+?)\\*|_(.+?)_";
        this.boldRegex = "\\*\\*(.+?)\\*\\*|__(.+?)__";
        this.boldAndItalicRegex = "\\*\\*\\*(.+?)\\*\\*\\*";
        this.strikethroughRegex = "~~(.+?)~~";
        this.inlineCodeRegex = "`([^`]+?)`";

        this.headingPattern = Pattern.compile(headingRegex);
        this.italicPattern = Pattern.compile(italicRegex);
        this.boldPattern = Pattern.compile(boldRegex);
        this.boldAndItalicPattern = Pattern.compile(boldAndItalicRegex);
        this.strikethroughPattern = Pattern.compile(strikethroughRegex);
        this.linkPattern = Pattern.compile(linkRegex);
        this.imagePattern = Pattern.compile(imageRegex);
        this.inlineCodePattern = Pattern.compile(inlineCodeRegex);
        this.codeBlockPattern = Pattern.compile(codeBlockRegex);
        this.blockQuotePattern = Pattern.compile(blockQuoteRegex);
        this.unorderedListPattern = Pattern.compile(unorderedListRegex);
        this.orderedListPattern = Pattern.compile(orderedListRegex);
        this.horizontalPattern = Pattern.compile(horizontalRuleRegex);
    }

    public boolean matchHeading(String t) {
        return t.matches(headingRegex);
    }
    public boolean matchItalic(String t) {
        return t.matches(italicRegex);
    }
    public boolean matchBold(String t) {
        return t.matches(boldRegex);
    }
    public boolean matchBoldAndItalic(String t) {
        return t.matches(boldAndItalicRegex);
    }
    public boolean matchStrikethrough(String t) {
        return t.matches(strikethroughRegex);
    }
    public boolean matchLink(String t) {
        return t.matches(linkRegex);
    }
    public boolean matchImage(String t) {
        return t.matches(imageRegex);
    }
    public boolean matchInlineCode(String t) {
        return t.matches(inlineCodeRegex);
    }
    public boolean matchCodeBlock(String t) {
        return t.matches(codeBlockRegex);
    }
    public boolean matchBlockQuote(String t) {
        return t.matches(blockQuoteRegex);
    }
    public boolean matchUnOrderedList(String t) {
        return t.matches(unorderedListRegex);
    }
    public boolean matchOrderedList(String t) {
        return t.matches(orderedListRegex);
    }
    public boolean matchHorizontalRule(String t) {
        return t.matches(horizontalRuleRegex);
    }

    public Matcher matcherHeading(String input) {
        return headingPattern.matcher(input);
    }
    public Matcher matcherItalic(String input) {
        return italicPattern.matcher(input);
    }
    public Matcher matcherBold(String input) {
        return boldPattern.matcher(input);
    }
    public Matcher matcherBoldAndItalic(String in) {
        return boldAndItalicPattern.matcher(in);
    }
    public Matcher matcherStrikethrough(String in) {
        return strikethroughPattern.matcher(in);
    }
    public Matcher matcherLink(String in) {
        return linkPattern.matcher(in);
    }
    public Matcher matcherImage(String in) {
        return imagePattern.matcher(in);
    }
    public Matcher matcherInlineCode(String in) {
        return inlineCodePattern.matcher(in);
    }
    public Matcher matcherCodeBlock(String in) {
        return codeBlockPattern.matcher(in);
    }
    public Matcher matcherBlockQuote(String in) {
        return blockQuotePattern.matcher(in);
    }
    public Matcher matcherUnorderedList(String in) {
        return unorderedListPattern.matcher(in);
    }
    public Matcher matcherOrderedList(String in) {
        return orderedListPattern.matcher(in);
    }
    public Matcher matcherHorizontalRule(String in) {
        return horizontalPattern.matcher(in);
    }
}
