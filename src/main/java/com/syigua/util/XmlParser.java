package com.syigua.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class XmlParser {

    private Document document = null;

    public void buildDoc(String str){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new ByteArrayInputStream(str.getBytes()));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
    public String getValueByKey(String key) {
        NodeList contentNodes = document.getElementsByTagName(key);
        if (contentNodes.getLength() > 0) {
            Node contentNode = contentNodes.item(0);
            return contentNode.getTextContent();
        }
        return "";
    }

}
