/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

/**
 *
 * @author Brittany Nkounkou
 */
public class ConfirmDialog extends AppDialog{
    private String message;
    
    public ConfirmDialog(MainFrame mf, String confirmMessage) {
        super(mf, "Sign In");
        message = confirmMessage;
    }
    
    //TODO
}
