package com.nekosuki.multieditor.markdown;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkDownParser {

    private final MarkDownLexer lexer = new MarkDownLexer();

    public void convert(String markdown) {
        String[] mdArray = markdown.split("\r\n|\r|\n");
        Stack<Token> elements = new Stack<>();

        for (String line : mdArray) {
            elements = tokenize(line, Token.genRootToken());
        }

        System.out.println("Stack<Token> {");
        while(!elements.isEmpty()) {
            System.out.println("\t" + elements.pop());
        }
        System.out.println("}");
    }

    //文字列→Token　1周のみ
    public Stack<Token> tokenize(String line, Token p) {
        int id = 0;
        Token parent = p;
        String editText = line;
        Stack<Token> elements = new Stack<>();


        while (!editText.isEmpty()){
            Matcher matcher = lexer.matchWithHeadingElement(editText);
            id++;
            if (matcher.matches()) {
                Token headerToken = lexer.getHeadingElement(id, parent, matcher.group(1));
                parent = headerToken;
                elements.push(headerToken);
                editText = editText.replace(matcher.group(1), "").trim();
            } else {
                Token textToken = lexer.getTextElement(id, parent, editText);
                parent = textToken;
                elements.push(textToken);
                editText = editText.replace(textToken.getContent(), "");
            }
        }

        return elements;
    }
}
