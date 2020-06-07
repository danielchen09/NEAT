package dataStructure;

import java.util.Random;

class TreeNode<T> {
    private T data;
    private TreeNode left;
    private TreeNode right;
    private float value;

    public TreeNode(T data, float value) {
        this.data = data;
        this.value = value;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public float getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
public class BinarySearchTree<T> {

    private TreeNode root;

    public void addNode(T data, float value) {
        TreeNode<T> treeNode = new TreeNode<>(data, value);
        addNode(root, treeNode);
    }
    private void addNode(TreeNode rootNode, TreeNode treeNode) {
        if (root == null) {
            root = treeNode;
            return;
        }
        if (treeNode.getValue() > rootNode.getValue()) {
            if (rootNode.getRight() == null) {
                rootNode.setRight(treeNode);
                return;
            }
            addNode(rootNode.getRight(), treeNode);
        } else {
            if (rootNode.getLeft() == null) {
                rootNode.setLeft(treeNode);
                return;
            }
            addNode(rootNode.getLeft(), treeNode);
        }
    }

    public LinkedList<T> getSortedList() {
        LinkedList<T> sortedList = new LinkedList<>();
        getSortedList(root, sortedList);
        return sortedList;
    }
    public void getSortedList(TreeNode treeNode, LinkedList sortedList) {
        if (treeNode == null) {
            return;
        }
        getSortedList(treeNode.getLeft(), sortedList);
        sortedList.add(treeNode.getData());
        getSortedList(treeNode.getRight(), sortedList);
    }

    public float getMax() {
        if (root == null) {
            return -1;
        }
        return getMax(root);
    }
    public float getMax(TreeNode node) {
        if (node.getRight() == null) {
            return node.getValue();
        }
        return getMax(node.getRight());
    }

    public int getSize() {
        return getSize(root);
    }
    public int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + getSize(node.getLeft()) + getSize(node.getRight());
    }

    public boolean elementExists(float value) {
        return elementExists(value, root);
    }
    public boolean elementExists(float value, TreeNode treeNode) {
        if (treeNode == null) {
            return false;
        }
        if (value > treeNode.getValue()) {
            return elementExists(value, treeNode.getRight());
        } else if (value < treeNode.getValue()) {
            return elementExists(value, treeNode.getLeft());
        }
        return true;
    }

    public T getRandom() {
        int rand = (int)(Math.random() * this.getSize());
        LinkedList<T> linkedList = this.getSortedList();
        while (rand-- > 0) {
            linkedList.next();
        }
        return linkedList.get();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return getSortedList().toString();
    }
}
