package it.univr.wordautoma_10;

import java.io.*;

public class Graph {

    //private int nodes;
    private String dotContent;

    private GraphManager graphM;

    String dotFile;
    String imgFile;

    private final String PATH= "src/main/resources/it/univr/wordautoma_10/automas/";

    public Graph(String fileName) {

        graphM= new GraphManager();

        dotContent = "digraph " + " {\n" + "\tnode[fontname=\"Open Sans\", shape=\"circle\"]\n" + "\t\"\"[shape=\"none\"]";
        this.dotFile= PATH + fileName + ".dot";
        this.imgFile= PATH + fileName + ".png";
        close();

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
        if(!graphM.nodeHasValue(node1, value)) {
            setDotContent("\t" + node1 + "->" + node2 + "[label=\"" + value + "\"]\n");
            graphM.setArrow(node1, value, node2);
        }
    }//se esiste la non la aggiungo

    public void setNodeInit(String node){
        if(!graphM.hasInit()){
            setDotContent("\t\"\" -> " + node + "\n");
            graphM.setInit(node);
        }
    }

    // Aggiunge graficamente un cerchio ad un nodo per renderlo finale
    public void setNodeFinal(String node){
        setDotContent("\t" + node + "[shape=\"doublecircle\"]\n");
        graphM.setFinal(node);
    }

    //aggiunge una stringa al dotContent
    private void setDotContent(String esistenceCheck){
        if(!dotContent.contains(esistenceCheck)){
            dotContent= dotContent.replace("}", "");
            dotContent+= esistenceCheck;
            close();
        }
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

    public void test(String key){
        //utilizzando graph manager devo trovare le parti di parola che si colleghino alle successive, prendedo il valore "più lungo possibile"
        //preferisco "aab" ad "aa"


    }
}