package org.example;


import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class ExecuteComparator {
    public static void main(String[] args) {
        try {
            String file1 = "/Users/Facel3ssR0nin/generated_strings.xml";
            String file2 = "/Users/Facel3ssR0nin/strings.xml";
            XmlComparator.compareXmlFiles(file1, file2);
            System.out.println("Compare has been finished successfully!");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException | org.xml.sax.SAXException e) {
            System.err.println("Error reading file!");
            e.printStackTrace();
        }
    }
}