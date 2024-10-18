package com.nekosuki.multieditor.markdown;

public enum TokenType {
    // 文
    TEXT,
    //コードブロック
    CODE_BLOCK,
    //インラインコードブロック
    CODE_SPAN,
    //ヘッダー
    HEADING,
    //補足説明
    NOTE,
    //順序なしリスト
    BULLET_LIST,
    //太字
    BOLD,
    //順序ありリスト
    ORDERED_LIST,
    //説明リスト
    DESCRIPTION_LIST,
    //チェックボックス
    CHECKBOX,
    //引用
    BLOCK_QUOTES,
    //水平線
    HORIZONTAL_RULES,
    //リンク
    LINKS,
    //画像
    IMAGES,
    //テーブル（表）
    TABLE,
    // 斜体
    ITALIC,
    // ROOT Token
    ROOT
}
