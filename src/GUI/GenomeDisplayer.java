package GUI;

import dataStructure.BinarySearchTree;
import dataStructure.LinkedList;
import neatMain.ConnectGene;
import neatMain.Genome;
import neatMain.NodeGene;
import neatMain.NodeType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class GenomeDisplayer extends JPanel {
    private Genome genome;

    private HashMap<NodeGene, Point> nodeToPos;

    public GenomeDisplayer() {
        this.nodeToPos = new HashMap<>();
        this.genome = new Genome();

        for (int i = 0; i < 8; i++) {
            this.genome.createNodeGene(NodeType.INPUT, 0);
        }
        for (int i = 0; i < 5; i++) {
            this.genome.createNodeGene(NodeType.OUTPUT, 1);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        Dimension dimension = this.getSize();
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, dimension.width, dimension.height);


        TreeMap<Float, ArrayList<NodeGene>> nodes = genome.getLevels();
        Object[] nodeLevels = nodes.keySet().toArray();
        int numLevels = nodeLevels.length;

        int stepWidth = dimension.width / (numLevels + 1);
        for (int i = 0; i < numLevels; i++) {
            ArrayList<NodeGene> column = nodes.get(nodeLevels[i]);
            int stepHeight = dimension.height / (column.size() + 1);
            for (int j = 0; j < column.size(); j++) {
                int x = stepWidth * (i + 1);
                int y = stepHeight * (j + 1);
                nodeToPos.put(column.get(j), new Point(x, y));
                graphics2D.setColor(Color.BLUE);
                graphics2D.fillOval(x, y, 10, 10);
                graphics2D.setColor(Color.YELLOW);
                graphics2D.drawString(String.valueOf(column.get(j).getId()), x, y);
            }
        }

        LinkedList<ConnectGene> connectGeneList = genome.getConnectGeneList().getSortedList();
        while (connectGeneList.get() != null) {
            Point start = nodeToPos.get(connectGeneList.get().getIn());
            Point end = nodeToPos.get(connectGeneList.get().getOut());

            graphics2D.setColor(connectGeneList.get().isEnabled() ? Color.GREEN : Color.RED);
            graphics2D.setStroke(new BasicStroke(3.0f));
            graphics2D.drawLine(start.x + 10, start.y + 5, end.x, end.y + 5);
            graphics2D.drawString(String.valueOf(connectGeneList.get().getWeight()),
                    (start.x + end.x) / 2,
                    (start.y + end.y) / 2);
            connectGeneList.next();
        }
        connectGeneList.reset();
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }
}
