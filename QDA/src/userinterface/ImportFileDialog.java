/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Project;

/**
 * This dialog allows the user to select and import a file into the project.
 * @author dumnzzz-sager
 */
public class ImportFileDialog extends AppDialog {

    private JLabel fileLabel;
    private JTextField path;
    private JButton browse;
    private JLabel spacer;
    private JButton add;
    private JButton cancel;
    private JLabel error;
    private JFileChooser chooser;
    private String choosertitle;
    private Project p;

    /**
     * Creates a new ImportFileDialog.
     * @param mf owning window
     * @param p project into which the file will be imported
     */
    public ImportFileDialog(MainFrame mf, Project p) {
        super(mf, "ImportFile");
        this.p = p;

    }

    /**
     * Initializes the components of an ImportFileDialog.
     * @param args forwarded to super
     */
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);

        setMinimumSize(new java.awt.Dimension(420, 240));


        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        fileLabel = new JLabel("File Path:  ");
        fileLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(fileLabel, c);

        path = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(path, c);

        browse = new JButton("Browse...");
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 0;
        panel.add(browse, c);


        spacer = new JLabel("                                                           ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(spacer, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(spacer, c);

        add = new JButton("Add File");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(add, c);

        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 2;
        panel.add(cancel, c);

        error = new JLabel(" ");
        error.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(error, c);
    }
    
    /**
     * Provides the user with a file chooser to select a file to import.
     * @param evt unused
     */
    private void browseActionPerformed(ActionEvent evt) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    + chooser.getSelectedFile());
        } else {
            System.out.println("No Selection ");
        }

        File file = chooser.getSelectedFile();
        if (file == null) {
            error.setText("No Selection");
        } else {
            path.setText(file.toString());
        }
    }

    /**
     * Validates the input then closes the dialog.
     * @param evt unused
     */
    private void addActionPerformed(ActionEvent evt) {
        if (validateInput()) {
            hasResults = true;
            setVisible(false);
        }
    }

    /**
     * Closes the dialog.
     * @param evt unused
     */
    private void cancelActionPerformed(ActionEvent evt) {
        setVisible(false);
    }

    /**
     * Gets the inputted filepath
     * @return the inputted filepath
     */
    public String getFilePath() {
        return path.getText();
    }

    /**
     * Ensures the the inputted text is not empty and contains a '.'
     * @return whether or not the input text is valid
     */
    private boolean validateInput() {
        String l = path.getText();
        if (l.equals("")) {
            error.setText("File Path Required");
            return false;
        }
        if (!l.contains(".")) {
            error.setText("Invalid Source TexT");
            return false;
        }
        return true;
    }
}
