package com.nekosuki.multieditor.markdown;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * テキストをAST(木構造)に変換
 */
public class MarkDownLexer {
    private final Pattern allPattern;

    public MarkDownLexer() {
        String allRegex =
                // 見出し heading
                "(^(?<headingHashes>#{1,6})\\s(?<headingText>.*)$)|" +
                // 引用 block quote
                "(^>\\s*(?<blockQuote>.+)$)|" +
                // 順序無しリスト unordered list
                "(^(?<unorderedListBlank>\\s*?)[*\\-+]\\s(?<unorderedListText>.+)$)|" +
                // 順序ありリスト ordered list
                "(^(?<orderedListBlank>\\s*?)\\d+\\.\\s+(?<orderedListText>.+)$)|" +
                // 水平線 horizontal rule
                "(^(?<horizontalRule>\\*{3}|-{3}|_{3})\\s*$)|" +
                // リンク link
                "(\\[(?<linkDesc>.+?)]\\((?<linkUrl>https?://[^ ]+)\\))|" +
                // 画像 image
                "(!\\[(?<imageDesc>.*?)]\\((?<imageUrl>https?|files)://[^ ]+\\))|" +
                // コードブロック code block
                "(^```(?<codeBlock>[\\s\\S]*?)```)|" +
                // イタリック italic
                "(\\*(?<italic1>.*?)\\*|_(?<italic2>.+?)_)|" +
                // 太字 bold
                "(\\*\\*(?<bold1>.+?)\\*\\*|__(?<bold2>.+?)__)|" +
                // 斜線 strikethrough
                "(~~(?<strikethrough>.+?)~~)|" +
                // inline code 埋め込みコードエリア
                "(`(?<inlineText>[^`]+?)`)|" +
                //text テキスト
                "(^(?<normalText>.*?)(?=\\*{1,3}|#{1,6}|-{1,3}|_{1,3}|`{1,3}|>|\\+|\\[|!|$|\u200B))";

        this.allPattern = Pattern.compile(allRegex);
    }

    public Matcher matchRegex(String  in) {
        return this.allPattern.matcher(in);
    }
}
