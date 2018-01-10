package com.company;

public class Node {
    private FatNode next;
    private FatNode previous;
    private int version;
    private FatNode myFatNode;

    public Node() {
        this.next = null;
        this.previous = null;
        this.version = -1;
    }

    public Node(FatNode next, FatNode previous, int version){
        this.version = version;
        this.previous = previous;
        this.next = next;
    }

    public Node(FatNode next, FatNode previous, int version, FatNode myFatNode){
        this.version = version;
        this.previous = previous;
        this.next = next;
        this.myFatNode = myFatNode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public FatNode getNext() {
        return next;
    }

    public void setNext(FatNode next) {
        this.next = next;
    }

    public FatNode getPrevious() {
        return previous;
    }

    public void setPrevious(FatNode previous) {
        this.previous = previous;
    }

    public FatNode getMyFatNode() {
        return myFatNode;
    }

    public void setMyFatNode(FatNode myFatNode) {
        this.myFatNode = myFatNode;
    }
}
