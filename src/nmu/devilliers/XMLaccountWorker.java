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

    public static void main(String[] args) throws Exception {
        //Document f = createAccountDocument();
        File F = new File("NEW.xml");
        Document cur = parse(F);
        writeAccountsBack(cur);
        //AccountIterators(cur);
        //Append(cur, new Account("Jack"));
        //find(cur, new Account("Jack"));
        //tp();
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
        for (int i = 0, size = element.nodeCount(); i < size; i++)
        {
            Node node = element.node(i);

            if (node instanceof Element)
            {
                treeWalk((Element) node);
                System.out.println("Element: " + element.attributeValue("PublicAddress") + element.getText());
            }
            else
            {
                System.out.println("Element: " + element.attributeValue("PublicAddress"));
                /*System.out.println("Current Element :"+ node.getName()
                        + node.valueOf("@PublicAddress"));*/
                    /*Attribute att = element.attribute("PublicAddress");
                System.out.println("Data: " + att.getData());
                System.out.println("Value: " + att.getValue());*/
                // do something…
            }
        }
    }

    public static void tp()
    {
        try {
            File inputFile = new File("NEW.xml");
            SAXReader reader = new SAXReader();
            Document document = reader.read( inputFile );

            System.out.println("Root element :" + document.getRootElement().getName());

            Element classElement = document.getRootElement();


            List<Node> nodes = document.selectNodes("/Accounts/Account[@PublicAddress = 'Tobias']" );
            System.out.println("----------------------------");

            for (Node node : nodes) {
                System.out.println("\nCurrent Element :"
                        + node.getName());
                System.out.println("PA : "
                        + node.valueOf("@PublicAddress") );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static boolean find(Document d, Account curAccount)
    {
        boolean bfound = false;
        treeWalk(d);

        return bfound;
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
            Account.addAttribute("PublicAddress", curA.getPublicAddress());

            writer.write(document);
            writer.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void AppendAll(Document document, AccountStorage AccStor) throws IOException
    {
        try
        {
            TreeSet<String> treesetToAdd = new TreeSet<>();
            treesetToAdd = AccStor.getTreeSet();

            FileWriter fileWriter = new FileWriter("NEW.xml");
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(fileWriter, format);

            Element root = document.getRootElement();
            for (String s : treesetToAdd)
            {
                Element Account = root.addElement("Account");
                Account.addAttribute("PublicAddress", s);
            }


            writer.write(document);
            writer.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void write(Document document) throws IOException {

        // lets write to a file
       /* try (FileWriter fileWriter = new FileWriter("NEW.xml")) {
            XMLWriter writer = new XMLWriter(fileWriter);
            /*writer.write( document );
            writer.close();*/
            // Compact format to System.out
           /* OutputFormat format = OutputFormat.createCompactFormat();
            writer = new XMLWriter(System.out, format);
            writer.write(document);
            writer.close();
        }*/
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


        // Pretty print the document to System.out
       /* OutputFormat format = OutputFormat.createPrettyPrint();
        writer = new XMLWriter(System.out, format);
        writer.write( document );*/


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

        //AppendAll(replaceDoc, accSto);
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
                Account.addAttribute("PublicAddress", s);
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
