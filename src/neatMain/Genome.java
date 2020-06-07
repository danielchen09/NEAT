package neatMain;

import dataStructure.BinarySearchTree;
import dataStructure.LinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Genome {

    private float fitness;

    private HashMap<Integer, NodeGene> nodeGeneList;
    private BinarySearchTree<ConnectGene> connectGeneList;

    private TreeMap<Float, ArrayList<NodeGene>> levels = new TreeMap<>();

    public Genome() {
        nodeGeneList = new HashMap<>();
        connectGeneList = new BinarySearchTree<>();
    }

    public static Genome crossOver(Genome g1, Genome g2) {
        Genome offspring = new Genome();

        LinkedList<ConnectGene> sorted1 = g1.getConnectGeneList().getSortedList();
        LinkedList<ConnectGene> sorted2 = g2.getConnectGeneList().getSortedList();

        while ((sorted1.get() != null) && (sorted2.get() != null)) {
            if (sorted1.get().getInnovation() == sorted2.get().getInnovation()) {
                offspring.addConnectGene(sorted1.get());

                sorted1.next();
                sorted2.next();
            } else if (sorted1.get().getInnovation() > sorted2.get().getInnovation()) {
                //1 2 4 vs 1 2 3, 4 is excess 3 is disjoint
                if (sorted1.hasNext()) {
                    offspring.addConnectGene(sorted1.get());
                }
                offspring.addConnectGene(sorted2.get());

                sorted2.next();
            } else {
                // 2 > 1, 1 2 4 vs 1 2 3
                if (sorted2.hasNext()) {
                    offspring.addConnectGene(sorted2.get());
                }
                offspring.addConnectGene(sorted1.get());

                sorted1.next();
            }
        }

        return null;
    }

    public void mutate() {
        switch((int)(Math.random() * 5)) {
            case 0: {
                mutateLink();
                break;
            }
            case 1: {
                mutateNode();
                break;
            }
            case 2: {
                mutateEnable();
                break;
            }
            case 3: {
                mutateWeight();
                break;
            }
            case 4: {
                mutateSetWeight();
                break;
            }
        }
    }
    public void mutateLink() {
        //new link
        int tries = 20;
        NodeGene node1, node2;
        do {
            node1 = getRandomNodeGene();
            node2 = getRandomNodeGene();
        } while ((node1.getLevel() == node2.getLevel()) &&
                (--tries > 0) ||
                (getConnectGeneList().elementExists(ConnectGene.computeInnovation(node1, node2))) ||
                (getConnectGeneList().elementExists(ConnectGene.computeInnovation(node2, node1))));

        if (tries <= 0) {
            return;
        }

        ConnectGene newConnection;
        if (node1.getLevel() < node2.getLevel()) {
            newConnection = new ConnectGene(node1, node2);
        } else {
            newConnection = new ConnectGene(node2, node1);
        }
        connectGeneList.addNode(newConnection, newConnection.getInnovation());
    }
    public void mutateNode() {
        //add a new node on a link
        if (connectGeneList.getRoot() == null) {
            return;
        }
        ConnectGene connectGene = connectGeneList.getRandom();
        NodeGene in = connectGene.getIn();
        NodeGene out = connectGene.getOut();
        NodeGene newNode = createNodeGene(NodeType.HIDDEN, (in.getLevel() + out.getLevel()) / 2);
        ConnectGene newConnect1 = new ConnectGene(in, newNode);
        ConnectGene newConnect2 = new ConnectGene(newNode, out);
        connectGene.setEnabled(false);
        connectGeneList.addNode(newConnect1, newConnect1.getInnovation());
        connectGeneList.addNode(newConnect2, newConnect2.getInnovation());
    }
    public void mutateEnable() {
        //flip enable
        if (connectGeneList.getRoot() == null) {
            return;
        }
        ConnectGene connectGene = connectGeneList.getRandom();
        connectGene.setEnabled(!connectGene.isEnabled());
    }
    public void mutateWeight() {
        //weight * (0~2)
        if (connectGeneList.getRoot() == null) {
            return;
        }
        ConnectGene connectGene = connectGeneList.getRandom();
        connectGene.setWeight(connectGene.getWeight() * (float)(Math.random() * 2));
    }
    public void mutateSetWeight() {
        //choose a new random weight
        if (connectGeneList.getRoot() == null) {
            return;
        }
        ConnectGene connectGene = connectGeneList.getRandom();
        connectGene.setWeight((float)Math.random() * 2);
    }

    public void addConnectGene(ConnectGene connectGene) {
        connectGeneList.addNode(connectGene, connectGene.getInnovation());
        if (nodeGeneList.get(connectGene.getIn().getId()) == null) {
            addNodeGene(connectGene.getIn());
        }
        if (nodeGeneList.get(connectGene.getOut().getId()) == null) {
            addNodeGene(connectGene.getOut());
        }
    }

    public NodeGene createNodeGene(NodeType type, float level) {
        NodeGene newNode = new NodeGene(type, level);
        if (levels.get(level) == null) {
            levels.put(level, new ArrayList<>());
        }
        levels.get(level).add(newNode);
        addNodeGene(newNode);
        return newNode;
    }

    public void addNodeGene(NodeGene nodeGene) {
        nodeGeneList.put(nodeGene.getId(), nodeGene);
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public HashMap<Integer, NodeGene> getNodeGeneList() {
        return nodeGeneList;
    }

    public void setNodeGeneList(HashMap<Integer, NodeGene> nodeGeneList) {
        this.nodeGeneList = nodeGeneList;
    }

    public NodeGene getRandomNodeGene() {
        return nodeGeneList.get(nodeGeneList.keySet().toArray()[(int)(Math.random() * nodeGeneList.size())]);
    }

    public BinarySearchTree<ConnectGene> getConnectGeneList() {
        return connectGeneList;
    }

    public void setConnectGeneList(BinarySearchTree<ConnectGene> connectGeneList) {
        this.connectGeneList = connectGeneList;
    }

    public TreeMap<Float, ArrayList<NodeGene>> getLevels() {
        return levels;
    }

    public void setLevels(TreeMap<Float, ArrayList<NodeGene>> levels) {
        this.levels = levels;
    }
}
