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

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.openide.awt.TabbedPaneFactory;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.*;

/**
 *
 * @author Brittany Nkounkou
 */
public class MainFrame extends JFrame implements PropertyChangeListener{

    /** Creates new from ApplicationStart */
    public MainFrame() {
        helpViewIndex = -1;
        
        checkedElements = new DefaultCheckboxTreeCellRenderer();
        checkedElements.setOpenIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Open Folder.png")));
        checkedElements.setClosedIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Closed Folder.png")));
        checkedElements.setLeafIcon(new ImageIcon(getClass().getResource("/userinterface/icons/File.png")));
        
        checkedTags = new DefaultCheckboxTreeCellRenderer();
        checkedTags.setOpenIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Open Tag Set.png")));
        checkedTags.setClosedIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Closed Tag Set.png")));
        checkedTags.setLeafIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Tag.png")));
        
        initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int) screenSize.getWidth();
        int h = (int) screenSize.getHeight();
        setBounds(w/10, h/10, w*4/5, h*3/4);
        projectData.requestFocusInWindow();
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
        repositoryWindow = new javax.swing.JPanel();
        repository = new javax.swing.JScrollPane();
        sourceFolder = new javax.swing.JTree();
        repositoryTools = new javax.swing.JToolBar();
        importFile = new javax.swing.JButton();
        newFolder = new javax.swing.JButton();
        cutElement = new javax.swing.JButton();
        copyElement = new javax.swing.JButton();
        pasteElement = new javax.swing.JButton();
        renameElement = new javax.swing.JButton();
        deleteElement = new javax.swing.JButton();
        tags = new javax.swing.JTabbedPane();
        viewAllTags = new javax.swing.JScrollPane();
        allTags = new javax.swing.JTree();
        editMyTagsWindow = new javax.swing.JPanel();
        editMyTags = new javax.swing.JScrollPane();
        myTags = new javax.swing.JTree();
        editMyTagsTools = new javax.swing.JToolBar();
        newTag = new javax.swing.JButton();
        newTagSet = new javax.swing.JButton();
        cutTag = new javax.swing.JButton();
        copyTag = new javax.swing.JButton();
        pasteTag = new javax.swing.JButton();
        renameTag = new javax.swing.JButton();
        deleteTag = new javax.swing.JButton();
        views = TabbedPaneFactory.createCloseButtonTabbedPane();
        newSearch = new javax.swing.JButton();
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
        accountSettings = new javax.swing.JMenuItem();
        signInUser = new javax.swing.JMenuItem();
        signOutUser = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpContents = new javax.swing.JMenuItem();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AppName");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(500, 300));
        setName(""); // NOI18N

        project.setDividerLocation(205);

        projectData.setDividerLocation(250);
        projectData.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        repositoryWindow.setLayout(new java.awt.BorderLayout());

        DefaultTreeCellRenderer uncheckedElements = new DefaultTreeCellRenderer();
        uncheckedElements.setOpenIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Open Folder.png")));
        uncheckedElements.setClosedIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Closed Folder.png")));
        uncheckedElements.setLeafIcon(new ImageIcon(getClass().getResource("/userinterface/icons/File.png")));
        sourceFolder.setCellRenderer(uncheckedElements);
        sourceFolder.setEditable(true);
        repository.setViewportView(sourceFolder);

        repositoryWindow.add(repository, java.awt.BorderLayout.CENTER);

        repositoryTools.setFloatable(false);
        repositoryTools.setRollover(true);

        importFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Import File.png"))); // NOI18N
        importFile.setToolTipText("Import File");
        importFile.setFocusable(false);
        importFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        repositoryTools.add(importFile);

        newFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/New Folder.png"))); // NOI18N
        newFolder.setToolTipText("New Folder");
        newFolder.setFocusable(false);
        newFolder.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newFolder.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        repositoryTools.add(newFolder);

        cutElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Cut.png"))); // NOI18N
        cutElement.setToolTipText("Cut");
        cutElement.setFocusable(false);
        cutElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cutElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        repositoryTools.add(cutElement);

        copyElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Copy.png"))); // NOI18N
        copyElement.setToolTipText("Copy");
        copyElement.setFocusable(false);
        copyElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copyElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        repositoryTools.add(copyElement);

        pasteElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Paste.png"))); // NOI18N
        pasteElement.setToolTipText("Paste");
        pasteElement.setFocusable(false);
        pasteElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pasteElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        repositoryTools.add(pasteElement);

        renameElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Rename.png"))); // NOI18N
        renameElement.setToolTipText("Rename");
        renameElement.setFocusable(false);
        renameElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        renameElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        repositoryTools.add(renameElement);

        deleteElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Delete.png"))); // NOI18N
        deleteElement.setToolTipText("Delete");
        deleteElement.setFocusable(false);
        deleteElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        repositoryTools.add(deleteElement);

        repositoryWindow.add(repositoryTools, java.awt.BorderLayout.NORTH);

        projectData.setTopComponent(repositoryWindow);

        DefaultTreeCellRenderer uncheckedTags = new DefaultTreeCellRenderer();
        uncheckedTags.setOpenIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Open Tag Set.png")));
        uncheckedTags.setClosedIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Closed Tag Set.png")));
        uncheckedTags.setLeafIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Tag.png")));
        allTags.setCellRenderer(uncheckedTags);
        viewAllTags.setViewportView(allTags);

        tags.addTab("View All Tags", viewAllTags);

        editMyTagsWindow.setLayout(new java.awt.BorderLayout());

        myTags.setCellRenderer(uncheckedTags);
        myTags.setEditable(true);
        editMyTags.setViewportView(myTags);

        editMyTagsWindow.add(editMyTags, java.awt.BorderLayout.CENTER);

        editMyTagsTools.setFloatable(false);
        editMyTagsTools.setRollover(true);

        newTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/New Tag.png"))); // NOI18N
        newTag.setToolTipText("New Tag");
        newTag.setFocusable(false);
        newTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMyTagsTools.add(newTag);

        newTagSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/New Tag Set.png"))); // NOI18N
        newTagSet.setToolTipText("New Tag Set");
        newTagSet.setFocusable(false);
        newTagSet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newTagSet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMyTagsTools.add(newTagSet);

        cutTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Cut.png"))); // NOI18N
        cutTag.setToolTipText("Cut");
        cutTag.setFocusable(false);
        cutTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cutTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMyTagsTools.add(cutTag);

        copyTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Copy.png"))); // NOI18N
        copyTag.setToolTipText("Copy");
        copyTag.setFocusable(false);
        copyTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copyTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMyTagsTools.add(copyTag);

        pasteTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Paste.png"))); // NOI18N
        pasteTag.setToolTipText("Paste");
        pasteTag.setFocusable(false);
        pasteTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pasteTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMyTagsTools.add(pasteTag);

        renameTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Rename.png"))); // NOI18N
        renameTag.setToolTipText("Rename");
        renameTag.setFocusable(false);
        renameTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        renameTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMyTagsTools.add(renameTag);

        deleteTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Delete.png"))); // NOI18N
        deleteTag.setToolTipText("Delete");
        deleteTag.setFocusable(false);
        deleteTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMyTagsTools.add(deleteTag);

        editMyTagsWindow.add(editMyTagsTools, java.awt.BorderLayout.NORTH);

        tags.addTab("Edit My Tags", editMyTagsWindow);

        projectData.setBottomComponent(tags);

        project.setLeftComponent(projectData);

