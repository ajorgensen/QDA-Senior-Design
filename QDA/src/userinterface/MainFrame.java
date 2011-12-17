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
import javax.swing.event.TreeSelectionEvent;
import org.openide.awt.TabbedPaneFactory;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import checkboxtree.*;
import java.awt.Component;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import model.*;

/**
 *
 * @author Brittany Nkounkou
 */
public class MainFrame extends JFrame {

    private Project project;
    private User session_user = new User("default");

    /** Creates new from ApplicationStart */
    public MainFrame() {
        project = null;

        helpViewIndex = -1;
        initializeRepository();
        initializeTags();

        initComponents();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int) screenSize.getWidth();
        int h = (int) screenSize.getHeight();
        setBounds(w / 10, h / 10, w * 4 / 5, h * 3 / 4);
        projectData.requestFocusInWindow();
        
        SignInDialog sid = new SignInDialog(this);
        sid.setVisible(true);
        while (!sid.hasResults()) {
            sid.setVisible(true);
        }
        User current_user = new User(sid.getUserName());
        this.session_user = current_user;
    }

    private void initializeRepository() {
        uncheckedRep = new BlankCheckboxTreeCellRenderer();
        uncheckedRep.setOpenIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Open Folder.png")));
        uncheckedRep.setClosedIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Closed Folder.png")));
        uncheckedRep.setLeafIcon(new ImageIcon(getClass().getResource("/userinterface/icons/File.png")));

        checkedRep = new DefaultCheckboxTreeCellRenderer();
        checkedRep.setOpenIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Open Folder.png")));
        checkedRep.setClosedIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Closed Folder.png")));
        checkedRep.setLeafIcon(new ImageIcon(getClass().getResource("/userinterface/icons/File.png")));

        repository = new CheckboxTree();
        repository.setVisible(false);
        repository.setCellRenderer(uncheckedRep);
        repository.setEditable(false);
        repository.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        repository.clearSelection();
        repository.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (repository.getSelectionCount()==1 && ((repository.getSelectionPath().getLastPathComponent()) instanceof Folder)) {
                    importFile.setEnabled(true);
                    newFolder.setEnabled(true);
                }
                else {
                    importFile.setEnabled(false);
                    newFolder.setEnabled(false);
                }
            }        
        });
        repository.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                repositoryMousePressed(evt);
            }
        });
    }

    private void initializeTags() {
        uncheckedTags = new BlankCheckboxTreeCellRenderer();
        uncheckedTags.setOpenIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Tags.png")));
        uncheckedTags.setClosedIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Tags.png")));
        uncheckedTags.setLeafIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Tag.png")));

        checkedTags = new DefaultCheckboxTreeCellRenderer();
        checkedTags.setOpenIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Tags.png")));
        checkedTags.setClosedIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Tags.png")));
        checkedTags.setLeafIcon(new ImageIcon(getClass().getResource("/userinterface/icons/Tag.png")));

        tags = new CheckboxTree();
        tags.setVisible(false);
        tags.setCellRenderer(uncheckedTags);
        tags.setEditable(false);
        tags.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tags.clearSelection();
        tags.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (tags.getSelectionCount()==1) {
                    newTag.setEnabled(true);
                }
                else {
                    newTag.setEnabled(false);
                }
            }        
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        applicationPane = new javax.swing.JSplitPane();
        projectData = new javax.swing.JSplitPane();
        repositoryWindow = new javax.swing.JPanel();
        repositoryLabel = new javax.swing.JLabel();
        repositoryTools = new javax.swing.JToolBar();
        importFile = new javax.swing.JButton();
        newFolder = new javax.swing.JButton();
        cutElement = new javax.swing.JButton();
        copyElement = new javax.swing.JButton();
        pasteElement = new javax.swing.JButton();
        renameElement = new javax.swing.JButton();
        deleteElement = new javax.swing.JButton();
        repositoryPane = new javax.swing.JScrollPane();
        tagsWindow = new javax.swing.JPanel();
        tagsLabel = new javax.swing.JLabel();
        tagsTools = new javax.swing.JToolBar();
        newTag = new javax.swing.JButton();
        newTagSet = new javax.swing.JButton();
        cutTag = new javax.swing.JButton();
        copyTag = new javax.swing.JButton();
        pasteTag = new javax.swing.JButton();
        renameTag = new javax.swing.JButton();
        deleteTag = new javax.swing.JButton();
        tagsPane = new javax.swing.JScrollPane();
        views = TabbedPaneFactory.createCloseButtonTabbedPane();
        newSearch = new javax.swing.JButton();
        applicationMenu = new javax.swing.JMenuBar();
        projectMenu = new javax.swing.JMenu();
        newProject = new javax.swing.JMenuItem();
        openProject = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        saveProject = new javax.swing.JMenuItem();
        commitProject = new javax.swing.JMenuItem();
        mergeProject = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        viewVersionHistory = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        closeProject = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpContents = new javax.swing.JMenuItem();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Parallel");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon(getClass().getResource("/userinterface/icons/Parallel.png")).getImage());
        setMinimumSize(new java.awt.Dimension(500, 300));
        setName(""); // NOI18N

        applicationPane.setDividerLocation(200);

        projectData.setDividerLocation(250);
        projectData.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        repositoryWindow.setLayout(new javax.swing.BoxLayout(repositoryWindow, javax.swing.BoxLayout.Y_AXIS));

        repositoryLabel.setText("Repository");
        repositoryWindow.add(repositoryLabel);

        repositoryTools.setFloatable(false);
        repositoryTools.setRollover(true);
        repositoryTools.setAlignmentX(0.03F);

        importFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Import File.png"))); // NOI18N
        importFile.setToolTipText("Import File");
        importFile.setEnabled(false);
        importFile.setFocusable(false);
        importFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        importFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importFileActionPerformed(evt);
            }
        });
        repositoryTools.add(importFile);

        newFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/New Folder.png"))); // NOI18N
        newFolder.setToolTipText("New Folder");
        newFolder.setEnabled(false);
        newFolder.setFocusable(false);
        newFolder.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newFolder.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createFolderActionPerformed(evt);
            }
        });
        repositoryTools.add(newFolder);

        cutElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Cut.png"))); // NOI18N
        cutElement.setToolTipText("Cut");
        cutElement.setEnabled(false);
        cutElement.setFocusable(false);
        cutElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cutElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cutElement.setVisible(false);
        repositoryTools.add(cutElement);

        copyElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Copy.png"))); // NOI18N
        copyElement.setToolTipText("Copy");
        copyElement.setEnabled(false);
        copyElement.setFocusable(false);
        copyElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copyElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        copyElement.setVisible(false);
        repositoryTools.add(copyElement);

        pasteElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Paste.png"))); // NOI18N
        pasteElement.setToolTipText("Paste");
        pasteElement.setEnabled(false);
        pasteElement.setFocusable(false);
        pasteElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pasteElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pasteElement.setVisible(false);
        repositoryTools.add(pasteElement);

        renameElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Rename.png"))); // NOI18N
        renameElement.setToolTipText("Rename");
        renameElement.setEnabled(false);
        renameElement.setFocusable(false);
        renameElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        renameElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        renameElement.setVisible(false);
        renameElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameElementActionPerformed(evt);
            }
        });
        repositoryTools.add(renameElement);

        deleteElement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Delete.png"))); // NOI18N
        deleteElement.setToolTipText("Delete");
        deleteElement.setEnabled(false);
        deleteElement.setFocusable(false);
        deleteElement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteElement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteElement.setVisible(false);
        repositoryTools.add(deleteElement);

        repositoryWindow.add(repositoryTools);

        repositoryPane.setAlignmentX(0.03F);

        repositoryPane.setViewportView(repository);

        repositoryWindow.add(repositoryPane);

        projectData.setTopComponent(repositoryWindow);

        tagsWindow.setLayout(new javax.swing.BoxLayout(tagsWindow, javax.swing.BoxLayout.Y_AXIS));

        tagsLabel.setText("Tags");
        tagsWindow.add(tagsLabel);

        tagsTools.setFloatable(false);
        tagsTools.setRollover(true);
        tagsTools.setAlignmentX(0.03F);

        newTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/New Tag.png"))); // NOI18N
        newTag.setToolTipText("New Tag");
        newTag.setEnabled(false);
        newTag.setFocusable(false);
        newTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTagActionPerformed(evt);
            }
        });
        tagsTools.add(newTag);

        newTagSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/New Tag Set.png"))); // NOI18N
        newTagSet.setToolTipText("New Tag Set");
        newTagSet.setEnabled(false);
        newTagSet.setFocusable(false);
        newTagSet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newTagSet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newTagSet.setVisible(false);
        tagsTools.add(newTagSet);

        cutTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Cut.png"))); // NOI18N
        cutTag.setToolTipText("Cut");
        cutTag.setEnabled(false);
        cutTag.setFocusable(false);
        cutTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cutTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cutTag.setVisible(false);
        tagsTools.add(cutTag);

        copyTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Copy.png"))); // NOI18N
        copyTag.setToolTipText("Copy");
        copyTag.setEnabled(false);
        copyTag.setFocusable(false);
        copyTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copyTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        copyTag.setVisible(false);
        tagsTools.add(copyTag);

        pasteTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Paste.png"))); // NOI18N
        pasteTag.setToolTipText("Paste");
        pasteTag.setEnabled(false);
        pasteTag.setFocusable(false);
        pasteTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pasteTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pasteTag.setVisible(false);
        tagsTools.add(pasteTag);

        renameTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Rename.png"))); // NOI18N
        renameTag.setToolTipText("Rename");
        renameTag.setEnabled(false);
        renameTag.setFocusable(false);
        renameTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        renameTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        renameTag.setVisible(false);
        renameTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameTagActionPerformed(evt);
            }
        });
        tagsTools.add(renameTag);

        deleteTag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/icons/Delete.png"))); // NOI18N
        deleteTag.setToolTipText("Delete");
        deleteTag.setEnabled(false);
        deleteTag.setFocusable(false);
        deleteTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteTag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteTag.setVisible(false);
        tagsTools.add(deleteTag);

        tagsPane.setViewportView(tags);

        tagsWindow.add(tagsTools);

        tagsPane.setAlignmentX(0.03F);
        tagsWindow.add(tagsPane);

        projectData.setRightComponent(tagsWindow);

        applicationPane.setLeftComponent(projectData);

        views.addPropertyChangeListener(TabbedPaneFactory.PROP_CLOSE,
            new PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent evt) {
                    viewsPropertyChange(evt);
                }
            });
            views.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    viewsStateChanged(evt);
                }
            });
            applicationPane.setRightComponent(views);

            newSearch.setText("New Search");
            newSearch.setEnabled(false);
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
            saveProject.setEnabled(false);
            saveProject.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    saveProjectActionPerformed(evt);
                }
            });
            projectMenu.add(saveProject);

            commitProject.setText("Commit");
            commitProject.setEnabled(false);
            commitProject.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    commitProjectActionPerformed(evt);
                }
            });
            projectMenu.add(commitProject);

            mergeProject.setText("Merge");
            mergeProject.setEnabled(false);
            mergeProject.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mergeProjectActionPerformed(evt);
                }
            });
            projectMenu.add(mergeProject);
            projectMenu.add(jSeparator3);

            viewVersionHistory.setText("View Version History");
            viewVersionHistory.setEnabled(false);
            projectMenu.add(viewVersionHistory);
            projectMenu.add(jSeparator4);

            closeProject.setText("Close");
            closeProject.setEnabled(false);
            closeProject.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    EXIT_ON_CLOSE(evt);
                }
            });
            projectMenu.add(closeProject);

            applicationMenu.add(projectMenu);

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

            applicationMenu.add(newSearch);

            setJMenuBar(applicationMenu);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(applicationPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(applicationPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
            );

            getAccessibleContext().setAccessibleName("");

            pack();
        }// </editor-fold>//GEN-END:initComponents

