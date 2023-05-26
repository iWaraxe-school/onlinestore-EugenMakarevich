package com.coherentsolutions.store.sorts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class XMLParser {
    public Map<String, String> parseXMLToMap(String pathFile, String nodeName) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(pathFile));
        Map<String, String> sorts = new TreeMap<>();

        NodeList nodes = doc.getElementsByTagName(nodeName);
        Element element = (Element) nodes.item(0);
        NodeList subnodes = element.getChildNodes();
        for (int i = 0; i < subnodes.getLength(); i++) {
            if (subnodes.item(i).getNodeType() == 1) {
                sorts.put(subnodes.item(i).getNodeName(), subnodes.item(i).getTextContent());
            }
        }
        return sorts;
    }
}
