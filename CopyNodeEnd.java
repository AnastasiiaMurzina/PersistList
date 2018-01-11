public class CopyNodeEnd {
    private CopyNodeEnd nextVersion;
    private CopyNodeEnd next;
    private CopyNodeEnd previous;
    public String name;

    public CopyNodeEnd(String name){
        this.name = name;
    }
    public CopyNodeEnd getLastVersion(){
        CopyNodeEnd copyNode = this;
        while (copyNode.nextVersion!= null){
            copyNode = copyNode.nextVersion;
        }
        return copyNode;
    }

    public CopyNodeEnd getNext() {
        return next;
    }

    public void setNext(CopyNodeEnd nextNode) {
        this.next = nextNode;
    }

    public CopyNodeEnd getPrevious() {
        return previous;
    }

    public void setPrevious(CopyNodeEnd previous) {
        this.previous = previous;
    }

    public CopyNodeEnd getNextVersion() {
        return nextVersion;
    }

    public void setNextVersion(CopyNodeEnd nextVersion) {
        this.nextVersion = nextVersion;
    }
}
