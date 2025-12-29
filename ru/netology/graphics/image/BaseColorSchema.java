package ru.netology.graphics.image;

public class BaseColorSchema implements TextColorSchema {
    private final char[] symbols = {'#', '$', '@', '%', '*', '+', '-', '`'};
    //private char[] symbols = {'▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-'};

    @Override
    public char convert(int color) {
        return symbols[color * (symbols.length - 1) / 255];
    }
}

