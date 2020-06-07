package GUI;

import neatMain.Genome;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GenomeEditor {
    private JButton mutateLinkButton;
    private JButton mutateNodeButton;
    private JButton mutateEnableButton;
    private JButton mutateWeightButton;
    private JButton mutateSetWeightButton;
    private JButton mutateButton;
    private JPanel displayPanel;
    private JPanel mainPanel;

    private GenomeDisplayer genomeDisplayer;

    public GenomeEditor() {
        mainPanel.setPreferredSize(new Dimension(1200, 600));
        mainPanel.repaint();
        mainPanel.revalidate();

        mutateLinkButton.addActionListener(actionEvent -> {
            genomeDisplayer.getGenome().mutateLink();
            genomeDisplayer.repaint();
        });
        mutateNodeButton.addActionListener(actionEvent -> {
            genomeDisplayer.getGenome().mutateNode();
            genomeDisplayer.repaint();
        });
        mutateEnableButton.addActionListener(actionEvent -> {
            genomeDisplayer.getGenome().mutateEnable();
            genomeDisplayer.repaint();
        });
        mutateWeightButton.addActionListener(actionEvent -> {
            genomeDisplayer.getGenome().mutateWeight();
            genomeDisplayer.repaint();
        });
        mutateSetWeightButton.addActionListener(actionEvent -> {
            genomeDisplayer.getGenome().mutateSetWeight();
            genomeDisplayer.repaint();
        });
        mutateButton.addActionListener(actionEvent -> {
            genomeDisplayer.getGenome().mutate();
            genomeDisplayer.repaint();
        });
    }

    public JButton getMutateLinkButton() {
        return mutateLinkButton;
    }

    public void setMutateLinkButton(JButton mutateLinkButton) {
        this.mutateLinkButton = mutateLinkButton;
    }

    public JButton getMutateNodeButton() {
        return mutateNodeButton;
    }

    public void setMutateNodeButton(JButton mutateNodeButton) {
        this.mutateNodeButton = mutateNodeButton;
    }

    public JButton getMutateEnableButton() {
        return mutateEnableButton;
    }

    public void setMutateEnableButton(JButton mutateEnableButton) {
        this.mutateEnableButton = mutateEnableButton;
    }

    public JButton getMutateWeightButton() {
        return mutateWeightButton;
    }

    public void setMutateWeightButton(JButton mutateWeightButton) {
        this.mutateWeightButton = mutateWeightButton;
    }

    public JButton getMutateSetWeightButton() {
        return mutateSetWeightButton;
    }

    public void setMutateSetWeightButton(JButton mutateSetWeightButton) {
        this.mutateSetWeightButton = mutateSetWeightButton;
    }

    public JButton getMutateButton() {
        return mutateButton;
    }

    public void setMutateButton(JButton mutateButton) {
        this.mutateButton = mutateButton;
    }

    public JPanel getDisplayPanel() {
        return displayPanel;
    }

    public void setDisplayPanel(JPanel displayPanel) {
        this.displayPanel = displayPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public GenomeDisplayer getGenomeDisplayer() {
        return genomeDisplayer;
    }

    public void setGenomeDisplayer(GenomeDisplayer genomeDisplayer) {
        this.genomeDisplayer = genomeDisplayer;
    }
}
