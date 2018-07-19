package nmu.devilliers;

import java.util.TreeSet;

public class LeafStorage {
    TreeSet<deVillCargo> treeSet;

    public LeafStorage() {
        this.treeSet = new TreeSet<>();
    }

    public boolean addSource(deVillCargo curSource)
    {
        boolean added = false;
        added = treeSet.add(curSource);
        return added;
    }

    public TreeSet<deVillCargo> getTreeSet() {
        return treeSet;
    }

    public int getPos(deVillCargo findSource)
    {
        int ipos = 0;
        String sLeafHash = findSource.hashCargo();//findSource.hashCargo();
        for (deVillCargo sCur : treeSet)
        {
            String sCurHash = sCur.hashCargo();
            if (sCurHash.compareTo(sLeafHash) == 0)
            {
                return ipos;
            }
            ipos = ipos + 1;
        }

        return ipos;
    }

    public int getSize()
    {
        return treeSet.size();
    }



    public boolean containsLeaf(Source curSource)
    {
        String sLeafHash = curSource.hashCargo();
        for (deVillCargo sCur : treeSet)
        {
            String sCurHash = sCur.hashCargo();
            if (sCurHash.compareTo(sLeafHash) == 0)
            {
                return true;
            }
        }
        return false;
    }

    public void print()
    {
        for (deVillCargo curLeaf : treeSet)
        {
            System.out.println(curLeaf.toStringWithHash());
        }
    }
}
