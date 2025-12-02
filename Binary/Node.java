package Binary;

import java.util.Random;
/**
 * Code Template Author: David G. Cooper
 * Purpose: A Binary Tree class for Arithmetic evaluation
 */
public class Node {
    private Node left;
    private Node right;
    private Op operation;
    private int depth;

    public Node(Unop operation) {
        this.operation = operation;
        this.depth = 0;
    }
    public Node(Op operation) {
        this.operation = operation;
        this.depth = 0;
    }
    public Node (Binop operation, Node left, Node right){
        this.operation = operation;
        this.depth = 0;
        attachLeft(left);
        attachRight(right);
    }
    // Helper methods to attach children and set their depth correctly
    private void attachLeft(Node child) {
        this.left = child;
        if (child != null) {
            child.setDepth(this.depth + 1);
        }
    }

    private void attachRight(Node child) {
        this.right = child;
        if (child != null) {
            child.setDepth(this.depth + 1);
        }
    }
    public int getDepth(){
        return this.depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand) {

        if (!(operation instanceof Binop)) {
            return;
        }

        if (this.depth == maxDepth) {
            attachLeft(nf.getTerminal(rand));
            attachRight(nf.getTerminal(rand));
            return;
        }
        int choiceRange = nf.getNumOps() + nf.getNumIndepVars();
        //left Child
        int pickLeft = rand.nextInt(choiceRange + 1);
        if (pickLeft < nf.getNumOps()) {
            Node L = nf.getOperator(rand);
            attachLeft(L);
            L.addRandomKids(nf, maxDepth, rand);
        } else {
            attachLeft(nf.getTerminal(rand));
        }
        //right child
        int pickRight = rand.nextInt(choiceRange + 1);
        if (pickRight < nf.getNumOps()) {
            Node R = nf.getOperator(rand);
            attachRight(R);
            R.addRandomKids(nf, maxDepth, rand);
        } else {
            attachRight(nf.getTerminal(rand));
        }
    }

    public Object clone() {
        Object o = null;
        try {
            o =  super.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("Op can't clone.");
        }
        Node b = (Node) o;
        if(left != null) {
            b.left = (Node) left.clone();
        }
        if(right != null) {
            b.right = (Node) right.clone();
        }
        if(operation != null) {
            b.operation = (Op) operation.clone();
        }
        return b;
    }

    public double eval(double[] values) {
        if (operation instanceof Unop) {
            return ((Unop) operation).eval(values);
        } else if (operation instanceof Binop) {
            double leftVal = left.eval(values);
            double rightVal = right.eval(values);

            return ((Binop) operation).eval(leftVal, rightVal);
        } else {
            System.err.println("Error operation is not a Unop or a Binop!");
            return 0.0;
        }
    }

    //Add a toString
    public String toString() {
        if (operation instanceof Unop) {
            return  operation.toString() ;
        }
        if (operation instanceof Binop) {
            return "("+ left.toString() + " " + operation.toString() + " " + right.toString()+ ")";
        } else {
            return "";
        }
    }
}


