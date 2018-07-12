package nmu.devilliers.Tree;


import nmu.devilliers.ProofOfWork;

public class Blockchain extends LinkedMultiTreeNode<Block>
{

    //prune
    //consensus
    //verify
    //add -> entire process
    //build
    //prepare for transfer
    //save
    //"View"
    //Receive

    private long totalNodeBlockReached;//total number of nodes this block has been received by
    private String parentsHash;


    /**
     * Creates an instance of this class
     *
     * @param data data to store in the current tree node
     */
    public Blockchain(Block data) {
        super(data);
        parentsHash = getMyParentsHash();
    }

    public Block getBlock()
    {
        return this.data();
    }

    public String getMyParentsHash()
    {
        if (this.isRoot() == true)
        {
            Block bcur = (Block)this.data;
            String s = bcur.getBlockHash();
            return s;
        }
        else if (this.parent.isRoot())
        {
            Block bcur = (Block)this.data;
            String s = bcur.getPrevBlockHash();
            return s;
        }
        else
        {
            Block bcur = (Block)this.data;
            String s = bcur.getPrevBlockHash();
            return s;
        }
    }

    public boolean verify(Blockchain A)
    {
        return A.verifyBlock();
    }

    public boolean sameParentHashBlock(Block A)
    {
        if (A.getPrevBlockHash().compareTo(this.getMyParentsHash()) == 0)
            return true;
        else
            return false;
    }

    public boolean sameParentBlockChain(Blockchain bcA)
    {
        if (bcA.getMyParentsHash().compareTo(this.getMyParentsHash()) == 0)
        {
            return true;
        }
        else
            return false;
    }

    /**
     * Check if this is the root Node -> generate this blocks merkle root and compare to what was given -> test nonce with PoW with this pattern with this blocks string
     * @return true if this link has been verified else false
     */
    public boolean verifyBlock()
    {
        if (this.isRoot() == false)
        {
            if (this.getMyParentsHash().compareTo(this.parentsHash) == 0)
            {
                String sMerkleRoot = this.data.generateMerkleRoot(this.data.getListSourceLeaves());
                if (sMerkleRoot.compareTo(this.data.getMerkleRoot()) == 0) {
                    ProofOfWork pow = new ProofOfWork(this.data.toPoWinputString(), this.data.makePattern());
                    String sBlockHash = pow.powString(this.data.getNonce(), 0);
                    if (sBlockHash == this.data.getBlockHash())
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            String sMerkleRoot = this.data.generateMerkleRoot(this.data.getListSourceLeaves());
            if (sMerkleRoot.compareTo(this.data.getMerkleRoot()) == 0) {
                ProofOfWork pow = new ProofOfWork(this.data.toPoWinputString(), this.data.makePattern());
                String sBlockHash = pow.powString(this.data.getNonce(), 0);
                if (sBlockHash == this.data.getBlockHash())
                {
                    return true;
                }
            }
        }
        return false;

    }


    public boolean add(Blockchain subtree)
    {
        if (subtree.verifyBlock()) {
            if (subtree == null) {
                return false;
            }
            if (!(subtree.isAncestorOf(this))) {
                linkParent(subtree, this);
                if (isLeaf())
                {
                    leftMostNode = subtree;
                    lastSubtreeNode = leftMostNode;
                } else {
                    lastSubtreeNode.rightSiblingNode = subtree;
                    lastSubtreeNode = lastSubtreeNode.rightSiblingNode;
                }
                return true;
            }
            return false;
        }
        return false;

    }

   /* public boolean add(TreeNode<T> subtree) {
        if (subtree == null) {
            return false;
        }
        if (!(subtree.isAncestorOf(this)))
        {
            linkParent(subtree, this);
            if (isLeaf()) {
                leftMostNode = (LinkedMultiTreeNode<T>) subtree;
                lastSubtreeNode = leftMostNode;
            } else {
                lastSubtreeNode.rightSiblingNode = (LinkedMultiTreeNode<T>) subtree;
                lastSubtreeNode = lastSubtreeNode.rightSiblingNode;
            }
            return true;
        }
        return false;
    }*/

}
