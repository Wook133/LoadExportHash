package nmu.devilliers.Trash;

import nmu.devilliers.Account;
import org.w3c.dom.*;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class AccountXML {


    //write to XML
    //read from XML
    //get from XML
    //Query XML

    public static void main(String[] args) throws Exception {

       // saveDoc(createItemDocument(), "output.xml");
        //writeToXML("output.xml", new Account("David"));
        Account A = new Account();
        A.setPublicAddress("Tobias");
        queryXML("output",A);
    }

    public static void writeToXML(String sFilename, Account curAccount) throws Exception
    {
      /*  DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(sFilename);


//https://stackoverflow.com/questions/978810/how-to-strip-whitespace-only-text-nodes-from-a-dom-before-serialization
        XPathFactory xpathFactory = XPathFactory.newInstance();
// XPath to find empty text nodes.
        XPathExpression xpathExp = xpathFactory.newXPath().compile(
                "//text()[normalize-space(.) = '']");
        NodeList emptyTextNodes = (NodeList)
                xpathExp.evaluate(document, XPathConstants.NODESET);

// Remove each empty text node from document.
        for (int i = 0; i < emptyTextNodes.getLength(); i++) {
            Node emptyTextNode = emptyTextNodes.item(i);
            emptyTextNode.getParentNode().removeChild(emptyTextNode);
        }

        Element root = document.getDocumentElement();
        Element newAccount = document.createElement("Account");
        Element name = document.createElement("publicAddress");
        name.appendChild(document.createTextNode(curAccount.publicAddress));
        newAccount.appendChild(name);
        root.appendChild(newAccount);


        DOMSource source = new DOMSource(document);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //INDENT
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        StreamResult result = new StreamResult("output.xml");
        transformer.transform(source, result);*/
    }

    public static void queryXML(String sFile, Account curAccount)
    {

            //String sExpression = "/Accounts/Account/[@"+sPA;

    }











}
