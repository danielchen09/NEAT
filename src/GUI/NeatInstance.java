package GUI;

import javax.swing.*;

public class NeatInstance {
    private JButton inspectButton;
    private JButton deleteButton;
    private JLabel nameLabel;
    private JPanel mainPanel;
    private JButton renameButton;

    private String name;
    private JDialog editor;
    private JPanel parent;

    public NeatInstance(String name, JDialog editor, JPanel parent) {
        this.name = name;
        this.editor = editor;

        nameLabel.setText(name);
        inspectButton.addActionListener(actionEvent -> {
            if (editor == null) {
                return;
            }
            editor.setVisible(true);
        });
        deleteButton.addActionListener(actionEvent -> {
            if (editor == null) {
                return;
            }
            parent.remove(this.mainPanel);
            parent.revalidate();
            parent.repaint();
        });
        renameButton.addActionListener(actionEvent -> {
            if (editor == null) {
                return;
            }
            String newName = JOptionPane.showInputDialog("Enter new name:");
            this.name = newName;
            this.nameLabel.setText(newName);
            this.editor.setTitle(newName);
            this.mainPanel.revalidate();
            this.mainPanel.repaint();
            parent.revalidate();
            parent.repaint();
        });
    }

    public JButton getInspectButton() {
        return inspectButton;
    }

    public void setInspectButton(JButton inspectButton) {
        this.inspectButton = inspectButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JDialog getEditor() {
        return editor;
    }

    public void setEditor(JDialog editor) {
        this.editor = editor;
    }

    public JPanel getParent() {
        return parent;
    }

    public void setParent(JPanel parent) {
        this.parent = parent;
    }
}
