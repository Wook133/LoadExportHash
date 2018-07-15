package nmu.devilliers;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileOutputStream;
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









}
