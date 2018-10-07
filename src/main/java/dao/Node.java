package dao;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Node {
    private int value;
    private Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return next == null ?
                "[Node = {Value = " + value + "; Next = <NULL>}]" :
                "[Node = {Value = " + value + "; Next = " + next.toString() + "}]";
    }
}
