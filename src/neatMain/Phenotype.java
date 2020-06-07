package neatMain;

import dataStructure.LinkedList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phenotype {
    public class Connection {
        private Node node;
        private float weight;

        public Connection(Node node, float weight) {
            this.node = node;
            this.weight = weight;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }
    }
    public class Node {
        private NodeGene nodeGene;
        private List<Connection> inList;
        private List<Connection> outList;
        private float output;

        public Node(NodeGene nodeGene) {
            this.nodeGene = nodeGene;
        }

        public float compute() {
            if (inList == null) {
                return output;
            }
            float sum = 0;
            for (Connection connection : inList) {
                sum += connection.getNode().compute() * connection.getWeight();
            }
            return activation(sum);
        }

        public float activation(float x) {
            return x;
        }


        public NodeGene getNodeGene() {
            return nodeGene;
        }

        public void setNodeGene(NodeGene nodeGene) {
            this.nodeGene = nodeGene;
        }

        public List<Connection> getInList() {
            return inList;
        }

        public void setInList(List<Connection> inList) {
            this.inList = inList;
        }

        public void addIn (Node inNode, float weight) {
            Connection connection = new Connection(inNode, weight);
            inList.add(connection);
        }

        public List<Connection> getOutList() {
            return outList;
        }

        public void setOutList(List<Connection> outList) {
            this.outList = outList;
        }

        public void addOut(Node outNode, float weight) {
            Connection connection = new Connection(outNode, weight);
            outList.add(connection);
        }

        public float getOutput() {
            return output;
        }

        public void setOutput(float output) {
            this.output = output;
        }
    }

    private HashMap<Integer, Node> inList;
    private HashMap<Integer, Node> hiddenList;
    private HashMap<Integer, Node> outList;

    public Phenotype (Genome genome) {
        for (Map.Entry node : genome.getNodeGeneList().entrySet()) {
            addNode((NodeGene) node.getValue());
        }

        LinkedList<ConnectGene> connections = genome.getConnectGeneList().getSortedList();

        while (connections.hasNext()) {
            addConnection(connections.get());
            connections.next();
        }
    }

    public HashMap<Integer, Node> getList(Node node) {
        return getList(node.getNodeGene());
    }

    public HashMap<Integer, Node> getList(NodeGene nodeGene) {

        switch (nodeGene.getType()) {
            case INPUT: {
                return inList;
            }
            case HIDDEN: {
                return hiddenList;
            }
            case OUTPUT: {
                return  outList;
            }
        }
        return null;
    }

    public void addNode(NodeGene nodeGene) {
        Node node = new Node(nodeGene);

        getList(node).put(nodeGene.getId(), node);
    }

    public Node getNode(NodeGene nodeGene) {
        return getList(nodeGene).get(nodeGene.getId());
    }

    public void addConnection(ConnectGene connectGene) {
        getNode(connectGene.getIn()).addOut(getNode(connectGene.getOut()), connectGene.getWeight());
        getNode(connectGene.getOut()).addIn(getNode(connectGene.getIn()), connectGene.getWeight());
    }

}
