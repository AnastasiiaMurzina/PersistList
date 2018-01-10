package com.company;

public class FatNode {
    private Node firstNode;
    private Node secondNode;
    public String name;

    public FatNode(String name, Node firstNode){
        this.firstNode = firstNode;
        this.name = name;
        this.secondNode = null;
        firstNode.setMyFatNode(this);
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getSecondNode() {
        return secondNode;
    }

    public FatNode getNext(){
        if (isRealFat()){
            return getSecondNode().getNext();
        }
        return getFirstNode().getNext();
    }

    public void setNext(FatNode fatNode){
        if (isRealFat()){
            getSecondNode().setNext(fatNode);
        } else {
            getFirstNode().setNext(fatNode);
        }
    }

    public FatNode getPrevious(){
        if (isRealFat()){
            return secondNode.getPrevious();
        }
        return firstNode.getPrevious();
    }

    public void setPrevious(FatNode fatNode){
        if (isRealFat()){
            getSecondNode().setPrevious(fatNode);
        } else {
            getFirstNode().setPrevious(fatNode);
        }
    }

    public void setSecondNode(Node secondNode) {
        this.secondNode = secondNode;
        secondNode.setMyFatNode(this);
    }

    public boolean isRealFat(){
        if (getSecondNode() == null){
            return false;
        }
        return true;
    }
}
