package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainPage {
    private JButton newEditorButton;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JPanel instancesPanel;

    private JFrame frame;
    private AtomicInteger numEditors;

    public MainPage(JFrame frame) {
        this.frame = frame;

        mainPanel.setPreferredSize(new Dimension(500, 500));

        numEditors = new AtomicInteger(0);
        newEditorButton.addActionListener(actionEvent -> {
            newEditor();
        });
    }

    public void newEditor() {
        numEditors.incrementAndGet();
        String name = "Neat Genome Editor " + numEditors;

        JDialog dialog = new JDialog(frame, name, true);
        Thread thread = new Thread(() -> {
            dialog.add(new GenomeEditor().getMainPanel());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(500, 500);
            dialog.pack();
            dialog.setVisible(true);
        });
        thread.start();

        instancesPanel.setLayout(new GridLayout(0, 1, 0, 10));
        instancesPanel.add(new NeatInstance(name, dialog, instancesPanel).getMainPanel());
        scrollPane.revalidate();
    }

    public JButton getNewEditorButton() {
        return newEditorButton;
    }

    public void setNewEditorButton(JButton newEditorButton) {
        this.newEditorButton = newEditorButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
