package it.univr.demo;

import java.io.FileWriter;
import java.io.IOException;

public class Graph {

    private int  nodes;

    private String dotContent;

    public Graph() {
        String startnode= "q0";
        dotContent = "graph Example {\n";
        dotContent += " " + startnode + "[shape= \"circle\"]\n";
        nodes= 1;

    }

    public void Add(){
        nodes++;
        String addNode= "q" + nodes;
        dotContent += " "+ addNode + "[shape= \"circle\"]\n";
    }

    public void AddArrow(String node1, String value, String node2){
        nodes++;
        String addNode= "q" + nodes;
        dotContent += " " + node1 +"->" + node2 + "[Headlable= " + value + "]\n";
    }

    public void Close(String dotFile, String imgFile) {
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

    public String ToString(){
        return dotContent;
    }
}
