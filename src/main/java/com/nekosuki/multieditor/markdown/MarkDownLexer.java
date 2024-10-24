package com.nekosuki.multieditor.markdown;

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


    public MarkDownLexer() {
        this.headingRegex = "^(#{1,6})\\s*(.+)$";
        this.italicRegex = "\\*(.+?)\\*|_(.+?)_";
        this.boldRegex = "\\*\\*(.+?)\\*\\*|__(.+?)__";
        this.boldAndItalicRegex = "\\*\\*\\*(.+?)\\*\\*\\*";
        this.strikethroughRegex = "~~(.+?)~~";
        this.linkRegex = "\\[(.+?)\\]\\((https?:\\/\\/[^\\s]+)\\)";
        this.imageRegex = "!\\[(.*?)\\]\\(https:?\\/\\/[^\\s]+\\)";
        this.inlineCodeRegex = "`([^`]+?)`";
        this.codeBlockRegex = "```[\\s\\S]*?```";
        this.blockQuoteRegex = "^>\\s*(.+)$";
        this.unorderedListRegex = "^[\\*\\-\\+]\\s(.+)$";
        this.orderedListRegex = "^\\d+\\.\\s+(.+)$";
        this.horizontalRuleRegex = "^(\\*\\*\\*|---|___)\\s*$";
    }
}
