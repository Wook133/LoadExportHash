package nmu.devilliers;


import java.util.TreeSet;

public class AccountStorage
{
    TreeSet<String> treeSet;

    public AccountStorage() {
        this.treeSet = new TreeSet<>();
    }

    public boolean addAccount(Account curAccount)
    {
        boolean added = false;
        added = treeSet.add(curAccount.getPublicAddress());
        return added;
    }

    public TreeSet<String> getTreeSet() {
        return treeSet;
    }

    public int getPos(String s)
    {
        int ipos = 0;
        for (String scur : treeSet)
        {
            if (scur.compareTo(s) == 0)
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



    public boolean containsAccount(Account curAccount)
    {
        return treeSet.contains(curAccount.getPublicAddress());
    }

    public void print()
    {
        for (String s : treeSet)
        {
            System.out.println("Account:" + s);
        }
    }









}
