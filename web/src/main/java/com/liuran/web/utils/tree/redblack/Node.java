package com.liuran.web.utils.tree.redblack;

public class Node<T extends Comparable<? super T>>{

    public Node(T t){
        this.data = t;
    }

    private T data;

    public T getData() {
        return data;
    }

    public int compareTo(Object o) {
        Node node = (Node) o;
        return data.compareTo((T) node.getData());
    }
}
