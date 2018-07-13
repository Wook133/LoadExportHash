package nmu.devilliers.Tree;


import nmu.devilliers.ProofOfWork;
import nmu.devilliers.Source;
import nmu.devilliers.TimerServer;

import java.util.ArrayList;
import java.util.HashSet;

public class Blockchain extends LinkedMultiTreeNode<Block>
{

    //prune -> if no consensus -> get sources in set and delete nodes -> take sources and push into pool of unadded sources                      halfway
    //consensus                     no
    //verify                        yes
    //add -> entire process         yes
    //build                         no
    //prepare for transfer          no
    //save                          no
    //"View"                        no
    //Receive                       no

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

    /**
     * Adds the subtree with all of its descendants to the current tree node
     * as long as: subtree is not Ancestor of this tree thus avoiding cycles;
     *             this subtree's PrevBlockHash == this Block's hash;
     *            and it is a verified block (i.e. contents do what they're supposed to)
     * <p>
     * {@code null} subtree cannot be added, in this case return result will
     * be {@code false}
     * <p>
     * Checks whether this tree node was changed as a result of the call
     *
     * @param subtree subtree to add to the current tree node
     * @return {@code true} if this tree node was changed as a
     *         result of the call; {@code false} otherwise
     */
    public boolean add(Blockchain subtree)
    {
        if (subtree.getMyParentsHash().compareTo(this.data.getBlockHash()) == 0)
        //if ((this.parentsHash.compareTo(this.data.getPrevBlockHash()) == 0) && (this.data.getPrevBlockHash().compareTo(this.parent.data.getBlockHash()) == 0))
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
        }
        return false;
    }

    /**
     * Consensus to drop is 40% of all Peers must have a tree if longer than a day has passed
     * @param dTotal
     * @return
     */
    public HashSet<Source> prunebyConsensus(double dTotal)
    {
        double dconsensus = (totalNodeBlockReached * 1.00) / (dTotal * 1.00);
        long timestamp = this.data.getTimestamp();
        HashSet<LinkedMultiTreeNode<Block>> setOut = new HashSet<>();

        long lnow = getTimeNow();
        long timestampTimePlusDay = this.data.getTimestamp() + 86400000;
        HashSet<Source> setSources = new HashSet<>();
        if (((dconsensus < 0.4) && (lnow > timestampTimePlusDay)))
        {
            setOut = clearWithGet();
            clear();
            setSources = getSourcesfromTree(setOut);
            return setSources;
        }
        return setSources;
    }

    public HashSet<Source> getSourcesfromTree(HashSet<LinkedMultiTreeNode<Block>> setIn)
    {
        HashSet<Source> setSources = new HashSet<Source>();
        for (LinkedMultiTreeNode<Block> lmntB : setIn)
        {
            ArrayList<Source> curSources = new ArrayList<>();
            curSources = lmntB.data.getListSourceLeaves();
            for (Source scur : curSources)
            {
                setSources.add(scur);
            }
        }
        return setSources;
    }



    private static long getTimeNow()
    {
        TimerServer ts = new TimerServer();
        return ts.getTime();
    }

    /**
     * Removes all the subtrees with all of its descendants from the current
     * tree node but returns subtrees
     */
    public HashSet<LinkedMultiTreeNode<Block>> clearWithGet() {
        HashSet<LinkedMultiTreeNode<Block>> setOut = new HashSet<>();
        if (!isLeaf()) {
            LinkedMultiTreeNode<Block> nextNode = leftMostNode;
            setOut.add(nextNode);
            while (nextNode != null) {
                unlinkParent(nextNode);
                LinkedMultiTreeNode<Block> nextNodeRightSiblingNode = nextNode.rightSiblingNode;
                setOut.add(nextNodeRightSiblingNode);
                nextNode.rightSiblingNode = null;
                nextNode.lastSubtreeNode = null;
                nextNode = nextNodeRightSiblingNode;
                setOut.add(nextNode);
            }
            leftMostNode = null;
        }
        return setOut;
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
