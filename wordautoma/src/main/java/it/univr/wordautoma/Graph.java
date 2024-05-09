package it.univr.wordautoma;

import java.io.*;

public class Graph {

    //private int nodes;
    private String dotContent;

    private String esistenceCheck;

    String dotFile;
    String imgFile;

    private final String PATH="src/main/resources/it/univr/wordautoma/automas/";

    public Graph(String fileName) {
        if(!graphExist(PATH + "filesName.txt", fileName)) {        appendGrpahToList(fileName);
            dotContent = "graph " + fileName + " {\n" + "\tnode[fontname=\"Open Sans\", shape=\"circle\"]\n";
        }
        this.dotFile= PATH + fileName + ".dot";
        this.imgFile= PATH + fileName + ".png";
        this.close();
    }

    /*
    public void add() {
        nodes++;
        String addNode = "q" + nodes;
        dotContent += "\t" + addNode + "\n";
    }
     */

    // Aggiungo un arco tra due nodi inseriti (posso aggiungere qui in una lista con gli archi)
    public void addArrow(String node1, String value, String node2) {
        //rimovere ultima riga
        esistenceCheck = "\t" + node1 + "--" + node2 + "[label=\"" + value + "\"]\n";
        if(!dotContent.contains(esistenceCheck)){
            dotContent= dotContent.replace("}", "");
            dotContent+= esistenceCheck;
            close();
        }

    }//se esiste la non la aggiungo

    // Aggiunge graficamente un cerchio ad un nodo per renderlo finale
    public void setNodeFinal(String node){
        dotContent += "\t" + node + "[shape=\"doublecircle\"]\n";

    }

    // Rimuove un nodo scelto
    public void removeNode(String nodeToRemove) {
        // Remove the node line
        dotContent = dotContent.replaceAll("\\b" + nodeToRemove + "\\b.*?;\n", "\n");

        // Remove any edges involving the node being removed
        dotContent = dotContent.replaceAll("\\s*" + nodeToRemove + "\\s*--\\s*\\w+\\s*\\[.*?\\];?\n", "\n");
        dotContent = dotContent.replaceAll("\\s*\\w+\\s*--\\s*" + nodeToRemove + "\\s*\\[.*?\\];?\n", "\n");
    }


    // Chiude il file .dot e lo esegue per ottenere il .png (diverso per windows, serve il path di graphviz)
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

    // Controlla se il file è nel file contenente i file
    public boolean graphExist(String filePath, String fileToFind){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(fileToFind)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    // Aggiunge il nome del file nel file contente i nomi di file già esistenti
    private void appendGrpahToList(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "filesName.txt", true))) {
            writer.write(fileName + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Si trova gia' in lista");    }
    }

}