private void helpContentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpContentsActionPerformed
    if (helpViewIndex == -1) {
        addView(new HelpView());
        helpViewIndex = views.getSelectedIndex();
    } else {
        views.setSelectedIndex(helpViewIndex);
    }
    views.getSelectedComponent().requestFocusInWindow();
}//GEN-LAST:event_helpContentsActionPerformed

    /**
     * When New Project menu button is pressed it opens a NewProjectDialog box
     * User then enters the required information
     * That information is then used to create a new project and all the information
     * is passed onto setUp
     * @param evt
     */
private void newProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectActionPerformed
    NewProjectDialog npd = new NewProjectDialog(this);
    npd.setVisible(true);
    if (npd.hasResults()) {
        String name = npd.getProjectName();
        String path = npd.getProjectPath();
        String sourcePath = npd.getSourcePath();
        
        Project p = new Project("", path+java.io.File.separator+name, this.session_user);
        p.setCurrentUser(this.session_user);
        openProject(p);
    }
}//GEN-LAST:event_newProjectActionPerformed

    /**
     * When user presses Close it exits the application.
     * Right now there is no check to make sure the user saved
     * before the project is closed
     * @param evt
     */
private void EXIT_ON_CLOSE(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXIT_ON_CLOSE
    closeProject();
}//GEN-LAST:event_EXIT_ON_CLOSE

