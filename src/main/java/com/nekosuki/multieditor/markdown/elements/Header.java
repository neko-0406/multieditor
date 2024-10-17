package com.nekosuki.multieditor.markdown.elements;

import com.nekosuki.multieditor.markdown.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Header extends MarkDownElements{
    private Pattern header = Pattern.compile("^(#{1,6})\\s+(.*)$\n");

    @Override
    Token getElement(int id, Token parentToken, String content) {
        return null;
    }

    @Override
    Matcher matchWithElement(String text) {
        return null;
    }
}
