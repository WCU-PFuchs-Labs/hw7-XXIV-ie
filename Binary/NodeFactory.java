package Binary;

import java.util.Random;

public class NodeFactory {
    private final int numIndepVars;
    private final Binop[] currentOps;

    //Constructor that accepts Binop and Int numIndepVars
    public NodeFactory(Binop[] b, int numVars) {
        if (b == null || b.length == 0) {
            throw new IllegalArgumentException("The given Binop array is empty");
        }
        if (numVars < 0) {
            throw new IllegalArgumentException("The given number of variables is negative");
        }
        currentOps = b;
        numIndepVars = numVars;
    }
    public int getNumOps() {
        return currentOps.length;
    }
    public int getNumIndepVars() {
        return numIndepVars;
    }
    //Method getOperator (Random rand)
    public Node getOperator(Random rand) {
        int i = rand.nextInt(currentOps.length);
        Op op = (Op) currentOps [i].clone();
        return new Node(op);
    }
    //Method getTerminal (Random rand)
    public Node getTerminal(Random rand) {
    int r = rand.nextInt(numIndepVars +1);
    if (r < numIndepVars) {
        return new Node(new Variable(r));
    }else {
        return new Node(new Const(rand.nextDouble()));
        }
    }
}

