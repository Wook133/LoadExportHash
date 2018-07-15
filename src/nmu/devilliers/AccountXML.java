package nmu.devilliers;

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
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.FileOutputStream;

public class AccountXML {


    //write to XML
    //read from XML
    //get from XML

    /**
     * Create an empty document.
     * @return The newly created document.
     * @throws Exception If cannot create the document for some reason.
     */
    public static Document createDoc() throws Exception {
        // create DocumentBuilder to parse XML document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // create empty document
        Document doc = builder.newDocument();


        return doc;
    }

    /**
     * <p>Save the document to a file with the given filename.</p>
     * <p>You will be given the code for this method in a test, so you do not need to memorise it.</p>
     * @param doc The document to save.
     * @param filename The filename of the file to which the document will be saved.
     * @throws Exception If something goes wrong, e.g. invalid filename, not enough space, etc.
     */
    public static void saveDoc(Document doc, String filename) throws Exception {
        // obtain serializer
        DOMImplementation impl = doc.getImplementation();
        DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS", "3.0");
        LSSerializer ser = implLS.createLSSerializer();
        ser.getDomConfig().setParameter("format-pretty-print", true);

        // create file to save too
        FileOutputStream fout = new FileOutputStream(filename);

        // set encoding options
        LSOutput lsOutput = implLS.createLSOutput();
        lsOutput.setEncoding("UTF-8");

        // tell to save xml output to file
        lsOutput.setByteStream(fout);

        // FINALLY write output
        ser.write(doc, lsOutput);



        // close file
        fout.close();
    }

    /**
     * <p>Creates, populates and returns an element that simply contains a text node. The element will have the
     * following format: &lt;name&gt;text&lt;/name&gt;</p>
     * @param doc The document to which this element belongs.
     * @param name The tag name for the element.
     * @param text The text contained by the element.
     * @return The newly created element.
     */
    public static Element createTextElement(Document doc, String name, String text) {
        Text textNode = doc.createTextNode(text);
        Element element = doc.createElement(name);
        element.appendChild(textNode);
        return element;
    }



    /**
     * <p>Creates, populates and returns a product element in the form <pre>
     *     &lt;product&gt;
     *          &lt;description&gt;description&lt;/product&gt;
     *          &lt;price&gt;price&lt;/price&gt;
     *     &lt;/product&gt;
     * </pre></p>
     * @param doc The document to which the element belongs.
     * @param publicAddress The public address of the entity.
     * @return The newly created product.
     */
    public static Element createAccount(Document doc, String publicAddress) {
        Element Account = doc.createElement("Account");
        Account.appendChild(createTextElement(doc, "publicAddress", publicAddress));
        return Account;
    }


    /**
     * Creates and returns a document containing a number of items.
     */
    public static Document createItemDocument() throws Exception {
        Document doc = createDoc();

        Element items = doc.createElement("Accounts");
        items.appendChild(createAccount(doc, "Caramelo Bears"));
        items.appendChild(createAccount(doc, "Jelly Tots"));
        items.appendChild(createAccount(doc, "Tobias"));
        doc.appendChild(items);

        return doc;
    }

    public static void main(String[] args) throws Exception {

       // saveDoc(createItemDocument(), "output.xml");
        Append("output.xml", new Account("David"));
    }

    public static void Append(String sFilename, Account curAccount) throws Exception
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
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
        transformer.transform(source, result);

    }



}
