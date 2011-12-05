/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;
import java.util.LinkedList;
import java.util.List;
import userinterface.*;
import model.*;
/**
 *
 * @author Brittany Ro
 */
public class Application {

        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame().setVisible(true);
                
                /**
                 * Example code 
                 */
                
                /*
                //Creating a user
                User user = new User("User","pass");
                
                
                //Initialize project. 
              
                Project p = new Project("NewProject","localPath/path",user);
                
                //Add a source text to the project using importSourceText()
                //Source texts are stored in folders as children of the folders.
                p.importSourceText("QDA/TestTextFile.txt", p.getMainFolder());
                
                //Folders can also be children of other folders!
                Folder f = p.createFolder(p.getMainFolder(), "subfolder");
                p.importSourceText("QDA/file2.txt",f);
                
                List<MarkedUpText> texts = p.getMainFolder().getDescendantTexts();
                for(MarkedUpText t: texts) {
                    //System.out.print(t.getSourceText().getText());
                }
                p.addTagType(p.getRootTag(),"NewTagType");
                
                TextSection selection = new TextSection(0,0,10,0);
                p.addTagInstance("NewTagType", selection);
                //System.out.print();
                
                LinkedList<TagType> tags = new LinkedList<TagType>();
                tags.add((TagType) p.getRootTag().getChildAt(0));
                
                LinkedList<Folder> folders = new LinkedList<Folder>();
                folders.add(p.getMainFolder());
                List<TagInstance> tagInstances = p.search(folders,tags);
                
                for(TagInstance t: tagInstances) {
                    t.print();
                    System.out.print(t.getTaggedText());
                }
                //p.searchTags()
                */
                
                
            }
        });
    }
}
