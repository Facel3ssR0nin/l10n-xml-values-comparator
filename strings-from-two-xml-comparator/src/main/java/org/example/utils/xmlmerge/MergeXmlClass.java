package org.example.utils.xmlmerge;

public class MergeXmlClass {
    public static void mergeXml(String directoryPath) {
        try {
            XmlMergerHelper.mergeXmlFiles(directoryPath);
            System.out.println("Merged XML files successfully!");
        } catch (Exception e) {
            System.err.println("Error merging XML files: " + e.getMessage());
        }
    }
    public static void doMerge() {
        MergeXmlClass.mergeXml("/Users/Fac3lessR0nin/folder-with-all-xml-that-need-to-be-merged");
    }

}
