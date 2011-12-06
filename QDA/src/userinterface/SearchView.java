/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import checkboxtree.TreeCheckingModel;

/**
 *
 * @author Brittany Nkounkou
 */
public class SearchView extends View {
    
    private TreeCheckingModel filesModel;
    private TreeCheckingModel tagsModel;
    
    public SearchView(String t, TreeCheckingModel fm, TreeCheckingModel tm) {
        super(t);
        filesModel = fm;
        tagsModel = tm;
    }
    
    public TreeCheckingModel getFilesModel() {
        return filesModel;
    }
    
    public TreeCheckingModel getTagsModel() {
        return tagsModel;
    }
}
