package nmu.devilliers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.dom4j.*;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.Document;

public class XMLaccountWorker {

    //write to XML
    //read from XML
    //get from XML
    //Query XML

    public static void main(String[] args) throws Exception {
        //Document f = createAccountDocument();
        File F = new File("NEW.xml");
        Document cur = parse(F);

       // treeWalk(cur);
        //Append(cur, new Account("de Villiers"));
        Account ac = new Account("fd");
        //System.out.println("Jack: "+ ac.getPublicAddress());
        //System.out.println(treeWalkFind(cur, ac));


        AccountStorage accS = getAllAccounts(cur);
        System.out.println(accS.containsAccount(ac));

        //writeAccountsBack(cur);
        /*Account tofind = new Account();
        tofind.setPublicAddress("Tobiafs");
        System.out.println(find(cur, tofind));*/


    }



    public static Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }

    public static Document parse(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        return document;
    }

    public static void treeWalk(Document document) {
        treeWalk(document.getRootElement());
    }

    public static void treeWalk(Element element) {
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
                treeWalk( (Element) node );
            }
            else {
                String s = element.attributeValue("PublicAddress");
                if (s != null) {
                    System.out.println("Should Work: " + s);
                }
                // do something....
            }
        }
    }

    public static boolean treeWalkFind(Document document, Account accFind) {
        boolean bfound = false;
        bfound = treeWalkFind(document.getRootElement(), accFind);
        System.out.println(bfound);
        return bfound;

    }

    public static boolean treeWalkFind(Element element, Account accFind) {
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
                if (treeWalkFind( (Element) node, accFind ))
                    return true;
            }
            else {
                String s = element.attributeValue("PublicAddress");
                if ((s != null))
                {
                    if (s.compareTo(accFind.getPublicAddress()) == 0) {
                        return true;
                    }
                }

                // do something....
            }
        }
        return false;
    }



    /**
     * Find using Iterator
     * @param d
     * @param curAccount
     * @return true if PublicAddress found else false
     */
    public static boolean find(Document d, Account curAccount)
    {
        Element root = d.getRootElement();
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element element = it.next();
            Account curAcc = new Account();
            String s = element.attributeValue("PublicAddress");
            if (s.compareTo(curAccount.getPublicAddress()) == 0){return true;}
        }
        return false;
    }


    public static Document createDocument() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        Element author1 = root.addElement("author")
                .addAttribute("name", "James")
                .addAttribute("location", "UK")
                .addText("James Strachan");

        Element author2 = root.addElement("author")
                .addAttribute("name", "Bob")
                .addAttribute("location", "US")
                .addText("Bob McWhirter");

        return document;
    }

    public static Document createAccountDocument()
    {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Accounts");
        Element Account = root.addElement("Account");
        Account.addAttribute("PublicAddress", "Tobias");
        return document;
    }

    public static void Append(Document document, Account curA) throws IOException
    {
        try
        {
            FileWriter fileWriter = new FileWriter("NEW.xml");
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(fileWriter, format);

            Element root = document.getRootElement();
            Element Account = root.addElement("Account");
            Account.addAttribute("PublicAddress", curA.getPublicAddress()).addText(curA.getPublicAddress());

            writer.write(document);
            writer.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void write(Document document) throws IOException {
           try
           {
              /* FileWriter out = new FileWriter("NEW.xml");
               document.write(out);
               out.close();*/
              FileWriter fileWriter = new FileWriter("NEW.xml");
              OutputFormat format = OutputFormat.createPrettyPrint();
              XMLWriter writer = new XMLWriter(fileWriter, format);
              writer.write(document);
              writer.close();

           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
    }


    public static void AccountIterators(Document document) throws DocumentException {

        Element root = document.getRootElement();

        // iterate through child elements of root
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element element = it.next();
            System.out.println("Element: " + element.attributeValue("PublicAddress"));
            // do something
        }

        // iterate through child elements of root with element name "foo"
        for (Iterator<Element> it = root.elementIterator("foo"); it.hasNext();) {
            Element foo = it.next();

            // do something
        }

        // iterate through attributes of root
        for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext();) {
            Attribute attribute = it.next();
            System.out.println("Attribute: " + attribute.getValue() + attribute.getData());
            // do something
        }
    }

    /**
     * Removese duplicates and orders
     * @param document
     * @return
     * @throws Exception
     */
    public static AccountStorage getAllAccounts(Document document) throws Exception
    {
        AccountStorage AccStor = new AccountStorage();

        Element root = document.getRootElement();
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element element = it.next();
            //System.out.println("Element: " + element.attributeValue("PublicAddress"));
            Account curAcc = new Account();
            String s = element.attributeValue("PublicAddress");
            //System.out.println("Loop: "+ s);
            curAcc.setPublicAddress(s);
            AccStor.addAccount(curAcc);
            // do something
        }
        //AccStor.print();
        return AccStor;
    }

    public static void writeAccountsBack(Document d) throws Exception
    {
        AccountStorage accSto = new AccountStorage();
        accSto = getAllAccounts(d);
        Document replaceDoc = createBlankAccountDocument(accSto);
    }

    public static Document createBlankAccountDocument(AccountStorage AccStor)
    {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Accounts");

        try
        {
            TreeSet<String> treesetToAdd = new TreeSet<>();
            treesetToAdd = AccStor.getTreeSet();

            FileWriter fileWriter = new FileWriter("NEW.xml");
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(fileWriter, format);
            for (String s : treesetToAdd)
            {
                Element Account = root.addElement("Account");
                Account cA = new Account();
                cA.setPublicAddress(s);
                Account.addAttribute("PublicAddress", s).addText(cA.getPublicAddress());
            }
            writer.write(document);
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return document;
    }



}
