package com.nekosuki.multieditor.markdown.elements;

public class OrderedListToken implements Token{
    private final Token parent;
    private final TokenType type;
    private final byte indentLevel;
    private final int id;
//    private final int listNumber;

    public OrderedListToken(Token parent, byte level, int id) {
        this.parent = parent;
        this.indentLevel = level;
        this.type = TokenType.ORDERED_LIST;
        this.id = id;
//        this.listNumber = listNumber;
    }

    public Token getParent() {
        return parent;
    }

    public TokenType getType() {
        return type;
    }

    public byte getIndentLevel() {
        return indentLevel;
    }

    @Override
    public int getId() {
        return id;
    }

//    public int getListNumber() {return listNumber;}

    @Override
    public String toString() {
        return "OrderedListToken{" +
                "parent=" + parent.getType() +
                ", type=" + type +
                ", level=" + indentLevel +
                ", id=" + id +
                '}';
    }
}
