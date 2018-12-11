package com.company.model.buffers;

import com.company.model.Language;
import com.company.model.abstraction.Model;

import java.util.ArrayList;
import java.util.List;

public class LanguagesArrayList implements Model {

    private ArrayList<Language> languageArrayList;

    @Override
    public String getModelName() {
        return "LanguagesArrayList";
    }

    public LanguagesArrayList(List<Language> languageArrayList) {
        this.languageArrayList = (ArrayList<Language>) languageArrayList;
    }

    public ArrayList<Language> getLanguageArrayList() {
        return languageArrayList;
    }

    public void setLanguageArrayList(ArrayList<Language> languageArrayList) {
        this.languageArrayList = languageArrayList;
    }
}
