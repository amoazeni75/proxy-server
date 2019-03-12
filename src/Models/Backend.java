package Models;

import GUI.ListItem;
import GUI.MainFrame;
import GUI.categoryItem;
import GUI.urlItem;

import java.awt.*;
import java.util.ArrayList;

public class Backend {
    public ArrayList <ListItem> categories;
    public ArrayList<ArrayList<ListItem>> urls;
    private Dimension screenDimention;
    private int itemH;
    private int categoryWidth;
    private int urlWidth;
    private MainFrame ui;

    public Backend(Dimension screenD, int urlW, int categoryW, MainFrame gui){
        categories =  new ArrayList<>();
        urls = new ArrayList<>();
        ui = gui;
        screenDimention = screenD;
        itemH = 50;
        categoryWidth = categoryW;
        urlWidth = urlW;
    }

    public void addCategory(String categoryName){
        categories.add(new categoryItem(new Dimension(categoryWidth, itemH), categoryName));
        ArrayList<ListItem> tmpUrl = new ArrayList<>();
        urls.add(tmpUrl);
        ui.categoriesPanel.itemsList.updateListView(categories);
    }

    public void addNewUrl(String url, int categoryID){
        urls.get(categoryID).add(new urlItem(new Dimension(urlWidth, itemH), url));
        ui.urlsPanel.itemsList.updateListView(urls.get(categoryID));
    }
}
