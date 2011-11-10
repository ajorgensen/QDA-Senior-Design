/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

/**
 *
 * @author Brittany Nkounkou
 */
public class ErrorDialog extends AppDialog{
    private String message;
    
    public ErrorDialog(MainFrame mf, String errorMessage) {
        super(mf, "Error");
        message = errorMessage;
    }
    
    //TODO
}
