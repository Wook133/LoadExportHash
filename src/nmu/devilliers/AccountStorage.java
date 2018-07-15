package nmu.devilliers;

import java.util.TreeSet;

public class AccountStorage
{
    TreeSet<String> treeSet;

    public boolean addAccount(Account curAccount)
    {
        boolean added = treeSet.add(curAccount.getPublicAddress());
        return added;
    }

    public boolean containsAccount(Account curAccount)
    {
        return treeSet.contains(curAccount.getPublicAddress());
    }

    //write to XML
    //read from XML
    //get from XML







}