        views.addPropertyChangeListener(TabbedPaneFactory.PROP_CLOSE, this);
        views.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                viewsStateChanged(evt);
            }
        });
        project.setRightComponent(views);

        newSearch.setText("New Search");
        newSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSearchActionPerformed(evt);
            }
        });

        projectMenu.setText("Project");
        projectMenu.setFocusCycleRoot(true);
        projectMenu.setFocusPainted(true);

        newProject.setText("New");
        newProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectActionPerformed(evt);
            }
        });
        projectMenu.add(newProject);

        openProject.setText("Open");
        openProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openProjectActionPerformed(evt);
            }
        });
        projectMenu.add(openProject);
        projectMenu.add(jSeparator1);

        saveProject.setText("Save");
        projectMenu.add(saveProject);

        saveAsProject.setText("Save As");
        saveAsProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsActionPerformed(evt);
            }
        });
        projectMenu.add(saveAsProject);
        projectMenu.add(jSeparator3);

        mergeProject.setText("Merge");
        projectMenu.add(mergeProject);

        viewVersionHistory.setText("View Version History");
        projectMenu.add(viewVersionHistory);
        projectMenu.add(jSeparator4);

        manageUsers.setText("Manage Users");
        manageUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUsersActionPerformed(evt);
            }
        });
        projectMenu.add(manageUsers);
        projectMenu.add(jSeparator2);

        closeProject.setText("Close");
        closeProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXIT_ON_CLOSE(evt);
            }
        });
        projectMenu.add(closeProject);

        applicationMenu.add(projectMenu);

        userMenu.setText("User");
        userMenu.setFocusCycleRoot(true);
        userMenu.setFocusPainted(true);

        accountSettings.setText("Account Settings");
        accountSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountSettingsActionPerformed(evt);
            }
        });
        userMenu.add(accountSettings);

        signInUser.setText("Sign In");
        signInUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInUserActionPerformed(evt);
            }
        });
        userMenu.add(signInUser);

        signOutUser.setText("Sign Out");
        userMenu.add(signOutUser);
        signOutUser.getAccessibleContext().setAccessibleName("");

        applicationMenu.add(userMenu);

        helpMenu.setText("Help");
        helpMenu.setFocusCycleRoot(true);
        helpMenu.setFocusPainted(true);

        helpContents.setText("Help Contents");
        helpContents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpContentsActionPerformed(evt);
            }
        });
        helpMenu.add(helpContents);

        about.setText("About");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        helpMenu.add(about);

        applicationMenu.add(helpMenu);

        applicationMenu.add(Box.createHorizontalGlue());
        applicationMenu.add(newSearch);

        setJMenuBar(applicationMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(project, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(project, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void helpContentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpContentsActionPerformed
    if (helpViewIndex == -1) {
        addView(new HelpView());
        helpViewIndex = views.getSelectedIndex();
    }
    else {
        views.setSelectedIndex(helpViewIndex);
    }
    views.getSelectedComponent().requestFocusInWindow();
}//GEN-LAST:event_helpContentsActionPerformed

private void newProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectActionPerformed
    NewProjectDialog npd = new NewProjectDialog(this);
    npd.setVisible(true);
    if (npd.hasResults()) {
        String name = npd.getProjectName();
        String path = npd.getProjectPath();
        String admin = npd.getAdministrator();
        char[] password = npd.getPassword();
        //TODO
    }
}//GEN-LAST:event_newProjectActionPerformed

private void manageUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUsersActionPerformed
    ManageUsersDialog mud = new ManageUsersDialog(this);
    mud.setVisible(true);
}//GEN-LAST:event_manageUsersActionPerformed

private void accountSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountSettingsActionPerformed
    AccountSettingsDialog asd = new AccountSettingsDialog(this);
    asd.setVisible(true);
}//GEN-LAST:event_accountSettingsActionPerformed

private void signInUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInUserActionPerformed
    SignInDialog sid = new SignInDialog(this);
    sid.setVisible(true);
}//GEN-LAST:event_signInUserActionPerformed

private void EXIT_ON_CLOSE(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXIT_ON_CLOSE
// TODO add your handling code here:
    System.exit(0); // Exit the application
}//GEN-LAST:event_EXIT_ON_CLOSE

private void openProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openProjectActionPerformed
// TODO add your handling code here:
    OpenProjectDialog opd = new OpenProjectDialog(this);
    opd.setVisible(true);
    if(opd.hasResults()) {
        String user = opd.getUserName();
        char[] password = opd.getPassword();
    }
}//GEN-LAST:event_openProjectActionPerformed

