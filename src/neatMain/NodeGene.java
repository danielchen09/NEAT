package neatMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class NodeGene implements Cloneable {
    public static int maxID = 0;

    private int id;
    private NodeType type;
    private float level;

    public NodeGene (NodeType type, float level) {
        this.id = maxID + 1;
        maxID = this.id;
        this.type = type;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("neatMain.NodeGene[id: %d, type: %s, level: %f]",
                id,
                type.toString(),
                level);
    }
}
