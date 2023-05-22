package org.example.utils.xmlmerge;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XmlMergerHelper {
    public static void mergeXmlFiles(String directoryPath) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        // Create a parser to read XML files
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Create a new Document object to hold the merged data
        Document mergedDoc = builder.newDocument();

        // Create the root element for the merged data
        Element resourcesElement = mergedDoc.createElement("resources");
        mergedDoc.appendChild(resourcesElement);

        // Create a HashMap to hold the merged data
        Map<String, String> mergedValues = new HashMap<>();

        // Loop through each file in the directory and read its contents into the merged data
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".xml")) {
                Document doc = builder.parse(file);
                NodeList nodes = doc.getElementsByTagName("string");
                for (int i = 0; i < nodes.getLength(); i++) {
                    Element element = (Element) nodes.item(i);
                    String name = element.getAttribute("name");
                    String value = element.getTextContent();
                    mergedValues.put(name, value);
                }
            }
        }

        // Loop through the merged data and create new elements for each key-value pair
        for (String key : mergedValues.keySet()) {
            Element stringElement = mergedDoc.createElement("string");
            stringElement.setAttribute("name", key);
            stringElement.setTextContent(mergedValues.get(key));
            resourcesElement.appendChild(stringElement);
        }

        // Write the merged data to a new XML file
        FileWriter writer = new FileWriter("generated_strings.xml");
        writer.write(XmlUtils.documentToString(mergedDoc));
        writer.close();
    }
}
