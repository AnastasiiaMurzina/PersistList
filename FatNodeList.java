package com.company;

public class FatNodeList {
    private FatNode head;
    private FatNode tail;
    private int currentVersion;

    public FatNode makeThisExtraToTail(FatNode fatNode, FatNode add) { //miss relationship to side of head
        Node node = new Node();
        FatNode fatNode1 = new FatNode(fatNode.name, node);
        node.setPrevious(add);
        node.setVersion(currentVersion);
        if (fatNode.getNext() != null && fatNode.getNext().isRealFat()) {
            node.setNext(makeThisExtraToTail(fatNode.getNext(), fatNode1));
            return fatNode1;
        }
        if (fatNode.getNext() != null) { //fatNode.getNext() has only one active node
            node.setNext(fatNode.getNext());
            Node node1 = new Node();
            node1.setPrevious(fatNode1);
            fatNode.getNext().setSecondNode(node1);
            node1.setNext(fatNode.getNext().getNext());
            return fatNode1;
        } // if fatNode.getNext() = null
        int n = 1;
        if (fatNode.name.substring(0, 4) == "tail") {
            n += Integer.valueOf(fatNode.name.substring(4));
        }
        Node node1 = new Node();
        FatNode fatNode2 = new FatNode(fatNode.getNext().name + n, node);
        node1.setPrevious(fatNode1);
        node1.setNext(fatNode.getNext());
        node.setNext(fatNode2);
        return fatNode1;
    }

    public FatNode makeThisExtraToHead(FatNode fatNode, FatNode add) { //miss relationship to side of head
        Node node = new Node();
        FatNode fatNode1 = new FatNode(fatNode.name, node);
        node.setNext(add);
        node.setVersion(currentVersion);

        if (fatNode.getPrevious() != null && fatNode.getPrevious().isRealFat()) {
            node.setPrevious(makeThisExtraToHead(fatNode.getPrevious(), fatNode1));
            return fatNode1;
        }
        if (fatNode.getPrevious() != null) { //fatNode.getNext() has only one active node
            node.setPrevious(fatNode.getPrevious());
            Node node1 = new Node();
            node1.setNext(fatNode1);
            fatNode.getPrevious().setSecondNode(node1);
            node1.setPrevious(fatNode.getPrevious().getPrevious());
            return fatNode1;
        } // if fatNode.getNext() = null
        int n = 1;
        if (fatNode.name.substring(0, 4) == "head") {
            n += Integer.valueOf(fatNode.name.substring(4));
        }
        Node node1 = new Node();
        FatNode fatNode2 = new FatNode(fatNode.getPrevious().name + n, node);
        node1.setNext(fatNode1);
        node1.setPrevious(fatNode.getPrevious());
        node.setPrevious(fatNode2);
        return fatNode1;
    }

    public void pushAfterNode(String name, Node node, FatNode pushAfterThis){
        setCurrentVersion(currentVersion+1);
        FatNode fatNode = new FatNode(name, node);
        node.setVersion(currentVersion);
            if (pushAfterThis.getNext().isRealFat()) {
                //here to write
                makeThisExtraToTail(pushAfterThis.getNext(), fatNode);
            } else {
                Node node1 = new Node();
                node1.setVersion(currentVersion);
                node1.setPrevious(fatNode);
                node1.setNext(pushAfterThis.getNext().getNext());
                pushAfterThis.getNext().setSecondNode(node1);
            }
            if (pushAfterThis.isRealFat()){
                //here to write
                makeThisExtraToHead(pushAfterThis, fatNode);
            } else {
                Node node2 = new Node();
                node2.setVersion(currentVersion);
                node2.setNext(fatNode);
                node2.setPrevious(pushAfterThis.getPrevious());
                pushAfterThis.setSecondNode(node2);
            }
    }
//////////////////////////////Work before persistance//////////////////////////
    public void addToBegin(String name, Node node){
        addAfterNode(name, node, getHead());
    }
    public FatNodeList() {
        Node headNode = new Node();
        Node tailNode = new Node();
        this.currentVersion = 0;
        headNode.setVersion(getCurrentVersion());
        tailNode.setVersion(getCurrentVersion());
        this.head = new FatNode("head", headNode);
        this.tail = new FatNode("tail", tailNode);
        headNode.setNext(tail);
        tailNode.setPrevious(head);
    }

    public void addToEnd(String name, Node node){
        addAfterNode(name, node, getTail().getPrevious());
    }

    public void addAfterNode(String name, Node toAdd, FatNode addAfterThis){
        FatNode toAddFat = new FatNode(name, toAdd);
        addAfterThis.getNext().setPrevious(toAddFat);
        toAddFat.setNext(addAfterThis.getNext());
        addAfterThis.setNext(toAddFat);
        toAddFat.setPrevious(addAfterThis);
    }

    public FatNode getFatNodeByNum(int n){
        FatNode fatNode = getHead();
        for (int i=0; i<n; i++){
            if (fatNode.getNext()!=null){
                fatNode = fatNode.getNext();
            } else {
                fatNode = getTail();
            }
        }
        return fatNode;
    }

    public FatNodeList(String[] names, Node[] nodes){
        for (int i=0; i<nodes.length; i++){
            addToEnd(names[i], nodes[i]);
        }
    }
////////////////////////////////Getters/setters///////////////////////////////
    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public FatNode getHead() {
        return head;
    }

    public FatNode getTail() {
        return tail;
    }

    public void setHead(FatNode head) {
        this.head = head;
    }

    public void setTail(FatNode tail) {
        this.tail = tail;
    }
}
