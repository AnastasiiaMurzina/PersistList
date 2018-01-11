public class CopyingPath {
    private CopyNodeEnd head;
    private CopyNodeEnd tail;

    public CopyingPath(){
        CopyNodeEnd h = new CopyNodeEnd("head");
        CopyNodeEnd t = new CopyNodeEnd("tail");
        h.setNext(t);
        h.setPrevious(null);
        h.setNextVersion(null);
        t.setPrevious(h);
        this.head = h;
        this.tail = t;
    }
    public CopyingPath(CopyNodeEnd oneNode){
        CopyNodeEnd h = new CopyNodeEnd("head");
        CopyNodeEnd t = new CopyNodeEnd("tail");
        h.setNext(oneNode);
        oneNode.setPrevious(h);
        oneNode.setNextVersion(null);
        oneNode.setNext(t);
        h.setPrevious(null);
        h.setNextVersion(null);
        t.setPrevious(oneNode);
        this.head = h;
        this.tail = t;
    }
    public CopyingPath(CopyingPath copyingPath){
        CopyNodeEnd prevPath = copyingPath.getHead();
        while (prevPath.getNext()!=null){
            CopyNodeEnd newNode = new CopyNodeEnd(prevPath.name);
            newNode.setPrevious(prevPath.getPrevious());
            newNode.setNext(prevPath.getNext());
            prevPath.setNextVersion(newNode);
            prevPath = prevPath.getNext();
        }
        CopyNodeEnd nodeEnd = new CopyNodeEnd(prevPath.name);
        nodeEnd.setNextVersion(null);
        nodeEnd.setNext(null);
        nodeEnd.setPrevious(prevPath.getPrevious());
        prevPath.setNextVersion(nodeEnd);
        this.tail = nodeEnd;
        this.head = copyingPath.getHead().getNextVersion();
    }

    public CopyingPath pushNewNode(CopyNodeEnd nodeToPush, CopyNodeEnd addAfterThis){
        CopyingPath copyingPathLast = new CopyingPath(this);
        addAfterThis.getLastVersion().getNext().setPrevious(nodeToPush);
        nodeToPush.setNext(addAfterThis.getLastVersion().getNext());
        addAfterThis.getLastVersion().setNext(nodeToPush);
        nodeToPush.setPrevious(addAfterThis.getLastVersion());
        return copyingPathLast;
    }

    /////////////////////adders for FirstLine///////////////////////////////
    public void addNewCopyNode(CopyNodeEnd copyNode, CopyNodeEnd addAfterThis){
        addAfterThis.getNext().setPrevious(copyNode);
        copyNode.setNext(addAfterThis.getNext());
        copyNode.setPrevious(addAfterThis);
        addAfterThis.setNext(copyNode);
    }
    public void addToHead(CopyNodeEnd copyNode){
        addNewCopyNode(copyNode, getHead());
    }
    public void addToTail(CopyNodeEnd copyNode){
        addNewCopyNode(copyNode, getTail().getPrevious());
    }
    //////////////////////Getters/setters//////////////////////////////////////
    public CopyNodeEnd getTail() {
        return tail;
    }

    public CopyNodeEnd getHead() {
        return head;
    }

    public void setHead(CopyNodeEnd head) {
        this.head = head;
    }

    public void setTail(CopyNodeEnd tail) {
        this.tail = tail;
    }

    public CopyNodeEnd getThisNodeLastVersion(CopyNodeEnd node){
        CopyNodeEnd nodeEnd = node;
        while (nodeEnd.getNextVersion()!=null){
            nodeEnd = nodeEnd.getNext();
        }
        return nodeEnd;
    }

    @Override
    public String toString() {
        String nodes = "";
        CopyNodeEnd copyNodeEnd = getHead();
        while (copyNodeEnd.getNext()!=null){
            nodes+=copyNodeEnd.name;
            nodes+=" ";
            copyNodeEnd = copyNodeEnd.getNext();
        }
        nodes+=copyNodeEnd.name;
        return nodes;
    }
    public int countNodes(){
        int i = 0;
        CopyNodeEnd node = getHead();
        while (node.getNext()!=null){
            node = node.getNext();
            i+=1;
        }
        return i;
    }
}
