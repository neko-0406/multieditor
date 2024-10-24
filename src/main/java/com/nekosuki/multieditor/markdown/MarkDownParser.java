package com.nekosuki.multieditor.markdown;

import com.nekosuki.multieditor.markdown.elements.Token;

import java.util.Stack;

public class MarkDownParser {
    private final MarkDownLexer markDownLexer;

    public MarkDownParser() {
        this.markDownLexer = new MarkDownLexer();
    }

    public void parse(String text) {

    }

    private void tokenize(String line) {
        Stack<Token> tokens = new Stack<>();
        StringBuilder sb = new StringBuilder(line);
        char word;
        int i = 0;
        while ((!sb.isEmpty())) {
            word = sb.charAt(0);  // 1文字目を回収
            if (isSymbol(word)) {

            }


            sb.deleteCharAt(0);  //1文字目を削除
        }

    }

    private boolean isSymbol(char word) {
        return  (word == '*' || word == '_' || word == '`');
    }
}
