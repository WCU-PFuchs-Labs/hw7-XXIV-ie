import java.util.Random;

public class NodeFactory {
    private Binop[] currentOps;
    private int numIndepVars;

    public NodeFactory(Binop [] binops, int numVars) {
        this.numIndepVars = numVars;
        this.currentOps = binops;

    }

    public Node getOperator (Random rand) {
        int index = rand.nextInt(currentOps.length);
        Binop clonedOp = (Binop) currentOps[index].clone();
        return new Node(clonedOp);
    }

    public Node getTerminal (Random rand) {
        int choice = rand.nextInt(numIndepVars + 1);

        if (choice < numIndepVars) {
            return new Node(new Variable(choice));
        } else {
            return new Node(new Const(rand.nextDouble()));
        }
    }

    public int getNumOps() {
        return currentOps.length;
    }

    public int getNumIndepVars() {
        return numIndepVars;
    }
}
