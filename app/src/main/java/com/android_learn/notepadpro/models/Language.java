package com.android_learn.notepadpro.models;

public enum Language {
    PERSIAN(0) , ENGLISH(1);

    int language;

    Language(int language)
    {
        this.language = language;
    }

    public int getLanguage()
    {
        return language;
    }

}
