package nmu.devilliers.Tree;

public class Blockchain extends LinkedMultiTreeNode<Block> {

    //prune
    //consensus
    //verify
    //add
    //build
    //prepare for transfer

    public void getParentsHash()
    {
        this.parent.data.getBlockHash();
    }


    /**
     * Creates an instance of this class
     *
     * @param data data to store in the current tree node
     */
    public Blockchain(Block data) {
        super(data);
    }
}
