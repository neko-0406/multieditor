package com.nekosuki.multieditor.markdown.elements;

import com.nekosuki.multieditor.markdown.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class MarkDownElements {

    abstract Token getElement(int id, Token parentToken, String content);

    abstract Matcher matchWithElement(String text);
}