private void openProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openProjectActionPerformed
    OpenProjectDialog opd = new OpenProjectDialog(this);
    opd.setVisible(true);

    if (opd.hasResults()) {
        String working_dir = opd.getWorkingDir();

        Project p = new Project("", working_dir, this.session_user);
        p.setCurrentUser(session_user);
        openProject(p);
    }

// No longer asks for username and password when opening a project
// if(opd.hasResults()) {
// String user = opd.getUserName();
// char[] password = opd.getPassword();
// }
}//GEN-LAST:event_openProjectActionPerformed

    /*
     * The project should already have been loaded into memory
     */
    private void openProject(Project p) {
        if (p != null) {
            this.project = p;
            
            newProject.setEnabled(false);
            openProject.setEnabled(false);
            closeProject.setEnabled(true);
            // Removed User Menu (Future Implementation?)
            // userMenu.setEnabled(true);

            saveProject.setEnabled(true);
            commitProject.setEnabled(true);
            //saveAsProject.setEnabled(true); // This option removed (future implemntation?)
            mergeProject.setEnabled(true);
            viewVersionHistory.setEnabled(true);

            newSearch.setEnabled(true);

            repository.setModel(new DefaultTreeModel(p.getMainFolder()));
            repository.setVisible(true);
            tags.setModel(new DefaultTreeModel(p.getRootTag()));
            tags.setVisible(true);

            this.repaint();
        }
    }

    /**
     * Signs out the current user and then repaints the application
     * This causes certain menu items to become grayed out
     */
    private void closeProject() {
        project.setCurrentUser(null);

        views.removeAll();

        newProject.setEnabled(true);
        openProject.setEnabled(true);
        saveProject.setEnabled(false);
        commitProject.setEnabled(false);
        //saveAsProject.setEnabled(false); // This option removed (future ipmlementation?)
        mergeProject.setEnabled(false);
        viewVersionHistory.setEnabled(false);
        //manageUsers.setEnabled(false); // This option removed (future ipmlementation?)
        // Removed User Menu Option (Future Implementatin?)
        // signInUser.setEnabled(true);
        // signOutUser.setEnabled(false);

        newSearch.setEnabled(false);

        repository.clearSelection();
        repository.setVisible(false);

        tags.clearSelection();
        tags.setVisible(false);
        
        project = null;

        this.repaint();
    }
    /*
     * Opens the About dialogs box describing the program
     */
