package it.univr.wordautoma_10;

import java.util.ArrayList;
import java.util.List;

public class GraphManager {

    private List<Node> nodeList;
    private List<Arrow> arrowList;

    public GraphManager() {
        nodeList = new ArrayList<>();
        arrowList = new ArrayList<>();
    }

    public void setNode(String node) {
        if (!containsNode(node)) {
            nodeList.add(new Node(node));
        }
    }

    public void setArrow(String node1, String value, String node2) {
        Node n1 = getNode(node1);
        Node n2 = getNode(node2);
        if (n1 == null) {
            setNode(node1);
        }
        if (n2 == null) {
            setNode(node2);
        }
        arrowList.add(new Arrow(n1, value, n2));
    }

    public void setInit(String node) {
        Node n = getNode(node);
        if (n != null) {
            n.setInit(true);
        }
    }

    public void setFinal(String node) {
        Node n = getNode(node);
        if (n != null) {
            n.setFinal(true);
        }
    }

    private Node getNode(String nodeName) {
        for (Node node : nodeList) {
            if (node.isEqual(nodeName)) {
                return node;
            }
        }
        return null;
    }

    private boolean containsNode(String nodeName) {
        return getNode(nodeName) != null;
    }

    public boolean hasInit() {
        for (Node node : nodeList) {
            if (node.isInit()) {
                return true;
            }
        }
        return false;
    }

    public Node nodeInit() {
        for (Node node : nodeList) {
            if (node.isInit()) {
                return node;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GraphManager:\n");
        sb.append("Nodes:\n");
        for (Node node : nodeList) {
            sb.append("  ").append(node).append("\n");
        }
        sb.append("Arrows:\n");
        for (Arrow arrow : arrowList) {
            sb.append("  ").append(arrow).append("\n");
        }
        return sb.toString();
    }

    public List<Node> hasNext(String node1){
        List<Node> nextNodes= new ArrayList<>();
        for (Arrow arrow: arrowList){
            if(node1.equals(arrow.node2))
                nextNodes.add(arrow.node2);
        }
        return nextNodes;
    }

    public List<Arrow> nextArrow(String node1){
        List<Arrow> nextArrow= new ArrayList<>();
        for (Arrow arrow: arrowList){
            if(node1.equals(arrow.node2))
                nextArrow.add(arrow);
        }
        return nextArrow;
    }

    public Node pathSelction(String key){

        Node node= nodeInit();

        int maxPrefix, count, kl;
        while (key != null){
            List<Arrow> nextA= nextArrow(node.name);

            maxPrefix= 0;
            count= 0;
            kl=key.length();

            for(Arrow arrow: nextA){
                int l=arrow.value.length();
                for(int i=0; i<l; i++){
                    if(arrow.value.charAt(i) == key.charAt(kl - i)){
                        count++;
                    }else{
                        count=0;
                        break;
                    }
                }
                if(maxPrefix < count) {
                    maxPrefix = count;
                    node= arrow.node2;
                }
            }

            key.substring(maxPrefix);
        }

        if(node.isFinal)
            return node;

        return null;

    }

    boolean nodeHasValue(String node, String value){
        for (Arrow arrow : arrowList) {
            if (arrow.getNode1Name().equals(node) && arrow.getArrowValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static class Node {
        private String name;
        private boolean isInit;
        private boolean isFinal;

        public Node(String name) {
            this.name = name;
            this.isInit = false;
            this.isFinal = false;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isInit() {
            return isInit;
        }

        public void setInit(boolean isInit) {
            if(!isFinal)
                this.isInit = isInit;
        }

        public boolean isFinal() {  //usato in CTest
            return isFinal;
        }

        public void setFinal(boolean isFinal) {
            if(!isInit)
                this.isFinal = isFinal;
        }

        public boolean isEqual(String name) {
            return this.name.equals(name);
        }

        @Override
        public String toString() {
            return "Node{name='" + name + "', isInit=" + isInit + ", isFinal=" + isFinal + "}";
        }
    }

    private class Arrow {
        private Node node1;
        private String value;
        private Node node2;

        public Arrow(Node node1, String value, Node node2) {
            this.node1 = node1;
            this.value = value;
            this.node2 = node2;
        }

        @Override
        public String toString() {
            return "Arrow{node1=" + node1.getName() + ", value='" + value + "', node2=" + node2.getName() + "}";
        }
        public String getNode1Name(){
            return node1.getName();
        }
        public String getNode2Name(){
            return node2.getName();
        }
        public String getArrowValue(){
            return value;
        }


    }
}
