/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ApplicationStart.java
 *
 * Created on Oct 12, 2011, 8:27:01 PM
 */
package userinterface;

/**
 *
 * @author Brittany Ro
 */
public class MainFrame extends javax.swing.JFrame {

    /** Creates new form ApplicationStart */
    public MainFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        project = new javax.swing.JSplitPane();
        projectData = new javax.swing.JSplitPane();
        repository = new javax.swing.JScrollPane();
        sourceFolder = new javax.swing.JTree();
        tags = new javax.swing.JTabbedPane();
        viewAllTags = new javax.swing.JScrollPane();
        allTags = new javax.swing.JTree();
        editMyTags = new javax.swing.JScrollPane();
        myTags = new javax.swing.JTree();
        views = new javax.swing.JTabbedPane();
        applicationMenu = new javax.swing.JMenuBar();
        projectMenu = new javax.swing.JMenu();
        newProject = new javax.swing.JMenuItem();
        openProject = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        saveProject = new javax.swing.JMenuItem();
        saveAsProject = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mergeProject = new javax.swing.JMenuItem();
        viewVersionHistory = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        manageUsers = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        closeProject = new javax.swing.JMenuItem();
        userMenu = new javax.swing.JMenu();
        editUser = new javax.swing.JMenuItem();
        signOutUser = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpContents = new javax.swing.JMenuItem();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AppName");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N

        project.setDividerLocation(200);

        projectData.setDividerLocation(100);
        projectData.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        repository.setViewportView(sourceFolder);

        projectData.setTopComponent(repository);

        viewAllTags.setViewportView(allTags);

        tags.addTab("View All Tags", viewAllTags);

        editMyTags.setViewportView(myTags);

        tags.addTab("Edit My Tags", editMyTags);

        projectData.setRightComponent(tags);

        project.setLeftComponent(projectData);
        project.setRightComponent(views);

        projectMenu.setText("Project");

        newProject.setText("New");
        projectMenu.add(newProject);

        openProject.setText("Open");
        projectMenu.add(openProject);
        projectMenu.add(jSeparator1);

        saveProject.setText("Save");
        projectMenu.add(saveProject);

        saveAsProject.setText("Save As");
        projectMenu.add(saveAsProject);
        projectMenu.add(jSeparator3);

        mergeProject.setText("Merge");
        projectMenu.add(mergeProject);

        viewVersionHistory.setText("View Version History");
        projectMenu.add(viewVersionHistory);
        projectMenu.add(jSeparator4);

        manageUsers.setText("Manage Users");
        projectMenu.add(manageUsers);
        projectMenu.add(jSeparator2);

        closeProject.setText("Close");
        projectMenu.add(closeProject);

        applicationMenu.add(projectMenu);

        userMenu.setText("User");

        editUser.setText("Edit Account");
        userMenu.add(editUser);

        signOutUser.setText("Sign Out");
        userMenu.add(signOutUser);
        signOutUser.getAccessibleContext().setAccessibleName("");

        applicationMenu.add(userMenu);

        helpMenu.setText("Help");

        helpContents.setText("Help Contents");
        helpMenu.add(helpContents);

        about.setText("About");
        helpMenu.add(about);

        applicationMenu.add(helpMenu);

        setJMenuBar(applicationMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(project, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(project, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JTree allTags;
    private javax.swing.JMenuBar applicationMenu;
    private javax.swing.JMenuItem closeProject;
    private javax.swing.JScrollPane editMyTags;
    private javax.swing.JMenuItem editUser;
    private javax.swing.JMenuItem helpContents;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem manageUsers;
    private javax.swing.JMenuItem mergeProject;
    private javax.swing.JTree myTags;
    private javax.swing.JMenuItem newProject;
    private javax.swing.JMenuItem openProject;
    private javax.swing.JSplitPane project;
    private javax.swing.JSplitPane projectData;
    private javax.swing.JMenu projectMenu;
    private javax.swing.JScrollPane repository;
    private javax.swing.JMenuItem saveAsProject;
    private javax.swing.JMenuItem saveProject;
    private javax.swing.JMenuItem signOutUser;
    private javax.swing.JTree sourceFolder;
    private javax.swing.JTabbedPane tags;
    private javax.swing.JMenu userMenu;
    private javax.swing.JScrollPane viewAllTags;
    private javax.swing.JMenuItem viewVersionHistory;
    private javax.swing.JTabbedPane views;
    // End of variables declaration//GEN-END:variables
}