private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
    AboutProjectDialog apd = new AboutProjectDialog(this);
    apd.setVisible(true);
}//GEN-LAST:event_aboutActionPerformed

    private void viewsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_viewsStateChanged
        View view = (View) views.getSelectedComponent();
        if (view instanceof SearchView) {
            repository.setCellRenderer(checkedRep);
            repository.setCheckingModel(((SearchView) view).getFilesModel());
            tags.setCellRenderer(checkedTags);
            tags.setCheckingModel(((SearchView) view).getTagsModel());
        } else {
            repository.setCellRenderer(uncheckedRep);
            if (view instanceof SourceTextView) {
                tags.setCellRenderer(checkedTags);
                tags.setCheckingModel(((SourceTextView) view).getTagsModel());
            } else {
                tags.setCellRenderer(uncheckedTags);
            }
        }
        repaint();
    }//GEN-LAST:event_viewsStateChanged

    private void viewsPropertyChange(PropertyChangeEvent evt) {
        int indexToDelete = views.indexOfComponent((java.awt.Component) evt.getNewValue());
        if (helpViewIndex == indexToDelete) {
            helpViewIndex = -1;
        }
        views.removeTabAt(indexToDelete);
    }

    private void renameElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameElementActionPerformed
        JTree rep = (JTree) repositoryPane.getViewport().getView();
        TreePath path = rep.getSelectionPath();
        if (path == null) {
            ErrorDialog ed = new ErrorDialog(this, "Please select a File or Folder to rename.");
            ed.setVisible(true);
        } else {
            rep.startEditingAtPath(rep.getSelectionPath());
        }
    }//GEN-LAST:event_renameElementActionPerformed

    private void renameTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameTagActionPerformed
        TreePath path = tags.getSelectionPath();
        if (path == null) {
            ErrorDialog ed = new ErrorDialog(this, "Please select a Tag or Tag Set to rename.");
            ed.setVisible(true);
        } else {
            tags.startEditingAtPath(tags.getSelectionPath());
        }
    }//GEN-LAST:event_renameTagActionPerformed

    public void openSourceTextView(MarkedUpText mut) {
        Component[] v = views.getComponents();
        for (int i = 0; i < v.length; i++) {
            if (v[i] instanceof SourceTextView) {
                if (((SourceTextView) v[i]).getMarkedUpText() == mut) {
                    views.setSelectedComponent(v[i]);
                    return;
                }
            }
        }
        TreeCheckingModel tag = new DefaultTreeCheckingModel(tags);
        tag.addCheckingPath(tags.getPathForRow(0));
        addView(new SourceTextView(this, mut, tag));
    }

    private void repositoryMousePressed(java.awt.event.MouseEvent evt) {
        JTree rep = (JTree) repositoryPane.getViewport().getView();
        int selRow = rep.getRowForLocation(evt.getX(), evt.getY());
        TreePath alpha = rep.getSelectionPath();
        if (alpha != null) {
            Object beta = alpha.getLastPathComponent();
            // check if node
            if (beta != null && beta instanceof MarkedUpText) { // it is a node
                if (selRow != -1) {
                    if (evt.getClickCount() == 2) { // double clicked
                        openSourceTextView((MarkedUpText) beta);
                    }
                }
            }
        }
    }

    private void importFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importFileActionPerformed
        JTree rep = (JTree) repositoryPane.getViewport().getView();
        TreePath alpha = rep.getSelectionPath();
        //Alpha is the selected folder from the repository pane
        // Checks to make sure that something was selected and it wasn't a text file
        if(alpha != null && !alpha.toString().contains(".")) {
            //the selected folder
            Folder parent = (Folder) alpha.getLastPathComponent();
            //Open the import file dialog box
            ImportFileDialog ifd = new ImportFileDialog(this, project);
            ifd.setVisible(true);
            if (ifd.hasResults()) {
                //import the new source text
                project.importSourceText(ifd.getFilePath(), parent);
                //Update to show the imported source text
                repository.updateUI();
            }
        }
    }//GEN-LAST:event_importFileActionPerformed

    private void commitProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commitProjectActionPerformed
        // TODO add your handling code here:
        CommitProjectDialog cpd = new CommitProjectDialog(this);
        cpd.setVisible(true);
        if (cpd.hasResults()) {
            this.project.saveProject();
            this.project.commitChanges(project.getCurrentUser().getName(), cpd.getCommit());
        }
    }//GEN-LAST:event_commitProjectActionPerformed

    private void mergeProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mergeProjectActionPerformed
        // TODO add your handling code here:
        MergeProjectDialog mpd = new MergeProjectDialog(this);
        mpd.setVisible(true);
        
        if(mpd.hasResults())
        {
            String working_dir = mpd.getWorkingDir();
            
            this.project.mergeProject(working_dir); 
            this.repaint();
            
        }
    }//GEN-LAST:event_mergeProjectActionPerformed

    private void newSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSearchActionPerformed
        TreeCheckingModel rep = new DefaultTreeCheckingModel(repository);
        TreeCheckingModel tag = new DefaultTreeCheckingModel(tags);
        addView(new SearchView(this, "Search", rep, tag));
    }//GEN-LAST:event_newSearchActionPerformed

    private void saveProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveProjectActionPerformed
        // TODO add your handling code here:
        this.project.saveProject();
    }//GEN-LAST:event_saveProjectActionPerformed

    private void createFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createFolderActionPerformed
        // TODO add your handling code here:
        JTree rep = (JTree) repositoryPane.getViewport().getView();
        TreePath alpha = rep.getSelectionPath();
        //Alpha is the selected folder and if nothing is selected then alpha is null
        //Alpha also can not be a source text file so it checks if it has a "." for an extension
        
        if(alpha != null && !alpha.toString().contains(".")) {
            //the selected folder
            Folder parent = (Folder) alpha.getLastPathComponent();
            //Open dialog box asking for new folder name
            CreateFolderDialog cfd = new CreateFolderDialog(this);
            cfd.setVisible(true);
            if (cfd.hasResults()) {
                //Add the new folder
                project.createFolder(parent, cfd.getFolderName());
                //Update UI with new folder
                repository.updateUI();
            }
        }
    }//GEN-LAST:event_createFolderActionPerformed
