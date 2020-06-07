package dataStructure;

class ListNode<T> {
    private T data;
    private ListNode next;

    public ListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
public class LinkedList<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private ListNode<T> current;

    public void add(T data) {
        if (data == null) {
            return;
        }
        ListNode<T> listNode = new ListNode<>(data);
        add(listNode);
    }
    public void add(ListNode listNode) {
        if (listNode == null) {
            return;
        }
        if (head == null) {
            head = listNode;
            tail = listNode;
            current = listNode;
            return;
        }
        this.tail.setNext(listNode);
        this.tail = listNode;
    }

    public void add(LinkedList list) {
        if (list == null) {
            return;
        }
        this.tail.setNext(list.getHead());
        this.tail = list.tail;
    }

    public boolean hasNext() {
        return (current != null) && (current.getNext() != null);
    }

    public void next() {
        if (current == null) {
            return;
        }
        current = current.getNext();
    }

    public void reset() {
        current = head;
    }

    public T get() {
        if (current == null) {
            return null;
        }
        return current.getData();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("LinkedList{");
        ListNode<T> temp = head;
        while (temp != null) {
            str.append(temp.getData());
            if (temp.getNext() != null)
                str.append("->");
            temp = temp.getNext();
        }
        str.append("}");
        return str.toString();
    }

    public ListNode<T> getHead() {
        return head;
    }

    public void setHead(ListNode<T> head) {
        this.head = head;
    }

    public ListNode<T> getTail() {
        return tail;
    }

    public void setTail(ListNode<T> tail) {
        this.tail = tail;
    }

    public ListNode<T> getCurrent() {
        return current;
    }

    public void setCurrent(ListNode<T> current) {
        this.current = current;
    }
}