private void SaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAsActionPerformed
// TODO add your handling code here:
    SaveAsProjectDialog sap = new SaveAsProjectDialog(this);
    sap.setVisible(true);
    if(sap.hasResults()) {
        String location = sap.getSaveLocation();
    }
}//GEN-LAST:event_SaveAsActionPerformed

private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
// TODO add your handling code here:
    AboutProjectDialog apd = new AboutProjectDialog(this);
    apd.setVisible(true);
}//GEN-LAST:event_aboutActionPerformed

    private void viewsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_viewsStateChanged
        View view = (View) views.getSelectedComponent();
        if (view instanceof SearchView) {
            repository.setViewportView(((SearchView) view).getFilesTree());
            viewAllTags.setViewportView(((SearchView) view).getTagsTree());
        }
        else {
            repository.setViewportView(sourceFolder);
            viewAllTags.setViewportView(allTags);
        }
    }//GEN-LAST:event_viewsStateChanged

    private void newSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSearchActionPerformed
        CheckboxTree rep = new CheckboxTree();
        rep.setCellRenderer(checkedElements);
        CheckboxTree tag = new CheckboxTree();
        tag.setCellRenderer(checkedTags);
        addView(new SearchView("New Search", rep, tag));
    }//GEN-LAST:event_newSearchActionPerformed

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int indexToDelete = views.indexOfComponent((java.awt.Component)evt.getNewValue());
        if (helpViewIndex == indexToDelete) {
            helpViewIndex = -1;
        }
        views.removeTabAt(indexToDelete);
    }

    public void addView(View v) {
        views.addTab(v.getAbbrv(), null, v, v.getTitle());
        views.setSelectedIndex(views.indexOfComponent(v));
    }
    
    private int helpViewIndex;
    private DefaultCheckboxTreeCellRenderer checkedElements = new DefaultCheckboxTreeCellRenderer();
    private DefaultCheckboxTreeCellRenderer checkedTags = new DefaultCheckboxTreeCellRenderer();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JMenuItem accountSettings;
    private javax.swing.JTree allTags;
    private javax.swing.JMenuBar applicationMenu;
    private javax.swing.JMenuItem closeProject;
    private javax.swing.JButton copyElement;
    private javax.swing.JButton copyTag;
    private javax.swing.JButton cutElement;
    private javax.swing.JButton cutTag;
    private javax.swing.JButton deleteElement;
    private javax.swing.JButton deleteTag;
    private javax.swing.JScrollPane editMyTags;
    private javax.swing.JToolBar editMyTagsTools;
    private javax.swing.JPanel editMyTagsWindow;
    private javax.swing.JMenuItem helpContents;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton importFile;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem manageUsers;
    private javax.swing.JMenuItem mergeProject;
    private javax.swing.JTree myTags;
    private javax.swing.JButton newFolder;
    private javax.swing.JMenuItem newProject;
    private javax.swing.JButton newSearch;
    private javax.swing.JButton newTag;
    private javax.swing.JButton newTagSet;
    private javax.swing.JMenuItem openProject;
    private javax.swing.JButton pasteElement;
    private javax.swing.JButton pasteTag;
    private javax.swing.JSplitPane project;
    private javax.swing.JSplitPane projectData;
    private javax.swing.JMenu projectMenu;
    private javax.swing.JButton renameElement;
    private javax.swing.JButton renameTag;
    private javax.swing.JScrollPane repository;
    private javax.swing.JToolBar repositoryTools;
    private javax.swing.JPanel repositoryWindow;
    private javax.swing.JMenuItem saveAsProject;
    private javax.swing.JMenuItem saveProject;
    private javax.swing.JMenuItem signInUser;
    private javax.swing.JMenuItem signOutUser;
    private javax.swing.JTree sourceFolder;
    private javax.swing.JTabbedPane tags;
    private javax.swing.JMenu userMenu;
    private javax.swing.JScrollPane viewAllTags;
    private javax.swing.JMenuItem viewVersionHistory;
    private javax.swing.JTabbedPane views;
    // End of variables declaration//GEN-END:variables
}
