package com.nekosuki.multieditor.markdown;

import com.nekosuki.multieditor.MainApp;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;

public class GenerateHTML {
    private final String baseHtml = """
            <!DOCTYPE html>
            <html lang="jp">
                <head>
                    <meta charset="UTF-8">
                    <base href="%s">
                    <title>Template Html</title>
                    <style>
                        %s
                    </style>
                </head>
                <body>
                    %s
                </body>
            </html>""";

    private final Parser parser;
    private final HtmlRenderer renderer;

    public GenerateHTML() {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.GITHUB);
        options.set(Parser.EXTENSIONS, Arrays.asList(
                TablesExtension.create(),
                StrikethroughExtension.create(),
                TaskListExtension.create(),
                FootnoteExtension.create(),
                TypographicExtension.create(),
                AutolinkExtension.create()
        ));

        this.parser = Parser.builder(options).build();
        this.renderer = HtmlRenderer.builder(options).build();
    }

    public String genHtmlTextsFromMarkDown(String text) {
        String baseUrl = MainApp.getAppConfig().getProperty("current_dir", System.getProperty("user.home"));
        String css = getAppThemeCss();
        Document document = parser.parse(text);
        String html = renderer.render(document);

        return baseHtml.formatted(baseUrl,css,html);
    }

    private String getAppThemeCss() {
       String theme = MainApp.getAppConfig().getProperty("display_theme", "light");
       StringBuilder cssTexts = new StringBuilder();
       String url;
       if (theme.equals("light")) {
           url = Objects.requireNonNull(MainApp.class.getResource("github_dark.css")).toExternalForm();
       }else {
           url = Objects.requireNonNull(MainApp.class.getResource("github_light.css")).toExternalForm();
       }

       try(BufferedReader br = new BufferedReader(new FileReader(url, Charset.defaultCharset()))) {
           String line;
           while((line = br.readLine()) != null) {
               cssTexts.append(line);
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       return cssTexts.toString();
    }
}
