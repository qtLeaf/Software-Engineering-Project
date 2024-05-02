package it.univr.wordautoma;

import java.io.FileWriter;
import java.io.IOException;

public class Graph {

    //private int nodes;
    private String dotContent;

    private String esistenceCheck;

    String dotFile;
    String imgFile;

    public Graph(String dotFile, String imgFile) {
        this.dotFile= "src/main/resources/it/univr/wordautoma/automas/" + dotFile;
        this.imgFile= "src/main/resources/it/univr/wordautoma/automas/" + imgFile;
        dotContent = "graph{\n" +
                "\tnode[fontname=\"Open Sans\", shape=\"circle\"]\n";
    }

    /*public void add() {
        nodes++;
        String addNode = "q" + nodes;
        dotContent += "\t" + addNode + "\n";
    }
     */

    public void addArrow(String node1, String value, String node2) {
        //rimovere ultima riga
        esistenceCheck = "\t" + node1 + "--" + node2 + "[label=\"" + value + "\"]\n";
        if(!dotContent.contains(esistenceCheck)){
            dotContent= dotContent.replace("}", "");
            dotContent+= esistenceCheck;
            close();
        }

    }//se esiste la non la aggiungo

    public void setNodeFinal(String node){
        dotContent += "\t" + node + "[shape=\"doublecircle\"]\n";

    }

    public void removeNode(String nodeToRemove) {
        // Remove the node line
        dotContent = dotContent.replaceAll("\\b" + nodeToRemove + "\\b.*?;\n", "\n");

        // Remove any edges involving the node being removed
        dotContent = dotContent.replaceAll("\\s*" + nodeToRemove + "\\s*--\\s*\\w+\\s*\\[.*?\\];?\n", "\n");
        dotContent = dotContent.replaceAll("\\s*\\w+\\s*--\\s*" + nodeToRemove + "\\s*\\[.*?\\];?\n", "\n");
    }


    public void close() {
        dotContent += "}\n";
        try {
            FileWriter writer = new FileWriter(dotFile);
            writer.write(dotContent);
            writer.close();

            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "-o" + imgFile, dotFile);
            Process process = pb.start();
            process.waitFor();
            System.out.println("Graph image generated: " + imgFile);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return dotContent;
    }
}
