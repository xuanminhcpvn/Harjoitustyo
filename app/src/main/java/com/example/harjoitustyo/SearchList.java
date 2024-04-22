package com.example.harjoitustyo;

import java.util.ArrayList;

public class SearchList {


    private ArrayList<Search> searches = new ArrayList<>();

    private static SearchList searchesStorage = null;


    public static SearchList getInstance() {
        if (searchesStorage == null) {
            searchesStorage = new SearchList();
        }
        return searchesStorage;
    }

    public void addSearch(Search search) {searches.add(0,search);}

    public ArrayList<Search> getSearches() {
        return searches;
    }
}
