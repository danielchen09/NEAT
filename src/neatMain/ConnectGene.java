package neatMain;

public class ConnectGene implements Cloneable {
    private NodeGene in;
    private NodeGene out;
    private float weight;
    private boolean enabled;
    private int innovation;

    public ConnectGene(NodeGene in, NodeGene out) {
        this.in = in;
        this.out = out;
        this.weight = (float) Math.random() * 2;
        this.enabled = true;
        this.innovation = ConnectGene.computeInnovation(this);
    }

    public static int computeInnovation(ConnectGene connectGene) {
        return computeInnovation(connectGene.getIn(), connectGene.getOut());
    }
    public static int computeInnovation(NodeGene inNode, NodeGene outNode) {
        int in = inNode.getId();
        int out = outNode.getId();
        //Szudzik's function, assuming in, out >= 0
        return in >= out ? in * in + in + out : in + out * out;
    }

    public NodeGene getIn() {
        return in;
    }

    public void setIn(NodeGene in) {
        this.in = in;
    }

    public NodeGene getOut() {
        return out;
    }

    public void setOut(NodeGene out) {
        this.out = out;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getInnovation() {
        return innovation;
    }

    public void setInnovation(int innovation) {
        this.innovation = innovation;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("neatMain.ConnectGene[in: %d, out: %d, innovation: %d, weight: %f, enabled: %b]",
                in.getId(),
                out.getId(),
                innovation,
                weight,
                enabled);
    }
}

