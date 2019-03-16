package Models;

import GUI.ListItem;
import GUI.MainFrame;
import GUI.categoryItem;
import GUI.urlItem;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Backend {
    public ArrayList <ListItem> categories;
    public ArrayList<ArrayList<ListItem>> urls;
    private Dimension screenDimention;
    private int itemH;
    private int categoryWidth;
    private int urlWidth;
    public MainFrame ui;
    public int currentSelectedCategory;

    public Backend(Dimension screenD, int urlW, int categoryW, MainFrame gui){
        categories =  new ArrayList<>();
        urls = new ArrayList<>();
        ui = gui;
        screenDimention = screenD;
        itemH = 50;
        currentSelectedCategory = -1;
        categoryWidth = categoryW;
        urlWidth = urlW;
    }

    /**
     * add new category
     * @param categoryName
     */
    public void addCategory(String categoryName){
        categories.add(new categoryItem(new Dimension(categoryWidth, itemH), categoryName));
        categories.get(categories.size() - 1).addMouseListener(ui.categoriesPanel);
        ((categoryItem)(categories.get(categories.size() - 1))).selectChk.addActionListener(ui.categoriesPanel);
        ArrayList<ListItem> tmpUrl = new ArrayList<>();
        urls.add(tmpUrl);
        ui.categoriesPanel.itemsList.updateListView(categories);
    }

    /**
     * add new url to specified category
     * @param url
     * @param categoryID
     */
    public void addNewUrl(String url, int categoryID){
        urls.get(categoryID).add(new urlItem(new Dimension(urlWidth, itemH), url));
        ui.urlsPanel.itemsList.updateListView(urls.get(categoryID));
        ((urlItem)urls.get(categoryID).get(urls.get(categoryID).size() - 1)).deleteBTN.addMouseListener(ui.urlsPanel);
        if(((categoryItem)(categories.get(categoryID))).selectChk.isSelected()){
            ((urlItem)urls.get(categoryID).get(urls.get(categoryID).size() - 1)).selectChk.setSelected(true);
        }
        ui.categoriesPanel.setSelected(null, categoryID);
    }

    /**
     * delete url from specified category
     * @param catID
     * @param UrlID
     */
    public void deleteURL(int catID, int UrlID){
        urls.get(catID).remove(UrlID);
        ui.urlsPanel.itemsList.updateListView(urls.get(catID));
    }

    /**
     * load saved categories and all urls belong to it
     */
    public void loadDataFromFile(){
        Path p = Paths.get("./AppData");
        if(Files.exists(p)){
            final File folder = new File("./AppData");
            for (final File fileEntry : folder.listFiles()) {
                //create category
                addCategory(fileEntry.getName().substring(0, fileEntry.getName().lastIndexOf('.')));
                int catID = categories.size() - 1;
                //add urls
                try(BufferedReader br = new BufferedReader(new FileReader(fileEntry.getAbsolutePath()))) {
                    for(String line; (line = br.readLine()) != null; ) {
                        addNewUrl(line, catID);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * creat a text file for each category and save all urls belong to it line by line
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void saveDataToFile() throws FileNotFoundException, UnsupportedEncodingException {
        final File folder = new File("./AppData");
        for (final File fileEntry : folder.listFiles())
            fileEntry.delete();

        for (int i = 0; i < categories.size(); i++) {
            PrintWriter writer = new PrintWriter("./AppData/" + ((categoryItem)categories.get(i)).getCategoryName() + ".txt", "UTF-8");
            for (int j = 0; j < urls.get(i).size(); j++)
                writer.println(((urlItem)urls.get(i).get(j)).getUrlAddress());
            writer.close();
        }
    }

    public boolean checkValidityOfURL(String address){
        for (int i = 0; i < categories.size(); i++) {
            ArrayList<ListItem> currentURL = urls.get(i);
            for (int j = 0; j < currentURL.size(); j++) {

            }
        }
        return true;
    }

    public void startProxy(){
        Runnable r = new SocketListener(this);
        r.run();
    }
}
