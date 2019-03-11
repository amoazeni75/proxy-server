package Models;

import GUI.categoryItem;
import GUI.urlItem;

import java.awt.*;
import java.util.ArrayList;

public class Backend {
    private ArrayList <categoryItem> categories;
    private ArrayList<ArrayList<urlItem>> urls;
    private Dimension screenDimention;

    public Backend(Dimension dim){
        categories =  new ArrayList<>();
        urls = new ArrayList<>();
        screenDimention = dim;
    }

    public void addCategory(String categoryName){
        categories.add(new categoryItem())
    }
}
