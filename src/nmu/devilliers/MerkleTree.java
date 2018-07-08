package nmu.devilliers;

import java.util.ArrayList;

public class MerkleTree
{
    private ArrayList<String> listHashes = new ArrayList<String>();//list of leaf nodes
    private merkleNode merkleRoot;//root of merkle tree
    private Boolean treeBuilt;
    private static class merkleNode
    {
        merkleNode left;
        merkleNode right;
        public String sHashData;

        merkleNode (String hash)
        {
            left = null;
            right = null;
            sHashData = hash;
        }
        merkleNode ()
        {
            left = null;
            right = null;
            sHashData = null;
        }
        merkleNode (merkleNode mnleft, merkleNode mnright)
        {
            left = mnleft;
            right = mnright;
        }

        Boolean isEqual(merkleNode mnOther)
        {
            Boolean b = false;
            if (this.sHashData.equals(mnOther.sHashData))
            {
                b = true;
            }
            return b;
        }

    }

    public void setMerkleRoot(String sin)
    {
        merkleRoot = new merkleNode(sin);
    }

    public String getMerkleRoot()
    {
        return merkleRoot.sHashData;

    }

    public int lengthList()
    {
        return this.listHashes.size();
    }


    /**
     * Initializes empty tree
     */
    public void MerkleTree()
    {
        treeBuilt = false;
        merkleRoot = null;
        listHashes.clear();
    }

    /**
     * Returns true if hash is in the tree
     * recursion
     * @param hashdata
     * @return
     */
    public boolean lookup(String hashdata)
    {
        return(lookup(merkleRoot, hashdata));
    }
    /**
     Recursive lookup  -- given a node, recur
     down searching for the given hash value.
     */
    private boolean lookup(merkleNode node, String hashdata) {
        if (node==null) {
            return(false);
        }

        if (hashdata==node.sHashData) {
            return(true);
        }
        else if (hashdata.compareTo(node.sHashData) < 0) {
            return(lookup(node.left, hashdata));
        }
        else {
            return(lookup(node.right, hashdata));
        }
    }

   /* public merkleNode makeParents(merkleNode mnLeft, merkleNode mnRight)
    {
        merkleNode Parent = new merkleNode();
    }*/

    public String getLeaf(int i)
    {
        if ((i >= 0) && (i < lengthList()))
        {
            return listHashes.get(i);
        }
        else
            return "";
    }

