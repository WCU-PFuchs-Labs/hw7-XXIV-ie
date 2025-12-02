import java.util.Random;

public class Node {
    private Node left;
    private Node right;
    private Op op;
    private int depth;

    public Node(Binop op, Node left, Node right) {
        this.left = left;
        this.right = right;
        this.op = op;
        this.depth = 0;
    }

    public Node(Unop op){
        this.op = op;
        this.left = null;
        this.right = null;
        this.depth = 0;
    }

    public Node(Binop op) {
        this.op = op;
        this.left = null;
        this.right = null;
        this.depth = 0;
    }

    public double eval(double [] values) {
        if (op instanceof Unop) {
            // Terminal node - delegate to Unop
            return ((Unop) op).eval(values);
        } else {
            // Binary operation node
            double leftVal = left.eval(values);
            double rightVal = right.eval(values);
            return ((Binop) op).eval(leftVal, rightVal);
        }
    }

    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("Not cloneable");
        }
        return 0;
    }

    public void addRandomKids (NodeFactory factory, int maxDepth, Random rand) {
        // If this is a terminal node(Unop), do nothing
        if (op instanceof Unop) {
            return;
        }

        // If we've reached max depth, add terminals for both children
        if (this.depth == maxDepth) {
            this.left = factory.getTerminal(rand);
            this.left.setDepth(this.depth + 1);

            this.right = factory.getTerminal(rand);
            this.right.setDepth(this.depth + 1);
            return;
        }

        // Otherwise, continue growing the tree
        int numOps = factory.getNumOps();
        int numIndepVars = factory.getNumIndepVars();

        //add left child
        int leftChoice = rand.nextInt(numOps + numIndepVars);
        if (leftChoice < numOps) {
            // add operator as left child
            this.left = factory.getOperator(rand);
            this.left.setDepth(this.depth + 1);
            this.left.addRandomKids(factory, maxDepth, rand);
        } else {
            // Add terminal as left child
            this.left = factory.getTerminal(rand);
            this.left.setDepth(this.depth + 1);
        }

        // add right child
        int rightChoice = rand.nextInt(numOps + numIndepVars);
        if (rightChoice < numOps) {
            // add operator as right child
            this.right = factory.getOperator(rand);
            this.right.setDepth(this.depth + 1);
            this.right.addRandomKids(factory, maxDepth, rand);
        } else {
            // add terminal as right child
            this.right = factory.getTerminal(rand);
            this.right.setDepth(this.depth + 1);
        }
    }

    public void traverse(Collector c) {
        c.collect(this);

        if (left != null) {
            left.traverse(c);
        }
        if (right != null) {
            right.traverse(c);
        }
    }

    public void swapLeft(Node trunk) {
        Node temp = this.left;
        this.left = trunk.left;
        trunk.left = temp;
    }

    public void swapRight(Node trunk) {
        Node temp = this .right;
        this.right = trunk.right;
        trunk.right = temp;
    }

    public boolean isLeaf() {
        return op instanceof Unop;
    }


    public String toString() {
        if (op instanceof Unop) {
            // terminal node
            return op.toString();
        } else {
            // Binary operation nodee
            return "(" + left.toString() + op.toString() + right.toString() + ")";
        }
    }

    // setter for depth
    public void setDepth(int depth) {
        this.depth = depth;
    }

    // Getter for depth
    public int getDepth() {
        return this.depth;
    }
}
