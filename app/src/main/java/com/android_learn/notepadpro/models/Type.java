package com.android_learn.notepadpro.models;

public enum Type {
    ADD(1) , EDIT(2);
    int type;
    Type(int type)
    {
        this.type = type;
    }
    public int getType()
    {
        return type;
    }
}