/**
     * Creates a new tag with the name entered that is a child of the tag selected
     * @param evt 
     */
    private void newTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTagActionPerformed
        JTree rep = (JTree) tagsPane.getViewport().getView();
        TreePath alpha = rep.getSelectionPath();
        //Alpha is the selected tag and make sure something is actually selected
        if(alpha != null) {
            //selected component
            TagType parent = (TagType) alpha.getLastPathComponent();
            //Open the new tag dialog box
            NewTagDialog nd = new NewTagDialog(this, project);
            nd.setVisible(true);
            if (nd.hasResults()) {
                //Add the new tag
                project.addTagType(parent, nd.getTagName());
                //Update the view to show the new tag
                tags.updateUI();
            }
        }
    }//GEN-LAST:event_newTagActionPerformed

    private void addView(View v) {
        views.addTab(v.getAbbrv(), null, v, v.getTitle());
        views.setSelectedIndex(views.indexOfComponent(v));
    }

    /**
     * Provides SourceTextView with a JTree of the tag hierarchy to select from.
     * @return a copy of tags with no checkboxes
     */
    public JTree getBlankTagTree() {
        TreeModel newTreeModel = tags.getModel();

        JTree newTree = new JTree(tags.getModel());
        newTree.setCellRenderer(uncheckedTags);
        newTree.setEditable(false);

        // make sure this new tree won't change the model
        newTree.addPropertyChangeListener(JTree.EDITABLE_PROPERTY, new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ((JTree) evt.getSource()).setEditable(false);
            }
        });

        return newTree;
    }
    private int helpViewIndex;
    private CheckboxTree repository;
    private BlankCheckboxTreeCellRenderer uncheckedRep;
    private DefaultCheckboxTreeCellRenderer checkedRep;
    private CheckboxTree tags;
    private BlankCheckboxTreeCellRenderer uncheckedTags;
    private DefaultCheckboxTreeCellRenderer checkedTags;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JMenuBar applicationMenu;
    private javax.swing.JSplitPane applicationPane;
    private javax.swing.JMenuItem closeProject;
    private javax.swing.JMenuItem commitProject;
    private javax.swing.JButton copyElement;
    private javax.swing.JButton copyTag;
    private javax.swing.JButton cutElement;
    private javax.swing.JButton cutTag;
    private javax.swing.JButton deleteElement;
    private javax.swing.JButton deleteTag;
    private javax.swing.JMenuItem helpContents;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton importFile;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem mergeProject;
    private javax.swing.JButton newFolder;
    private javax.swing.JMenuItem newProject;
    private javax.swing.JButton newSearch;
    private javax.swing.JButton newTag;
    private javax.swing.JButton newTagSet;
    private javax.swing.JMenuItem openProject;
    private javax.swing.JButton pasteElement;
    private javax.swing.JButton pasteTag;
    private javax.swing.JSplitPane projectData;
    private javax.swing.JMenu projectMenu;
    private javax.swing.JButton renameElement;
    private javax.swing.JButton renameTag;
    private javax.swing.JLabel repositoryLabel;
    private javax.swing.JScrollPane repositoryPane;
    private javax.swing.JToolBar repositoryTools;
    private javax.swing.JPanel repositoryWindow;
    private javax.swing.JMenuItem saveProject;
    private javax.swing.JLabel tagsLabel;
    private javax.swing.JScrollPane tagsPane;
    private javax.swing.JToolBar tagsTools;
    private javax.swing.JPanel tagsWindow;
    private javax.swing.JMenuItem viewVersionHistory;
    private javax.swing.JTabbedPane views;
    // End of variables declaration//GEN-END:variables
}