    public void buildTree(String HashToUse)
    {
        int inumLeaves = listHashes.size();
        int iadd = AmountToAdd(LogOfBase(2, listHashes.size()), 2);
       // System.out.println("Iadd: " + iadd);
        //if there are not enough leaves to make a perfect binary merkle tree then pad it with a specific element
        if (iadd != 0) {
            String sPad = listHashes.get(0);
            for (int j = 0; j <= iadd - 1; j++)
            {
                listHashes.add(sPad);
            }
        }
        try
        {
            int iheight = (int)LogOfBase(2, inumLeaves);
           // System.out.println("Height: " + iheight);
            int ihalf = inumLeaves;
            ArrayList<merkleNode> alParents = new ArrayList<merkleNode>();
            ArrayList<merkleNode> alGrandParents = new ArrayList<merkleNode>();
            GeneralHASH gh = new GeneralHASH();
            int m = 0;
            for (int i = 0; i <= listHashes.size() - 1; i++)
            {
                String s = listHashes.get(i);
                merkleNode mnCur = new merkleNode(s);
                alParents.add(mnCur);
            }
            //m = m + 1;
            boolean bpar = true;
            while (m < iheight)
            {
               // System.out.println("The M stands at: " + m);
                if ((bpar == true) && (alParents.size() > 0))
                {
                    alGrandParents.clear();
                  //  System.out.println("Parents Size: " + alParents.size());
                    for (int i = 0; i <= alParents.size() - 2; i+=2)
                    {
                     //   System.out.println("Parents:  " + i + " " +  alParents.get(i).sHashData + alParents.get(i+1).sHashData);
                        String scurHash = gh.HashnoPrint(alParents.get(i).sHashData + alParents.get(i+1).sHashData, HashToUse);
                      //  System.out.println("HASH: "+ scurHash);
                        merkleNode curNode = new merkleNode(scurHash);
                       // System.out.println("CN: " + curNode.sHashData);
                        alGrandParents.add(curNode);
                      //  System.out.println();

                    }
                    bpar = false;
                }
                if ((bpar == false) && (alGrandParents.size() > 0))
                {
                    alParents.clear();
                   // System.out.println("GrandParents Size: " + alGrandParents.size());
                    if (alGrandParents.size() == 1)
                    {
                   //     System.out.println("Hello : " +alGrandParents.get(0).sHashData);
                    }
                    for (int j = 0; j <= alGrandParents.size() - 2; j+=2)
                    {
                       // System.out.println("HASH gp: " + alGrandParents.get(j).sHashData);
                      //  System.out.println("GrandParents:  " + j + " " + alGrandParents.get(j).sHashData + alGrandParents.get(j+1).sHashData);
                        String scurHash = gh.HashnoPrint(alGrandParents.get(j).sHashData + alGrandParents.get(j+1).sHashData, HashToUse);
                      //  System.out.println("HASH: "+ scurHash);
                        merkleNode curNode = new merkleNode(scurHash);
                      //  System.out.println("CN: " + curNode.sHashData);
                        alParents.add(curNode);
                     //   System.out.println();
                    }
                    bpar = true;
                }
                m = m + 1;
            }
            if (alParents.size() == 1)
            {
               // System.out.println("Hello : " +alParents.get(0).sHashData);
                setMerkleRoot(alParents.get(0).sHashData);
                //merkleRoot.sHashData = alParents.get(0).sHashData;
            }
            if (alGrandParents.size() == 1)
            {
               // System.out.println("Hello : " +alGrandParents.get(0).sHashData);
                setMerkleRoot(alGrandParents.get(0).sHashData);
              //  merkleRoot.sHashData = alGrandParents.get(0).sHashData;
            }
            treeBuilt = true;










        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    /**while (m == 0)
     {
     if (alParents.size() == 1)
     {
     merkleRoot = alParents.get(0);
     System.out.println(merkleRoot.sHashData);
     }
     else if (alGrandParents.size() == 1)
     {
     merkleRoot = alGrandParents.get(0);
     System.out.println(merkleRoot.sHashData);
     }
     for (int k = 0; k < alParents.size() - 2; k++) {
     String scurHash = gh.Hash(alParents.get(k).sHashData + alParents.get(k + 1).sHashData, HashToUse);
     System.out.println(scurHash);
     merkleNode curNode = new merkleNode(scurHash);
     alGrandParents.add(curNode);
     System.out.println("M: " + m + " K: " + k);
     }
     alParents.clear();
     for (int n = 0; n < alGrandParents.size() - 2; n++) {
     String scurHash = gh.Hash(alGrandParents.get(n).sHashData + alGrandParents.get(n + 1).sHashData, HashToUse);
     System.out.println(scurHash);
     merkleNode curNode = new merkleNode(scurHash);
     alParents.add(curNode);
     System.out.println("M: " + m + " N: " + n);
     }

     alGrandParents.clear();
     m = m + 1;
     }**/

    /**
     Recursive insert -- given a node pointer, recur down and
     insert the given data into the tree. Returns the new
     node pointer (the standard way to communicate
     a changed pointer back to the caller).
     */
    private merkleNode insert(merkleNode node, String data) {
        if (node==null) {
            node = new merkleNode(data);
        }
        else {
            if (data.compareTo(node.sHashData) <= 0)  {
                node.left = insert(node.left, data);
            }
            else {
                node.right = insert(node.right, data);
            }
        }
        return(node); // in any case, return the new pointer to the caller
    }

    public void addHashes(String sHash)
    {
        listHashes.add(sHash);
    }
    public static double LogOfBase(int base, int num)
    {
        return Math.log(num) / Math.log(base);
    }
    public static int ceilingLogOfBase(int base, int num)
    {
        int i = (int)Math.ceil(Math.log(num) / Math.log(base));
        return i;
    }

    public static int AmountToAdd(double din, int base)
    {
        int inum = 0;
        int iadd;
        double ceiling = Math.ceil(din);
        if (ceiling == din) {
            return inum;
        }
        else
        {
            double dinPlay = Math.pow(base * 1.00, din);
            double difference = Math.pow(base * 1.00, ceiling) - dinPlay;
            return (int)difference;
        }
    }

    /**
     * Compare Whether 2 trees are identical, if roots of trees are the same then compare leaves
     * @param OtherTree
     * @return true if leaves and roots are the same else false
     */
    public Boolean compareTrees(MerkleTree OtherTree)
    {
        if (this.treeBuilt == OtherTree.treeBuilt == true)
        {
            if (!(this.merkleRoot.isEqual(OtherTree.merkleRoot)))
            {
                return false;
            }
            else
            {
                int icountHashes = lengthList();
                int icountOtherHashes = OtherTree.lengthList();
                if (icountHashes == icountOtherHashes)//automatically false if the size of the lists differs
                {
                    for (int i = 0; i <= icountHashes - 1; i++)
                    {
                        String s = this.getLeaf(i);
                        String sOther = OtherTree.getLeaf(i);
                        if (!(s.equals(sOther)))
                        {
                            return false;
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            System.out.println("Either Tree has not been built");
            return false;
        }
    }




}
