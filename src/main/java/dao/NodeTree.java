package dao;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class NodeTree {
    private int value;
    private List<NodeTree> next;

    public NodeTree(int value, List<NodeTree> next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<NodeTree> getNext() {
        return next;
    }

    public void setNext(List<NodeTree> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return next == null ?
                "[Node = {Value = " + value + "; Next = <NULL>}]" :
                "[Node = {Value = " + value + "; Next = (" + next.toString() + ")}]";
    }
}
