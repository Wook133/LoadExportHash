package nmu.devilliers;



import java.util.ArrayList;
import java.util.List;

//https://www.javagists.com/java-tree-data-structure
public class Node<T> {
    //display
    //builder method
    //method to broadcast e.g. to string which builder reads
    //find depth of a leaf
    //Done find depths of all leaves
    //Done find longest path to leaves
    //Done remove "edge" between
    //DONE: remove node completely
    //DONE: Searches/Traversal: In order/Post Order/Pre Order depth-first search, Breadth first search
    //No Need: Check for Cycles/Loops
    //DONE: get root
    //Done: get Path between nodes


    private T data = null;
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> addChild(Node<T> child) {
        if (child != this) {
            child.setParent(this);
            this.children.add(child);
        }
        return child;
    }

    public void addChildren(List<Node<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void printTree(Node<T> node, String sappend) {
        System.out.println(sappend + node.getData().toString());
        node.getChildren().forEach((each -> printTree(each, sappend + sappend)));
    }

    public Node getRoot()
    {
        if (parent == null)
        {
            return this;
        }
        return parent.getRoot();
    }

    public void deleteNode()
    {
        if (parent != null) {
            int index = this.parent.getChildren().indexOf(this);
            this.parent.getChildren().remove(this);
            for (Node<T> each : getChildren()) {
                each.setParent(this.parent);
            }
            this.parent.getChildren().addAll(index, this.getChildren());
        }
        else
        {
            deleteRootNode();
        }
        this.getChildren().clear();
    }

    public Node<T> deleteRootNode()
    {
        if (parent != null)
        {
            throw new IllegalStateException("deleteRootNode was not called on root node");
        }
        Node<T> newParent = null;
        if (!getChildren().isEmpty())
        {
            newParent = getChildren().get(0);
            newParent.setParent(null);
            getChildren().remove(0);
            for (Node<T> each : getChildren())
            {
                each.setParent(newParent);
            }
            newParent.getChildren().addAll(getChildren());
        }
        this.getChildren().clear();
        return newParent;
    }





}
