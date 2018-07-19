package nmu.devilliers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.util.Pair;
import org.dom4j.*;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.Document;

public class XMLdvc {

    public static void main(String[] args) throws Exception {
        File F = new File("Leaves.xml");
        Document cur = parse(F);

       // TreeSet<deVillCargo> treeDVC = new TreeSet<>(deVillCargo::compareTo);
        /*TreeSet<deVillCargo> treeDVC = new TreeSet<>();
        treeDVC.add(new deVillCargo());*/

       /* ArrayList<deVillCargo> list = new ArrayList<>();
        list = getAllLeavesList(cur);
        System.out.println(list.size());*/

        deVillCargo dvc = new deVillCargo("h1", "t1", "s1", "PA1", "OURL1");
        dvc.addLink("AURL0");
        dvc.addLink("AURL1");
        System.out.println(dvc.toStringWithHash());
        treeWalkFind(cur,dvc);
        LeafStorage LSS = new LeafStorage();
        LSS = getAllLeavesTree(cur);
        System.out.println(LSS.getSize());
        LSS.print();




        //System.out.println(cur.getRootElement().getText().toString());



        //System.out.println(list.size());


       // write(createDocument());
    }

    /*public class deVillCargoComparator implements Comparator<deVillCargo> {
        @Override
        public int compare(deVillCargo o1, deVillCargo o2) {
            if(o1.hashCargo().compareTo(o2.hashCargo()) == 0)
                return 0;
            else if (o1.hashCargo().compareTo(o2.hashCargo()) == -1)
                return -1;
            else
                return 1;
        }
    }*/


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
                Iterator<Attribute> itrA = element.attributeIterator();
                while (itrA.hasNext())
                {
                    Attribute curA = itrA.next();
                    String sAttributeName = curA.getName();
                    String sAttributeValue = curA.getValue();
                    System.out.println(sAttributeName + " : " + sAttributeValue);
                }
            }
        }
    }

    public static ArrayList<deVillCargo> treeWalkGet(Document document)
    {
        ArrayList<deVillCargo> treeCur = new ArrayList<>();
        treeCur.addAll(treeWalkGet(document.getRootElement()));
        return treeCur;
    }

    public static ArrayList<deVillCargo> treeWalkGet(Element element)
    {
        ArrayList<deVillCargo> treeCur = new ArrayList<>();
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
                treeWalkGet( (Element) node );
            }
            else {

                Iterator<Attribute> itrA = element.attributeIterator();
                String sType = element.getName();
                if (sType.compareTo("Source") == 0) {
                    //System.out.println(sType);
                    String HashofFile           = "";
                    String sHashCargo           = "";
                    String Timestamp            = "";
                    String SizeofFile           = "";
                    String publicAddressAdder   = "";
                    String OriginalURL          = "";

                    ArrayList<String> moreLinks = new ArrayList<>();
                    int iLinkCounter = 0;
                    while (itrA.hasNext())
                    {
                        Attribute curA = itrA.next();
                        String sAttributeName = curA.getName();
                        String sAttributeValue = curA.getValue();

                        switch(sAttributeName)
                        {
                            case "HashofCargo":
                            {
                                sHashCargo = sAttributeValue;
                            }
                            break;
                            case "HashofFile":
                            {
                                HashofFile = sAttributeValue;
                            }
                            break;
                            case "Timestamp":
                            {
                                Timestamp = sAttributeValue;
                            }
                            break;
                            case "SizeofFile":
                            {
                                SizeofFile = sAttributeValue;
                            }
                            break;
                            case "PublicAddressAdder":
                            {
                                publicAddressAdder = sAttributeValue;
                            }
                            break;
                            case "OriginalURL":
                            {
                                OriginalURL = sAttributeValue;
                            }
                            break;
                            default:
                            {
                                String sCombine = "AdditionalURL"+iLinkCounter;
                                if (sAttributeName.compareTo(sCombine) == 0)
                                {
                                    moreLinks.add(sAttributeValue);
                                    iLinkCounter = iLinkCounter+1;
                                }
                            }
                            break;
                        }
                    }
                    deVillCargo dvcCur = new deVillCargo(HashofFile, Timestamp, SizeofFile, publicAddressAdder, OriginalURL);
                    dvcCur.addLinks(moreLinks);
                    treeCur.add(dvcCur);
                    //System.out.println(dvcCur.toStringWithHash());
                    ;
                }
            }
        }
        System.out.println(treeCur.size());
        return treeCur;
    }

    public static boolean treeWalkFind(Document document, deVillCargo accFind) {
        boolean bfound = false;
        bfound = treeWalkFind(document.getRootElement(), accFind);
       // System.out.println(bfound);
        return bfound;
    }

    public static boolean treeWalkFind(Element element, deVillCargo accFind) {
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
                if (treeWalkFind( (Element) node, accFind ))
                    return true;
            }
            else {
                String s = element.attributeValue("HashofCargo");
                if ((s != null))
                {
                    if (s.compareTo(accFind.hashCargo()) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Document createDocument() {
        Document document = DocumentHelper.createDocument();
        /*Element root = document.addElement("root");

        Element author1 = root.addElement("author")
                .addAttribute("name", "James")
                .addAttribute("location", "UK")
                .addText("James Strachan");

        Element author2 = root.addElement("author")
                .addAttribute("name", "Bob")
                .addAttribute("location", "US")
                .addText("Bob McWhirter");*/


      /*  Element root = document.addElement("PoolSources");
        Element source1 = root.addElement("Source")
                .addElement("HashofCargo").addText("HC0")
                .addElement("HashofFile").addText("h0")
                .addAttribute("Timestamp", "t0")
                .addAttribute("SizeofFile", "s0")
                .addAttribute("PublicAddressAdder", "PA0")
                .addAttribute("OriginalURL", "OURL0")
                .addAttribute("AdditionalURL", "AURLO")
                .addAttribute("AdditionalURL1", "AURL1");*/

        Element root = document.addElement("PoolLeaves");
        Element source1 = root.addElement("Source")
                .addAttribute("HashofCargo",        "HC0")
                .addAttribute("HashofFile",         "h0")
                .addAttribute("Timestamp",          "t0")
                .addAttribute("SizeofFile",         "s0")
                .addAttribute("PublicAddressAdder", "PA0")
                .addAttribute("OriginalURL",        "OURL0")
                .addAttribute("AdditionalURL",      "AURLO")
                .addAttribute("AdditionalURL1",     "AURL1").addText("HC0");



/*
 <Sources>
    <Source>
        CargoHash = ""
        HashofFile = ""
        Timestamp = ""
        SizeofFIle = ""
        PublicAddressAdder = ""
        OriginalURL = ""
                <AdditionalURLs>
            <Link></Link>
        </AdditionalURLs>
    </Source>
</Sources>*/

        return document;
    }

    public static void write(Document document) throws IOException {
        try
        {
              /* FileWriter out = new FileWriter("NEW.xml");
               document.write(out);
               out.close();*/
            FileWriter fileWriter = new FileWriter("Leaves.xml");
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

    /**
     * Removese duplicates and orders
     * @param document
     * @return
     * @throws Exception
     */
    public static ArrayList<deVillCargo> getAllLeavesList(Document document) throws Exception {
        LeafStorage leafStore = new LeafStorage();
        ArrayList<deVillCargo> listOut = new ArrayList<>();

        Element root = document.getRootElement();
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            Iterator<Attribute> itrA = element.attributeIterator();
            String sType = element.getName();
            if (sType.compareTo("Source") == 0) {
                //System.out.println(sType);
                String HashofFile = "";
                String sHashCargo = "";
                String Timestamp = "";
                String SizeofFile = "";
                String publicAddressAdder = "";
                String OriginalURL = "";

                ArrayList<String> moreLinks = new ArrayList<>();
                int iLinkCounter = 0;
                while (itrA.hasNext()) {
                    Attribute curA = itrA.next();
                    String sAttributeName = curA.getName();
                    String sAttributeValue = curA.getValue();

                    switch (sAttributeName) {
                        case "HashofCargo": {
                            sHashCargo = sAttributeValue;
                        }
                        break;
                        case "HashofFile": {
                            HashofFile = sAttributeValue;
                        }
                        break;
                        case "Timestamp": {
                            Timestamp = sAttributeValue;
                        }
                        break;
                        case "SizeofFile": {
                            SizeofFile = sAttributeValue;
                        }
                        break;
                        case "PublicAddressAdder": {
                            publicAddressAdder = sAttributeValue;
                        }
                        break;
                        case "OriginalURL": {
                            OriginalURL = sAttributeValue;
                        }
                        break;
                        default: {
                            String sCombine = "AdditionalURL" + iLinkCounter;
                            if (sAttributeName.compareTo(sCombine) == 0) {
                                moreLinks.add(sAttributeValue);
                                iLinkCounter = iLinkCounter + 1;
                            }
                        }
                        break;
                    }
                }
                deVillCargo dvcCur = new deVillCargo(HashofFile, Timestamp, SizeofFile, publicAddressAdder, OriginalURL);
                dvcCur.addLinks(moreLinks);
                listOut.add(dvcCur);
            }
        }
        return listOut;
    }

    public static LeafStorage getAllLeavesTree(Document document) throws Exception {
        LeafStorage leafStore = new LeafStorage();
        //ArrayList<deVillCargo> listOut = new ArrayList<>();

        Element root = document.getRootElement();
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            Iterator<Attribute> itrA = element.attributeIterator();
            String sType = element.getName();
            if (sType.compareTo("Source") == 0) {
                //System.out.println(sType);
                String HashofFile = "";
                String sHashCargo = "";
                String Timestamp = "";
                String SizeofFile = "";
                String publicAddressAdder = "";
                String OriginalURL = "";

                ArrayList<String> moreLinks = new ArrayList<>();
                int iLinkCounter = 0;
                while (itrA.hasNext()) {
                    Attribute curA = itrA.next();
                    String sAttributeName = curA.getName();
                    String sAttributeValue = curA.getValue();

                    switch (sAttributeName) {
                        case "HashofCargo": {
                            sHashCargo = sAttributeValue;
                        }
                        break;
                        case "HashofFile": {
                            HashofFile = sAttributeValue;
                        }
                        break;
                        case "Timestamp": {
                            Timestamp = sAttributeValue;
                        }
                        break;
                        case "SizeofFile": {
                            SizeofFile = sAttributeValue;
                        }
                        break;
                        case "PublicAddressAdder": {
                            publicAddressAdder = sAttributeValue;
                        }
                        break;
                        case "OriginalURL": {
                            OriginalURL = sAttributeValue;
                        }
                        break;
                        default: {
                            String sCombine = "AdditionalURL" + iLinkCounter;
                            if (sAttributeName.compareTo(sCombine) == 0) {
                                moreLinks.add(sAttributeValue);
                                iLinkCounter = iLinkCounter + 1;
                            }
                        }
                        break;
                    }
                }
                deVillCargo dvcCur = new deVillCargo(HashofFile, Timestamp, SizeofFile, publicAddressAdder, OriginalURL);
                dvcCur.addLinks(moreLinks);
                leafStore.addSource(dvcCur);
            }
        }
        return leafStore;
    }


    /**fix
     * Find using Iterator
     * @param d
     * @param curAccount
     * @return true if PublicAddress found else false
     */
    public static boolean find(Document d, Account curAccount, Source curS)
    {
        Element root = d.getRootElement();
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element element = it.next();
            Source curSource = new Source(new deVillCargo());

            Account curAcc = new Account();
            String s = element.attributeValue("PublicAddress");
            if (s.compareTo(curAccount.getPublicAddress()) == 0){return true;}
        }
        return false;
    }



}
