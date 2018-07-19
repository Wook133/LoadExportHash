package nmu.devilliers;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class LeafStorage {
    TreeSet<deVillCargo> treeSet ;//= new TreeSet<>(deVillCargo::compareTo);

    public LeafStorage() {
        this.treeSet = new TreeSet<deVillCargo>();
    }

    public boolean addSource(deVillCargo curSource)
    {
        boolean added = false;
        added = treeSet.add(curSource);
        //System.out.println(added);
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
        System.out.println(treeSet.size());
        for (deVillCargo curLeaf : treeSet)
        {
            System.out.println("Hello : " + curLeaf.toStringWithHash());
        }
    }

   /*
    public int compare(deVillCargo o1, deVillCargo o2) {
        String s = o1.toStringWithHash();
        String sObj = o2.toStringWithHash();
        return (s.compareTo(sObj));
    }*/
}
