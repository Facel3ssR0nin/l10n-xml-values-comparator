package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlComparator {

    public static void compareXmlFiles(String file1Path, String file2Path) throws ParserConfigurationException,
            SAXException, IOException {
        PrintStream out = new PrintStream(Files.newOutputStream(Paths.get("console.log")));
        System.setOut(out);

        // Create a parser to read XML files
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Read the contents of the first XML file into a HashMap
        Map<String, String> file1Values = new HashMap<>();
        Document file1 = builder.parse(new File(file1Path));
        NodeList file1Nodes = file1.getElementsByTagName("string");
        for (int i = 0; i < file1Nodes.getLength(); i++) {
            Element element = (Element) file1Nodes.item(i);
            String name = element.getAttribute("name");
            String value = element.getTextContent();
            file1Values.put(name, value);
        }

        // Read the contents of the second XML file into a HashMap
        Map<String, String> file2Values = new HashMap<>();
        Document file2 = builder.parse(new File(file2Path));
        NodeList file2Nodes = file2.getElementsByTagName("string");
        for (int i = 0; i < file2Nodes.getLength(); i++) {
            Element element = (Element) file2Nodes.item(i);
            String name = element.getAttribute("name");
            String value = element.getTextContent();
            file2Values.put(name, value);
        }

        // Compare the values of keys in the two HashMaps
        int totalKeys = file1Values.size();
        int numMatches = 0;
        Set<String> matchingKeys = new HashSet<>();
        for (String key1 : file1Values.keySet()) {
            String value1 = file1Values.get(key1);
            for (String key2 : file2Values.keySet()) {
                String value2 = file2Values.get(key2);
                // Remove leading/trailing whitespace, quotes, and any spaces/semicolons at the end of the string.
// This is done to ensure that the two strings are compared in a consistent way, with no extraneous characters.
                double similarity = compareStrings(
                        value1.replaceAll("(^\\s*\"|\"\\s*$|\\s+)", ""), // remove quotes, leading/trailing whitespace, and any spaces
                        value2.replaceAll("(^\\s*\"|\"\\s*;?\\s*$|\\s+)", "") // remove quotes, leading/trailing whitespace, and any spaces/semicolons
                );
                if (similarity >= 0.5) {
                    numMatches++;
                    matchingKeys.add(key1);
                    System.out.println("Match found: " + file1Values.get(key1) + " in " + file1Path + " matches " + file2Values.get(key2) + " in " + file2Path + " with a similarity of " + similarity);
                    break;
                }
            }
        }
    }


    private static double compareStrings(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }

        int maxLen = Math.max(len1, len2);
        double similarity = 1;
        if (maxLen > 0) {
            similarity = (double) (maxLen - dp[len1][len2]) / maxLen;
        }

        return similarity;
    }


}