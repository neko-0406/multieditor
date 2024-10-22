package com.nekosuki.multieditor.html;

import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.util.Arrays;

public class GenerateHTML {
    private final String baseHtml = """
            <!DOCTYPE html>
            <html lang="jp">
                <head>
                    <meta charset="UTF-8">
                    <base href="%s">
                    <title>Template Html</title>
                </head>
                <body>
                    %s
                </body>
            </html>""";

    private final MutableDataSet options;

    public GenerateHTML() {
        this.options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(

        ));
    }
}
