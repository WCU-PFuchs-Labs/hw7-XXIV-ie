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
            return ((Unop) op).eval(values);
        } else {
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
        if (op instanceof Unop) {
            return;
        }

        if (this.depth == maxDepth) {
            this.left = factory.getTerminal(rand);
            this.left.setDepth(this.depth + 1);

            this.right = factory.getTerminal(rand);
            this.right.setDepth(this.depth + 1);
            return;
        }

        int numOps = factory.getNumOps();
        int numIndepVars = factory.getNumIndepVars();

        int leftChoice = rand.nextInt(numOps + numIndepVars);
        if (leftChoice < numOps) {
            this.left = factory.getOperator(rand);
            this.left.setDepth(this.depth + 1);
            this.left.addRandomKids(factory, maxDepth, rand);
        } else {
            this.left = factory.getTerminal(rand);
            this.left.setDepth(this.depth + 1);
        }

        int rightChoice = rand.nextInt(numOps + numIndepVars);
        if (rightChoice < numOps) {
            this.right = factory.getOperator(rand);
            this.right.setDepth(this.depth + 1);
            this.right.addRandomKids(factory, maxDepth, rand);
        } else {
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
            return op.toString();
        } else {
            return "(" + left.toString() + op.toString() + right.toString() + ")";
        }
    }
  
    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return this.depth;
    }
}
